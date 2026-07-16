package projeto_clinica.com.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import projeto_clinica.com.model.Perfil;

public record FuncionarioRequestDTO(
        @NotNull @Size(min = 6, max = 20) String nome,
        @NotBlank Perfil perfil,
        @NotNull Boolean ativo) {

}