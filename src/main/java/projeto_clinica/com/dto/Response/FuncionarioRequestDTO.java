package projeto_clinica.com.dto.Response;

import projeto_clinica.com.model.Funcionario;
import projeto_clinica.com.model.Perfil;

public record FuncionarioRequestDTO(
        Long id,
        String nome,
        Perfil perfil,
        Boolean ativo) {
    public FuncionarioRequestDTO(Funcionario entity) {
        this(
                entity.getId(),
                entity.getNome(),
                entity.getPerfil(),
                entity.getAtivo());
    }
}