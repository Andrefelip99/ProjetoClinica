package projeto_clinica.com.dto.Response;

import java.time.LocalDateTime;

import projeto_clinica.com.model.Prescricao;
import projeto_clinica.com.model.enums.StatusPrescricao;

public record PrescricaoResponseDTO(
    Long id,
    Long pacienteId,
    Long medicoId,
    LocalDateTime dataHora,
    String observacoes,
    StatusPrescricao status
) {

    public PrescricaoResponseDTO(Prescricao entity) {
        this(
            entity.getId(),
            entity.getPaciente().getId(),
            entity.getMedico().getId(),
            entity.getDataHora(),
            entity.getObservacoes(),
            entity.getStatus()
        );
    }
}

