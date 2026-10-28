package projeto_clinica.com.dto.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PrescricaoRequestDTO(

    @NotNull
    Long pacienteId,

    @NotNull
    Long medicoId,

    @Size(max = 2000)
    String observacoes

) {
}

