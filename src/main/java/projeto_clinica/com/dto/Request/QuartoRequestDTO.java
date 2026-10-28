package projeto_clinica.com.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import projeto_clinica.com.model.enums.TipoQuarto;

public record QuartoRequestDTO(
        @NotBlank
        @Size(max = 20)
        String numero,
        @PositiveOrZero
        int andar,
        @NotNull
        TipoQuarto tipo

) {

}
