package projeto_clinica.com.service;

import org.springframework.stereotype.Service;

import projeto_clinica.com.model.Funcionario;
import projeto_clinica.com.repository.FuncionarioRepository;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repo;

    public FuncionarioService(FuncionarioRepository repo) {
        this.repo = repo;
    }

    public Funcionario criar(Funcionario funcionario) {
        return repo.save(funcionario);
    }

    public Funcionario buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    public List<Funcionario> listar() {
        return repo.findAll();
    }

    public Funcionario save(Funcionario funcionario) {
        return repo.save(funcionario);
    }

    public void desativar(Long id) {
        Funcionario f = buscarPorId(id);
        f.setAtivo(false);
        repo.save(f);
    }
}
