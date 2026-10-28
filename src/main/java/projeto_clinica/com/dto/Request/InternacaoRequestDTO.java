package projeto_clinica.com.dto.Request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import projeto_clinica.com.model.enums.StatusInternacao;

public record InternacaoRequestDTO(
        @NotNull(message = "O ID do paciente e obrigatorio")
        Long pacienteId,
        @NotNull(message = "O ID do leito e obrigatorio")
        Long leitoId,
        @NotBlank(message = "O motivo da internacao e obrigatorio")
        @Size(max = 1000, message = "O motivo nao pode exceder 1000 caracteres")
        String motivo,
        LocalDateTime dataAlta,
        @NotNull (message = "O status da internacao e obrigatorio")
        StatusInternacao status
) {

}
