package projeto_clinica.com.dto.Request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ConsultaRequestDTO(
                @NotNull(message = "A data da consulta é obrigatória") @Future(message = "A consulta deve ser uma data futura") LocalDateTime dataHora,

                @NotNull(message = "O ID do paciente é obrigatório") Long pacienteId,

                @NotNull(message = "O ID do médico é obrigatório") Long medicoId,

                @NotNull(message = "O ID do enfermeiro é obrigatório") Long enfermeiroId,

                @Size(max = 500, message = "A observação não pode exceder 500 caracteres") String observacao) {
}