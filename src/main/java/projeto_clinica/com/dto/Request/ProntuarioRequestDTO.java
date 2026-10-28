package projeto_clinica.com.dto.Request;

import jakarta.validation.constraints.NotNull;

public record ProntuarioRequestDTO(
    @NotNull(message = "O ID do paciente é obrigatório")
    Long paciente
) {

}
