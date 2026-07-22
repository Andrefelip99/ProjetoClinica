package projeto_clinica.com.service;

import org.springframework.stereotype.Service;

import projeto_clinica.com.dto.Request.FuncionarioRequestDTO;
import projeto_clinica.com.dto.Response.FuncionarioResponseDTO;
import projeto_clinica.com.model.Funcionario;
import projeto_clinica.com.repository.FuncionarioRepository;
import projeto_clinica.com.service.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repo;

    public FuncionarioService(FuncionarioRepository repo) {
        this.repo = repo;
    }

    public FuncionarioResponseDTO criarFuncionario(FuncionarioRequestDTO dto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.nome());
        funcionario.setPerfil(dto.perfil());
        funcionario.setAtivo(dto.ativo());

        funcionario = repo.save(funcionario);
        return new FuncionarioResponseDTO(funcionario);
    }

    public FuncionarioResponseDTO buscarFuncionarioPorId(Long id) {
        return new FuncionarioResponseDTO(repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado")));
    }

    public List<FuncionarioResponseDTO> listarFuncionarios() {
        return repo.findAll().stream().map(FuncionarioResponseDTO::new).toList();
    }

    public Funcionario save(Funcionario funcionario) {
        return repo.save(funcionario);
    }

    public void desativar(Long id) {
        Funcionario f = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado"));
        f.setAtivo(false);
        repo.save(f);
    }
}
