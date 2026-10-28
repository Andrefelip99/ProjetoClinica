package projeto_clinica.com.dto.Response;

import projeto_clinica.com.model.Leito;
import projeto_clinica.com.model.enums.StatusLeito;

public record LeitoResponseDTO(
    Long id,
    String numero,
    Long quarto,
    StatusLeito status


) {

    public LeitoResponseDTO(Leito entity){
        this(
            entity.getId(),
            entity.getNumero(),
            entity.getQuarto().getId(),
            entity.getStatus()

        );
    }

}
