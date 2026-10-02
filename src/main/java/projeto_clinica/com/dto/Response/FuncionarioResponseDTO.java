package projeto_clinica.com.dto.Response;

import java.util.Set;
import java.util.stream.Collectors;
import projeto_clinica.com.model.Funcionario;

public record FuncionarioResponseDTO(Long id, String nome, String cpf, String email,
        Set<String> roles, boolean ativo) {
    public FuncionarioResponseDTO(Funcionario funcionario) {
        this(funcionario.getId(), funcionario.getNome(), funcionario.getCpf(), funcionario.getEmail(),
                funcionario.getRoles().stream().map(role -> role.getAuthority()).collect(Collectors.toUnmodifiableSet()),
                funcionario.isAtivo());
    }
}
