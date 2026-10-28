package projeto_clinica.com.dto.Response;

import projeto_clinica.com.model.PrescricaoItem;

public record PrescricaoItemResponseDTO(
    Long id,
    Long prescricaoId,
    String medicamento,
    String dosagem,
    String frequencia,
    String duracao,
    String observacoes
) {

    public PrescricaoItemResponseDTO(PrescricaoItem entity) {
        this(
            entity.getId(),
            entity.getPrescricao().getId(),
            entity.getMedicamento(),
            entity.getDosagem(),
            entity.getFrequencia(),
            entity.getDuracao(),
            entity.getObservacoes()
        );
    }
}

