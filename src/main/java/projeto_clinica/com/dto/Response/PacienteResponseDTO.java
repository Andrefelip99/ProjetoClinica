package projeto_clinica.com.dto.Response;

import java.time.LocalDate;

import projeto_clinica.com.model.Paciente;

public record PacienteResponseDTO(
        Long id,
        String nome,
        Integer idade,
        String cpf,
        LocalDate dataDeNascimento,
        String telefone,
        String endereco,
        String email,
        String tipoSanguineo) {
    public PacienteResponseDTO(Paciente entity) {
        this(
                entity.getId(),
                entity.getNome(),
                entity.getIdade(),
                entity.getCpf(),
                entity.getDataDeNascimento(),
                entity.getTelefone(),
                entity.getEndereco(),
                entity.getEmail(),
                entity.getTipoSanguineo());
    }
}