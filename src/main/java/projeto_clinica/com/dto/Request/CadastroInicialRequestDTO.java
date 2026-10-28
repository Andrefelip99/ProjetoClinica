package projeto_clinica.com.dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CadastroInicialRequestDTO(

    
                @NotBlank
                @Pattern (regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "O nome deve conter apenas letras e espaços")
                String nome,
            
                @NotBlank
                @Email(message = "Email inválido")
                String email,
                
                @NotBlank
                String senha) {
}
