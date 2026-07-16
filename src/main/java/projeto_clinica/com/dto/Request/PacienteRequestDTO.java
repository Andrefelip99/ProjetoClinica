package projeto_clinica.com.dto.Request;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PacienteRequestDTO(
        @NotNull @Size(min = 3, max = 50) String nome,
        @NotNull @NotBlank @Min(0) @Max(150) Integer idade,
        @NotNull @NotBlank @Size(min = 11, max = 11, message = "CPF deve conter 11 dígitos") String cpf,
        @NotBlank @NotNull @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Data de nascimento deve estar no formato yyyy-MM-dd") @NotNull LocalDate dataDeNascimento,
        @NotNull @NotBlank @Pattern(regexp = "^\\d{10,11}$", message = "Telefone deve conter 10 ou 11 dígitos") String telefone,
        @NotNull @NotBlank @Size(min = 8, max = 100) String endereco) {

}