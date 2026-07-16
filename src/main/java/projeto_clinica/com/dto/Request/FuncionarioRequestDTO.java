package projeto_clinica.com.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import projeto_clinica.com.model.Perfil;

public record FuncionarioRequestDTO(
                @NotBlank(message = "O nome é obrigatório") @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres") String nome,
                @NotNull(message = "O perfil é obrigatório") Perfil perfil,
                @NotNull(message = "O status é obrigatório") Boolean ativo) {

}