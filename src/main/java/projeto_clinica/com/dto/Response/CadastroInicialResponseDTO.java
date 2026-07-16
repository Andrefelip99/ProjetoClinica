package projeto_clinica.com.dto.Response;

import projeto_clinica.com.model.CadastroInicial;

public record CadastroInicialResponseDTO(
        Long id,
        String nome,
        String email,
        String senha

) {

    public CadastroInicialResponseDTO(CadastroInicial entity) {
        this(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getSenha());
    }
}
