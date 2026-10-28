package projeto_clinica.com.dto.Request;

import java.time.LocalDateTime;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record RemarcarConsultaRequestDTO(
        @NotNull @Future LocalDateTime dataHora) {
}
