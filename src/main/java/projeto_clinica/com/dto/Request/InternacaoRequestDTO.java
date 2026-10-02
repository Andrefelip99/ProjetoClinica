package projeto_clinica.com.dto.Request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import projeto_clinica.com.model.enums.StatusInternacao;

public record InternacaoRequestDTO(
        @NotNull(message = "O ID do paciente e obrigatorio")
        Long pacienteId,
        @NotNull(message = "O ID do leito e obrigatorio")
        Long leitoId,
        @NotNull(message = "O motivo da internacao e obrigatorio")
        String motivo,
        @NotBlank (message = "A data de alta e obrigatoria")
        LocalDateTime dataAlta,
        @NotBlank (message = "A data de entrada e obrigatoria")
        LocalDateTime dataEntrada,
        @NotNull (message = "O status da internacao e obrigatorio")
        StatusInternacao status
) {

}
