package projeto_clinica.com.dto.Request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import projeto_clinica.com.model.enums.StatusAdministracao;

public record AdministracaoMedicamentoRequestDTO(
        @NotNull(message = "O ID da prescricao e obrigatorio")
        Long prescricaoItem,
        @NotNull(message = "O ID do funcionario e obrigatorio")
        Long funcionario,
        String observacao,
        @NotNull (message = "A data e hora da administracao e obrigatoria")
        LocalDateTime dataHora,
        @NotNull (message = "O status da administracao e obrigatorio")
        StatusAdministracao status
        

) {
}
