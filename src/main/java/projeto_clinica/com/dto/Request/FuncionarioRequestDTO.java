package projeto_clinica.com.dto.Request;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record FuncionarioRequestDTO(
        @NotBlank
        @Size(min = 3, max = 100)
        @Pattern (regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "O nome deve conter apenas letras e espaços")
        String nome,

        @NotBlank
        @Pattern(regexp = "\\d{11}$", message = "CPF deve conter 11 dígitos")
        String cpf,

        @NotBlank
        @Email(message = "Email inválido")
        String email,

        @NotBlank
        @Size(min = 8, max = 100)
        String senha,

        @NotEmpty 
        Set<Long> roleIds) {
}
