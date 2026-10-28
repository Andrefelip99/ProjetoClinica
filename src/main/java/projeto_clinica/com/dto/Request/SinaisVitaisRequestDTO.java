package projeto_clinica.com.dto.Request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record SinaisVitaisRequestDTO(

    @NotNull
    Long pacienteId,

    @NotNull
    Long funcionarioId,

    @NotNull
    @DecimalMin("25.0")
    @DecimalMax("45.0")
    BigDecimal temperatura,

    @NotBlank
    @Size(max = 20)
    String pressaoArterial,

    @NotNull
    @Positive
    Integer frequenciaCardiaca,

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("100.0")
    BigDecimal saturacaoOxigenio,

    @Size(max = 1000)
    String observacoes

) {
}

