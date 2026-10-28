package projeto_clinica.com.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto_clinica.com.dto.Request.PrescricaoRequestDTO;
import projeto_clinica.com.dto.Response.PrescricaoResponseDTO;
import projeto_clinica.com.model.Prescricao;
import projeto_clinica.com.repository.PrescricaoRepository;
import projeto_clinica.com.repository.PacienteRepository;
import projeto_clinica.com.repository.FuncionarioRepository;
import projeto_clinica.com.service.exceptions.ResourceNotFoundException;
import projeto_clinica.com.service.exceptions.BusinessRuleException;

@Service
public class PrescricaoService {
    private final PrescricaoRepository repository;
    private final PacienteRepository pacientes;
    private final FuncionarioRepository funcionarios;

    public PrescricaoService(PrescricaoRepository repository, PacienteRepository pacientes, FuncionarioRepository funcionarios) {
        this.repository = repository;
        this.pacientes = pacientes;
        this.funcionarios = funcionarios;
    }

    @Transactional(readOnly = true)
    public List<PrescricaoResponseDTO> findAll() {
        return repository.findAll().stream().map(PrescricaoResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public PrescricaoResponseDTO findById(Long id) {
        return new PrescricaoResponseDTO(buscar(id));
    }

    @Transactional
    public PrescricaoResponseDTO create(PrescricaoRequestDTO dto) {
        Prescricao prescricao = new Prescricao();
        copiar(dto, prescricao);
        return new PrescricaoResponseDTO(repository.save(prescricao));
    }

    @Transactional
    public PrescricaoResponseDTO update(Long id, PrescricaoRequestDTO dto) {
        Prescricao prescricao = buscar(id);
        copiar(dto, prescricao);
        return new PrescricaoResponseDTO(repository.save(prescricao));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(buscar(id));
    }

    private Prescricao buscar(Long id) {
        return repository.findByIdWithAssociations(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescrição não encontrada. ID: " + id));
    }

    private void copiar(PrescricaoRequestDTO dto, Prescricao p) {
        p.setPaciente(pacientes.findById(dto.pacienteId()).orElseThrow(
                () -> new ResourceNotFoundException("Paciente não encontrado. ID: " + dto.pacienteId())));
        var medico = funcionarios.findByIdWithRoles(dto.medicoId()).orElseThrow(
                () -> new ResourceNotFoundException("Funcionário não encontrado. ID: " + dto.medicoId()));
        if (medico.getRoles().stream().noneMatch(role -> "ROLE_MEDICO".equals(role.getAuthority()))) {
            throw new BusinessRuleException("O funcionário indicado não possui a role de médico.");
        }
        p.setMedico(medico);
        p.setObservacoes(dto.observacoes());
    }
}
