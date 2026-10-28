package projeto_clinica.com.dto.Response;

import java.time.LocalDateTime;

import projeto_clinica.com.model.Prontuario;

public record ProntuarioResponseDTO(
    Long id,
    Long paciente,
    LocalDateTime dataCriacao
) {

    public ProntuarioResponseDTO(Prontuario entity){
        this(
            entity.getId(),
            entity.getPaciente() != null ? entity.getPaciente().getId() : null,
            entity.getDataCriacao()

        );
    }

}
