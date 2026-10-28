package projeto_clinica.com.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto_clinica.com.dto.Request.ProntuarioRegistroRequestDTO;
import projeto_clinica.com.dto.Response.ProntuarioRegistroResponseDTO;
import projeto_clinica.com.model.ProntuarioRegistro;
import projeto_clinica.com.repository.ProntuarioRegistroRepository;
import projeto_clinica.com.repository.ProntuarioRepository;
import projeto_clinica.com.repository.FuncionarioRepository;
import projeto_clinica.com.service.exceptions.ResourceNotFoundException;

@Service
public class ProntuarioRegistroService {
    private final ProntuarioRegistroRepository repository;
    private final ProntuarioRepository prontuarios;
    private final FuncionarioRepository funcionarios;

    public ProntuarioRegistroService(ProntuarioRegistroRepository repository, ProntuarioRepository prontuarios,
            FuncionarioRepository funcionarios) {
        this.repository = repository;
        this.prontuarios = prontuarios;
        this.funcionarios = funcionarios;
    }

    @Transactional(readOnly = true)
    public List<ProntuarioRegistroResponseDTO> findAll() {
        return repository.findAll().stream().map(ProntuarioRegistroResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ProntuarioRegistroResponseDTO findById(Long id) {
        return new ProntuarioRegistroResponseDTO(buscar(id));
    }

    @Transactional
    public ProntuarioRegistroResponseDTO create(ProntuarioRegistroRequestDTO dto) {
        ProntuarioRegistro registro = new ProntuarioRegistro();
        copiar(dto, registro);
        return new ProntuarioRegistroResponseDTO(repository.save(registro));
    }

    @Transactional
    public ProntuarioRegistroResponseDTO update(Long id, ProntuarioRegistroRequestDTO dto) {
        ProntuarioRegistro registro = buscar(id);
        copiar(dto, registro);
        return new ProntuarioRegistroResponseDTO(repository.save(registro));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(buscar(id));
    }

    private ProntuarioRegistro buscar(Long id) {
        return repository.findByIdWithAssociations(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado. ID: " + id));
    }

    private void copiar(ProntuarioRegistroRequestDTO dto, ProntuarioRegistro registro) {
        registro.setProntuario(prontuarios.findByIdWithPaciente(dto.prontuarioId()).orElseThrow(
                () -> new ResourceNotFoundException("Prontuário não encontrado. ID: " + dto.prontuarioId())));
        registro.setFuncionario(funcionarios.findById(dto.funcionarioId()).orElseThrow(
                () -> new ResourceNotFoundException("Funcionário não encontrado. ID: " + dto.funcionarioId())));
        registro.setDescricao(dto.descricao());
        registro.setTipo(dto.tipo());
    }
}
