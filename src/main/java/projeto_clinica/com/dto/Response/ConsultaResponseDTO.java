package projeto_clinica.com.dto.Response;

import java.time.LocalDateTime;
import projeto_clinica.com.model.Consulta;
import projeto_clinica.com.model.StatusConsulta;

public record ConsultaResponseDTO(

        Long id,
        LocalDateTime dataHora,
        Long pacienteId,
        Long medicoId,
        Long enfermeiroId,
        String observacao,
        StatusConsulta status

) {

    public ConsultaResponseDTO(Consulta entity) {
        this(
                entity.getId(),
                entity.getDataHora(),
                entity.getPaciente().getId(),
                entity.getMedicoId(),
                entity.getEnfermeiroId(),
                entity.getObservacao(),
                entity.getStatus());
    }
}