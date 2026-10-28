package projeto_clinica.com.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import projeto_clinica.com.dto.Request.CadastroInicialRequestDTO;
import projeto_clinica.com.dto.Request.FuncionarioRequestDTO;
import projeto_clinica.com.dto.Request.PacienteRequestDTO;
import projeto_clinica.com.model.CadastroInicial;
import projeto_clinica.com.model.Funcionario;
import projeto_clinica.com.model.Paciente;
import projeto_clinica.com.repository.CadastroInicialRepository;
import projeto_clinica.com.repository.FuncionarioRepository;
import projeto_clinica.com.repository.PacienteRepository;
import projeto_clinica.com.repository.RoleRepository;
import projeto_clinica.com.service.exceptions.ConflictException;
import projeto_clinica.com.service.exceptions.UnauthorizedException;

@ExtendWith(MockitoExtension.class)
class CadastroFuncionarioPacienteServiceTest {
    @Mock
    CadastroInicialRepository cadastros;
    @Mock
    FuncionarioRepository funcionarios;
    @Mock
    RoleRepository roles;
    @Mock
    PacienteRepository pacientes;
    @Mock
    PasswordEncoder encoder;

    @Test
    void cadastroDuplicadoProducesConflict() {
        when(cadastros.findByEmail("a@b.com")).thenReturn(Optional.of(new CadastroInicial()));
        var service = new CadastroInicialService(cadastros, encoder);
        assertThrows(ConflictException.class,
                () -> service.cadastrar(new CadastroInicialRequestDTO("Ana", "a@b.com", "senha")));
    }

    @Test
    void loginInvalidoProducesUnauthorized() {
        CadastroInicial usuario = new CadastroInicial();
        usuario.setEmail("a@b.com");
        usuario.setSenha("hash");
        when(cadastros.findByEmail("a@b.com")).thenReturn(Optional.of(usuario));
        assertThrows(UnauthorizedException.class,
                () -> new CadastroInicialService(cadastros, encoder).login("a@b.com", "errada"));
    }

    @Test
    void loginValidoReturnsTrue() {
        CadastroInicial usuario = new CadastroInicial();
        usuario.setEmail("a@b.com");
        usuario.setSenha("encoded");
        when(cadastros.findByEmail("a@b.com")).thenReturn(Optional.of(usuario));
        when(encoder.matches("senha", "encoded")).thenReturn(true);
        assertTrue(new CadastroInicialService(cadastros, encoder).login("a@b.com", "senha"));
    }

    @Test
    void cadastroFuncionarioComEmailDuplicadoProducesConflict() {
        when(funcionarios.findByEmail("a@b.com")).thenReturn(Optional.of(new Funcionario()));
        var service = new FuncionarioService(funcionarios, roles, encoder);
        var dto = new FuncionarioRequestDTO("Ana", "12345678901", "a@b.com", "senha1234", Set.of());
        assertThrows(ConflictException.class, () -> service.criarFuncionario(dto));
    }

    @Test
    void criarPacientePersisteEConverteParaResponse() {
        Paciente paciente = new Paciente();
        paciente.setId(5L);
        when(pacientes.save(any(Paciente.class))).thenReturn(paciente);
        var dto = new PacienteRequestDTO("Ana", "12345678901", LocalDate.of(1990, 1, 1), "11999999999",
                "Rua A", "ana@example.com", "O+");
        var response = new PacienteService(pacientes).criarPaciente(dto);
        assertEquals(5L, response.id());
    }
}
