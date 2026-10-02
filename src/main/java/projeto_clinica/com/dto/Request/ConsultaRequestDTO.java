package projeto_clinica.com.dto.Request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ConsultaRequestDTO(
        
        @NotNull(message = "A data da consulta e obrigatoria")
        @Future(message = "A consulta deve ser uma data futura")
        LocalDateTime dataHora,

        @NotNull(message = "O ID do paciente e obrigatorio")
        Long pacienteId,

        @NotNull(message = "O ID do medico e obrigatorio")
        Long medicoId,

        @Size(max = 500, message = "A observacao nao pode exceder 500 caracteres")
        String observacao) {
}
