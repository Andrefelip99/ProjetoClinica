package projeto_clinica.com.dto.Request;

import jakarta.validation.constraints.NotNull;

public record CadastroInicialRequestDTO(
        @NotNull String nome,
        @NotNull String email,
        @NotNull String senha) {
}
