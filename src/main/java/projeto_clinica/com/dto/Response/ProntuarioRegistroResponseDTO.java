package projeto_clinica.com.dto.Response;

import java.time.LocalDateTime;

import projeto_clinica.com.model.ProntuarioRegistro;
import projeto_clinica.com.model.enums.TipoRegistroProntuario;

public record ProntuarioRegistroResponseDTO(
    Long id,
    Long prontuarioId,
    Long funcionarioId,
    LocalDateTime dataHora,
    String descricao,
    TipoRegistroProntuario tipo
) {

    public ProntuarioRegistroResponseDTO(ProntuarioRegistro entity) {
        this(
            entity.getId(),
            entity.getProntuario().getId(),
            entity.getFuncionario().getId(),
            entity.getDataHora(),
            entity.getDescricao(),
            entity.getTipo()
        );
    }
}

