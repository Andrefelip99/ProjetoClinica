package projeto_clinica.com.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import projeto_clinica.com.repository.*;
import projeto_clinica.com.service.exceptions.ResourceNotFoundException;

@ExtendWith(MockitoExtension.class)
class ServiceExceptionTest {
    @Mock
    QuartoRepository quartos;
    @Mock
    RoleRepository roles;
    @Mock
    LeitoRepository leitos;
    @Mock
    InternacaoRepository internacoes;
    @Mock
    ProntuarioRepository prontuarios;
    @Mock
    ProntuarioRegistroRepository registros;
    @Mock
    SinaisVitaisRepository sinais;
    @Mock
    PrescricaoRepository prescricoes;
    @Mock
    PrescricaoItemRepository itens;
    @Mock
    AdministracaoMedicamentoRepository administracoes;
    @Mock
    PacienteRepository pacientes;
    @Mock
    FuncionarioRepository funcionarios;

    @Test
    void quartoNotFound() {
        when(quartos.findById(10L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> new QuartoService(quartos).findById(10L));
    }

    @Test
    void roleNotFound() {
        when(roles.findById(10L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> new RoleService(roles).findById(10L));
    }

    @Test
    void leitoNotFound() {
        when(leitos.findByIdWithQuarto(10L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> new LeitoService(leitos, quartos).findById(10L));
    }

    @Test
    void internacaoNotFound() {
        when(internacoes.findByIdWithAssociations(10L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> new InternacaoService(internacoes, pacientes, leitos).findById(10L));
    }

    @Test
    void prontuarioNotFound() {
        when(prontuarios.findByIdWithPaciente(10L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> new ProntuarioService(prontuarios, pacientes).findById(10L));
    }

    @Test
    void prontuarioRegistroNotFound() {
        when(registros.findByIdWithAssociations(10L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> new ProntuarioRegistroService(registros, prontuarios, funcionarios).findById(10L));
    }

    @Test
    void sinaisVitaisNotFound() {
        when(sinais.findByIdWithAssociations(10L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> new SinaisVitaisService(sinais, pacientes, funcionarios).findById(10L));
    }

    @Test
    void prescricaoNotFound() {
        when(prescricoes.findByIdWithAssociations(10L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> new PrescricaoService(prescricoes, pacientes, funcionarios).findById(10L));
    }

    @Test
    void prescricaoItemNotFound() {
        when(itens.findByIdWithPrescricao(10L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> new PrescricaoItemService(itens, prescricoes).findById(10L));
    }

    @Test
    void administracaoMedicamentoNotFound() {
        when(administracoes.findById(10L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> new AdministracaoMedicamentoService(administracoes, funcionarios, itens).findById(10L));
    }

    @Test
    void funcionarioDetailsMissingProducesUsernameNotFound() {
        when(funcionarios.findByEmail("missing@example.com")).thenReturn(Optional.empty());
        org.junit.jupiter.api.Assertions.assertThrows(
                org.springframework.security.core.userdetails.UsernameNotFoundException.class,
                () -> new FuncionarioUserDetailsService(funcionarios).loadUserByUsername("missing@example.com"));
    }
}
