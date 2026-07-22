package projeto_clinica.com.config;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import projeto_clinica.com.model.CadastroInicial;
import projeto_clinica.com.model.Consulta;
import projeto_clinica.com.model.Funcionario;
import projeto_clinica.com.model.Perfil;
import projeto_clinica.com.model.Paciente;
import projeto_clinica.com.model.StatusConsulta;
import projeto_clinica.com.repository.CadastroInicialRepository;
import projeto_clinica.com.repository.ConsultaRepository;
import projeto_clinica.com.repository.FuncionarioRepository;
import projeto_clinica.com.repository.PacienteRepository;

@Configuration
public class DataSeeder {

    @Bean
    @Transactional
    CommandLineRunner seedData(
            CadastroInicialRepository cadastroInicialRepository,
            FuncionarioRepository funcionarioRepository,
            PacienteRepository pacienteRepository,
            ConsultaRepository consultaRepository) {

        return args -> {
            if (cadastroInicialRepository.count() == 0) {
                cadastroInicialRepository.save(new CadastroInicial(
                        0L,
                        "Administrador",
                        "admin@projetosaude.local",
                        "admin123"));
            }

            if (funcionarioRepository.count() == 0) {
                funcionarioRepository.save(new Funcionario(null, "Administrador", Perfil.ADMINISTRADOR, true));
                funcionarioRepository.save(new Funcionario(null, "Enfermeiro Teste", Perfil.ENFERMEIRO, true));
                funcionarioRepository.save(new Funcionario(null, "Medico Teste", Perfil.MEDICO, true));
            }

            if (pacienteRepository.count() == 0) {
                pacienteRepository.save(new Paciente(
                        null,
                        "Paciente Exemplo",
                        "12345678901",
                        LocalDate.now().minusYears(30),
                        "11999999999",
                        "Endereco Exemplo",
                        null));
            }

            if (consultaRepository.count() == 0) {
                Paciente paciente = pacienteRepository.findAll().stream().findFirst().orElse(null);
                Funcionario enfermeiro = funcionarioRepository.findAll().stream()
                        .filter(funcionario -> funcionario.getPerfil() == Perfil.ENFERMEIRO)
                        .findFirst()
                        .orElse(null);
                Funcionario medico = funcionarioRepository.findAll().stream()
                        .filter(funcionario -> funcionario.getPerfil() == Perfil.MEDICO)
                        .findFirst()
                        .orElse(null);

                if (paciente != null && enfermeiro != null && medico != null) {
                    consultaRepository.save(new Consulta(
                            null,
                            LocalDateTime.now().plusDays(1),
                            paciente,
                            medico.getId(),
                            enfermeiro.getId(),
                            "Consulta inicial de exemplo",
                            StatusConsulta.AGENDADA));
                }
            }
        };
    }
}
