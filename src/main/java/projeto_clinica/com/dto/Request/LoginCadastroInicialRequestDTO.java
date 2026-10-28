package projeto_clinica.com.dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginCadastroInicialRequestDTO(
        @NotBlank @Email String email,
        @NotBlank String senha) {
}
