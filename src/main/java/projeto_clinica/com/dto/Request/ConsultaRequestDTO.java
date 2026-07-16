package projeto_clinica.com.dto.Request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ConsultaRequestDTO(
        @NotNull LocalDateTime dataHora,
        @NotNull Long pacienteId,
        @NotNull Long medicoId,
        @NotNull Long enfermeiroId,
        @Size(max = 500) String observacao) {
}