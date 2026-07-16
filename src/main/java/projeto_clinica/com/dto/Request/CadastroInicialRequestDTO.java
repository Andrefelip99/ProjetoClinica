package projeto_clinica.com.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroInicialRequestDTO(
                @NotNull @NotBlank String nome,
                @NotNull @NotBlank String email,
                @NotNull @NotBlank String senha) {
}
