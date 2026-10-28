package projeto_clinica.com.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import projeto_clinica.com.model.enums.StatusLeito;


public record LeitoRequestDTO(
    @NotBlank(message = "O campo não pode ficar em branco")
    @Size(max = 20, message = "O número do leito não pode exceder 20 caracteres")
    String numero,
    @NotNull(message = "O ID do quarto é obrigatório")
    Long quarto,
    @NotNull(message = "O status do leito é obrigatório")
    StatusLeito status
) {

}
