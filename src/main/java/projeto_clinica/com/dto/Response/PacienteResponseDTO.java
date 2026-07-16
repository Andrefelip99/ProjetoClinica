package projeto_clinica.com.dto.Response;

import java.time.LocalDate;

import projeto_clinica.com.model.Paciente;

public record PacienteResponseDTO(
        long id,
        String nome,
        Integer idade,
        String cpf,
        LocalDate dataDeNascimento,
        String telefone,
        String endereco) {
    public PacienteResponseDTO(Paciente entity) {
        this(
                entity.getId(),
                entity.getNome(),
                entity.getIdade(),
                entity.getCpf(),
                entity.getDataDeNascimento(),
                entity.getTelefone(),
                entity.getEndereco());
    }
}