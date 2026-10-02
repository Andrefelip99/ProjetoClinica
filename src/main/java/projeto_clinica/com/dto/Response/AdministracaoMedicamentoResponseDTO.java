package projeto_clinica.com.dto.Response;

import java.time.LocalDateTime;

import projeto_clinica.com.model.AdministracaoMedicamento;
import projeto_clinica.com.model.enums.StatusAdministracao;

public record AdministracaoMedicamentoResponseDTO(
        Long id,
        Long prescricaoItem,
        Long funcionario,
        String observacao,
        LocalDateTime dataHora,
        StatusAdministracao status
) {

    public AdministracaoMedicamentoResponseDTO(AdministracaoMedicamento entity) {
        this(
                entity.getId(),
                entity.getPrescricaoItem() != null ? entity.getPrescricaoItem().getId() : null,
                entity.getFuncionario() != null ? entity.getFuncionario().getId() : null,
                entity.getObservacao(),
                entity.getDataHora(),
                entity.getStatus());
    }
}
