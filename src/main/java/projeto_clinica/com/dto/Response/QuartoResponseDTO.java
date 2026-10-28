package projeto_clinica.com.dto.Response;

import projeto_clinica.com.model.Quarto;
import projeto_clinica.com.model.enums.TipoQuarto;

public record QuartoResponseDTO(
        Long id,
        String numero,
        int andar,
        TipoQuarto tipo) {

    public QuartoResponseDTO(Quarto entity) {
        this(entity.getId(),
                entity.getNumero(),
                entity.getAndar(),
                entity.getTipo());

    }
}
