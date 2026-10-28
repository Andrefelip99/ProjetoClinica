package projeto_clinica.com.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto_clinica.com.dto.Request.SinaisVitaisRequestDTO;
import projeto_clinica.com.dto.Response.SinaisVitaisResponseDTO;
import projeto_clinica.com.model.SinaisVitais;
import projeto_clinica.com.repository.SinaisVitaisRepository;
import projeto_clinica.com.repository.PacienteRepository;
import projeto_clinica.com.repository.FuncionarioRepository;
import projeto_clinica.com.service.exceptions.ResourceNotFoundException;

@Service
public class SinaisVitaisService {
    private final SinaisVitaisRepository repository;
    private final PacienteRepository pacientes;
    private final FuncionarioRepository funcionarios;

    public SinaisVitaisService(SinaisVitaisRepository repository, PacienteRepository pacientes, FuncionarioRepository funcionarios) {
        this.repository = repository;
        this.pacientes = pacientes;
        this.funcionarios = funcionarios;
    }

    @Transactional(readOnly = true)
    public List<SinaisVitaisResponseDTO> findAll() {
        return repository.findAll().stream().map(SinaisVitaisResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public SinaisVitaisResponseDTO findById(Long id) {
        return new SinaisVitaisResponseDTO(buscar(id));
    }

    @Transactional
    public SinaisVitaisResponseDTO create(SinaisVitaisRequestDTO dto) {
        SinaisVitais sinais = new SinaisVitais();
        copiar(dto, sinais);
        return new SinaisVitaisResponseDTO(repository.save(sinais));
    }

    @Transactional
    public SinaisVitaisResponseDTO update(Long id, SinaisVitaisRequestDTO dto) {
        SinaisVitais sinais = buscar(id);
        copiar(dto, sinais);
        return new SinaisVitaisResponseDTO(repository.save(sinais));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(buscar(id));
    }

    private SinaisVitais buscar(Long id) {
        return repository.findByIdWithAssociations(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sinais vitais não encontrados. ID: " + id));
    }

    private void copiar(SinaisVitaisRequestDTO dto, SinaisVitais s) {
        s.setPaciente(pacientes.findById(dto.pacienteId()).orElseThrow(
                () -> new ResourceNotFoundException("Paciente não encontrado. ID: " + dto.pacienteId())));
        s.setFuncionario(funcionarios.findById(dto.funcionarioId()).orElseThrow(
                () -> new ResourceNotFoundException("Funcionário não encontrado. ID: " + dto.funcionarioId())));
        s.setTemperatura(dto.temperatura());
        s.setPressaoArterial(dto.pressaoArterial());
        s.setFrequenciaCardiaca(dto.frequenciaCardiaca());
        s.setSaturacaoOxigenio(dto.saturacaoOxigenio());
        s.setObservacoes(dto.observacoes());
    }
}
