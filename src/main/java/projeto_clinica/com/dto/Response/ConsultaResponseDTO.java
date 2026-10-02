package projeto_clinica.com.dto.Response;

import java.time.LocalDateTime;

import projeto_clinica.com.model.Consulta;
import projeto_clinica.com.model.enums.StatusConsulta;

public record ConsultaResponseDTO(
        Long id,
        LocalDateTime dataHora,
        Long pacienteId,
        Long medicoId,
        String observacao,
        StatusConsulta status) {

    public ConsultaResponseDTO(Consulta entity) {
        this(
                entity.getId(),
                entity.getDataHora(),
                entity.getPaciente() != null ? entity.getPaciente().getId() : null,
                entity.getMedico() != null ? entity.getMedico().getId() : null,
                entity.getObservacao(),
                entity.getStatus());
    }
}
