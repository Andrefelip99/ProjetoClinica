package projeto_clinica.com.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PrescricaoItemRequestDTO(

    @NotNull
    Long prescricaoId,

    @NotBlank
    @Size(max = 150)
    String medicamento,

    @NotBlank
    @Size(max = 100)
    String dosagem,

    @NotBlank
    @Size(max = 100)
    String frequencia,

    @NotBlank
    @Size(max = 100)
    String duracao,

    @Size(max = 1000)
    String observacoes

) {
}

