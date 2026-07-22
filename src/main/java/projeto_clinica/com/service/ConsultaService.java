package projeto_clinica.com.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import projeto_clinica.com.dto.Request.ConsultaRequestDTO;
import projeto_clinica.com.dto.Response.ConsultaResponseDTO;
import projeto_clinica.com.model.Consulta;
import projeto_clinica.com.model.Paciente;
import projeto_clinica.com.model.StatusConsulta;
import projeto_clinica.com.repository.ConsultaRepository;
import projeto_clinica.com.service.exceptions.ResourceNotFoundException;

@AllArgsConstructor
@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteService pacienteService;

    public ConsultaResponseDTO marcarConsulta(ConsultaRequestDTO dto) {
        Paciente paciente = pacienteService.buscarPorId(dto.pacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

        Consulta consulta = new Consulta();
        consulta.setDataHora(dto.dataHora());
        consulta.setPaciente(paciente);
        consulta.setMedicoId(dto.medicoId());
        consulta.setEnfermeiroId(dto.enfermeiroId());
        consulta.setObservacao(dto.observacao());
        consulta.setStatus(StatusConsulta.AGENDADA);

        return new ConsultaResponseDTO(consultaRepository.save(consulta));
    }

    public ConsultaResponseDTO remarcarConsulta(Long id, LocalDateTime novaDataHora) {
        return consultaRepository.findById(id).map(consulta -> {
            consulta.setDataHora(novaDataHora);
            return new ConsultaResponseDTO(consultaRepository.save(consulta));
        }).orElse(null);
    }

    public ConsultaResponseDTO cancelarConsulta(Long id) {
        return consultaRepository.findById(id).map(consulta -> {
            consulta.setStatus(StatusConsulta.CANCELADA);
            return new ConsultaResponseDTO(consultaRepository.save(consulta));
        }).orElse(null);
    }

    public Consulta buscarPorId(Long id) {
        return consultaRepository.findById(id).orElse(null);
    }

    public List<ConsultaResponseDTO> listarConsultasPorPaciente(Long pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId).stream()
                .map(ConsultaResponseDTO::new)
                .toList();
    }

    public List<ConsultaResponseDTO> listarConsultasPorMedico(Long medicoId) {
        return consultaRepository.findByMedicoId(medicoId).stream()
                .map(ConsultaResponseDTO::new)
                .toList();
    }

    public List<ConsultaResponseDTO> historicoConsultasPorPaciente(Long pacienteId) {
        return listarConsultasPorPaciente(pacienteId);
    }
}
