package projeto_clinica.com.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto_clinica.com.dto.Request.ConsultaRequestDTO;
import projeto_clinica.com.dto.Response.ConsultaResponseDTO;
import projeto_clinica.com.model.Consulta;
import projeto_clinica.com.model.Funcionario;
import projeto_clinica.com.model.enums.StatusConsulta;
import projeto_clinica.com.repository.ConsultaRepository;
import projeto_clinica.com.repository.FuncionarioRepository;
import projeto_clinica.com.service.exceptions.ResourceNotFoundException;

@Service
public class ConsultaService {
    private final ConsultaRepository consultas;
    private final PacienteService pacientes;
    private final FuncionarioRepository funcionarios;

    public ConsultaService(ConsultaRepository consultas, PacienteService pacientes, FuncionarioRepository funcionarios) {
        this.consultas = consultas;
        this.pacientes = pacientes;
        this.funcionarios = funcionarios;
    }

    @Transactional
    public ConsultaResponseDTO marcarConsulta(ConsultaRequestDTO dto) {
        var paciente = pacientes.buscarPorId(dto.pacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente nao encontrado"));
        Funcionario medico = funcionarios.findByIdWithRoles(dto.medicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Medico nao encontrado"));
        boolean isMedico = medico.getRoles().stream().anyMatch(r -> "ROLE_MEDICO".equals(r.getAuthority()));
        if (!isMedico) throw new IllegalArgumentException("Funcionario nao possui ROLE_MEDICO");
        verificarConflito(medico.getId(), dto.dataHora());

        Consulta consulta = new Consulta();
        consulta.setDataHora(dto.dataHora());
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setObservacao(dto.observacao());
        consulta.setStatus(StatusConsulta.AGENDADA);
        return new ConsultaResponseDTO(consultas.save(consulta));
    }

    @Transactional
    public ConsultaResponseDTO remarcarConsulta(Long id, LocalDateTime novaDataHora) {
        Consulta consulta = buscarEntidade(id);
        verificarConflito(consulta.getMedico().getId(), novaDataHora, id);
        consulta.setDataHora(novaDataHora);
        return new ConsultaResponseDTO(consultas.save(consulta));
    }

    @Transactional
    public ConsultaResponseDTO cancelarConsulta(Long id) {
        Consulta consulta = buscarEntidade(id);
        consulta.setStatus(StatusConsulta.CANCELADA);
        return new ConsultaResponseDTO(consultas.save(consulta));
    }

    @Transactional(readOnly = true)
    public List<ConsultaResponseDTO> listarConsultasPorPaciente(Long pacienteId) {
        return consultas.findByPacienteId(pacienteId).stream().map(ConsultaResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<ConsultaResponseDTO> listarConsultasPorMedico(Long medicoId) {
        return consultas.findByMedico_Id(medicoId).stream().map(ConsultaResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<ConsultaResponseDTO> historicoConsultasPorPaciente(Long pacienteId) {
        return listarConsultasPorPaciente(pacienteId);
    }

    private Consulta buscarEntidade(Long id) {
        return consultas.findByIdWithPacienteAndMedico(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta nao encontrada"));
    }

    private void verificarConflito(Long medicoId, LocalDateTime dataHora) {
        verificarDataConflito(medicoId, dataHora, null);
    }

    private void verificarConflito(Long medicoId, LocalDateTime dataHora, Long consultaId) {
        verificarDataConflito(medicoId, dataHora, consultaId);
    }

    private void verificarDataConflito(Long medicoId, LocalDateTime dataHora, Long consultaId) {
        if (dataHora == null || !dataHora.isAfter(LocalDateTime.now()))
            throw new IllegalArgumentException("A consulta deve ter data e hora futuras");
        boolean existeConflito = consultaId == null
                ? consultas.existsByMedico_IdAndDataHoraAndStatusNot(medicoId, dataHora, StatusConsulta.CANCELADA)
                : consultas.existsByMedico_IdAndDataHoraAndStatusNotAndIdNot(
                        medicoId, dataHora, StatusConsulta.CANCELADA, consultaId);
        if (existeConflito)
            throw new IllegalArgumentException("O medico ja possui consulta nesse horario");
    }
}
