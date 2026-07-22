package projeto_clinica.com.dto.Request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PacienteRequestDTO(
        @NotBlank(message = "O nome é obrigatório") @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres") String nome,
        @NotBlank(message = "O CPF é obrigatório") @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos") String cpf,
        @NotNull(message = "A data de nascimento é obrigatória") @Past(message = "Data de nascimento deve estar no passado") LocalDate dataDeNascimento,
        @NotBlank(message = "O telefone é obrigatório") @Pattern(regexp = "^\\d{10,11}$", message = "Telefone deve conter 10 ou 11 dígitos") String telefone,
        @NotBlank(message = "O endereço é obrigatório") @Size(min = 8, max = 100, message = "O endereço deve ter entre 8 e 100 caracteres") String endereco) {

}