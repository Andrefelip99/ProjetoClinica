package projeto_clinica.com.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto_clinica.com.model.Prontuario;
import projeto_clinica.com.model.Paciente;
import projeto_clinica.com.repository.PacienteRepository;
import projeto_clinica.com.service.exceptions.ResourceNotFoundException;

import projeto_clinica.com.dto.Request.ProntuarioRequestDTO;
import projeto_clinica.com.dto.Response.ProntuarioResponseDTO;
import projeto_clinica.com.repository.ProntuarioRepository;

@Service
public class ProntuarioService {
    private final ProntuarioRepository repository;
    private final PacienteRepository pacientes;

    public ProntuarioService(ProntuarioRepository repository, PacienteRepository pacientes) {
        this.repository = repository;
        this.pacientes = pacientes;
    }

    @Transactional(readOnly = true)
    public List<ProntuarioResponseDTO> findAll() {
        return repository.findAll().stream().map(ProntuarioResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ProntuarioResponseDTO findById(Long id) {
        return new ProntuarioResponseDTO(buscar(id));
    }

    @Transactional
    public ProntuarioResponseDTO create(ProntuarioRequestDTO dto) {
        Prontuario prontuario = new Prontuario();
        copiar(dto, prontuario);
        return new ProntuarioResponseDTO(repository.save(prontuario));
    }

    @Transactional
    public ProntuarioResponseDTO update(Long id, ProntuarioRequestDTO dto) {
        Prontuario prontuario = buscar(id);
        copiar(dto, prontuario);
        return new ProntuarioResponseDTO(repository.save(prontuario));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(buscar(id));
    }

    private Prontuario buscar(Long id) {
        return repository.findByIdWithPaciente(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prontuário não encontrado. ID: " + id));
    }

    private void copiar(ProntuarioRequestDTO dto, Prontuario prontuario) {
        Paciente paciente = pacientes.findById(dto.paciente())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado. ID: " + dto.paciente()));
        prontuario.setPaciente(paciente);
    }
}
