package projeto_clinica.com.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto_clinica.com.dto.Request.FuncionarioRequestDTO;
import projeto_clinica.com.dto.Response.FuncionarioResponseDTO;
import projeto_clinica.com.model.Funcionario;
import projeto_clinica.com.model.Role;
import projeto_clinica.com.repository.FuncionarioRepository;
import projeto_clinica.com.repository.RoleRepository;
import projeto_clinica.com.service.exceptions.ResourceNotFoundException;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarios;
    private final RoleRepository roles;
    private final PasswordEncoder passwordEncoder;

    public FuncionarioService(FuncionarioRepository funcionarios, RoleRepository roles, PasswordEncoder passwordEncoder) {
        this.funcionarios = funcionarios;
        this.roles = roles;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public FuncionarioResponseDTO criarFuncionario(FuncionarioRequestDTO dto) {
        if (funcionarios.findByEmail(dto.email()).isPresent())
            throw new IllegalArgumentException("Email ja cadastrado");
        if (funcionarios.existsByCpf(dto.cpf()))
            throw new IllegalArgumentException("CPF ja cadastrado");
        List<Role> assigned = roles.findByIdIn(dto.roleIds());
        if (assigned.size() != dto.roleIds().size())
            throw new ResourceNotFoundException("Uma ou mais roles nao foram encontradas");
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.nome());
        funcionario.setCpf(dto.cpf());
        funcionario.setEmail(dto.email());
        funcionario.setSenha(passwordEncoder.encode(dto.senha()));
        funcionario.setRoles(Set.copyOf(assigned));
        return new FuncionarioResponseDTO(funcionarios.save(funcionario));
    }

    @Transactional(readOnly = true)
    public FuncionarioResponseDTO buscarFuncionarioPorId(Long id) {
        return new FuncionarioResponseDTO(funcionarios.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario nao encontrado")));
    }

    @Transactional(readOnly = true)
    public List<FuncionarioResponseDTO> listarFuncionarios() {
        return funcionarios.findAll().stream().map(FuncionarioResponseDTO::new).toList();
    }

    @Transactional
    public void desativar(Long id) {
        Funcionario f = funcionarios.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario nao encontrado"));
        f.setAtivo(false);
    }
}
