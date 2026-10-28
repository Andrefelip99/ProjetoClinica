package projeto_clinica.com.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import projeto_clinica.com.model.enums.TipoRegistroProntuario;

public record ProntuarioRegistroRequestDTO(

    @NotNull
    Long prontuarioId,

    @NotNull
    Long funcionarioId,

    @NotBlank
    @Size(max = 4000)
    String descricao,

    @NotNull
    TipoRegistroProntuario tipo

) {
}

