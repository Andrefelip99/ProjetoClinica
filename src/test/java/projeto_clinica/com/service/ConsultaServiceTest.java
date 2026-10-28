package projeto_clinica.com.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import projeto_clinica.com.dto.Request.ConsultaRequestDTO;
import projeto_clinica.com.model.Funcionario;
import projeto_clinica.com.model.Paciente;
import projeto_clinica.com.model.Role;
import projeto_clinica.com.model.enums.StatusConsulta;
import projeto_clinica.com.repository.ConsultaRepository;
import projeto_clinica.com.repository.FuncionarioRepository;
import projeto_clinica.com.service.exceptions.BusinessRuleException;
import projeto_clinica.com.service.exceptions.ConflictException;
import projeto_clinica.com.service.exceptions.ResourceNotFoundException;

@ExtendWith(MockitoExtension.class)
class ConsultaServiceTest {
    @Mock
    ConsultaRepository consultas;
    @Mock
    PacienteService pacientes;
    @Mock
    FuncionarioRepository funcionarios;

    private ConsultaService service() {
        return new ConsultaService(consultas, pacientes, funcionarios);
    }

    @Test
    void pacienteAusenteProducesNotFound() {
        doThrow(new ResourceNotFoundException("Paciente não encontrado"))
                .when(pacientes).buscarPorId(1L);
        assertThrows(ResourceNotFoundException.class,
                () -> service().marcarConsulta(request(LocalDateTime.now().plusDays(1))));
    }

    @Test
    void horarioNoFuturoProducesBusinessError() {
        when(pacientes.buscarPorId(1L)).thenReturn(mock(Paciente.class));
        Funcionario doctor = funcionarioWithRole();
        when(funcionarios.findByIdWithRoles(2L)).thenReturn(Optional.of(doctor));
        assertThrows(BusinessRuleException.class,
                () -> service().marcarConsulta(request(LocalDateTime.now().minusMinutes(1))));
    }

    @Test
    void funcionarioSemRoleMedicoProducesBusinessError() {
        when(pacientes.buscarPorId(1L)).thenReturn(mock(Paciente.class));
        Funcionario funcionario = mock(Funcionario.class);
        when(funcionario.getRoles()).thenReturn(Set.of());
        when(funcionarios.findByIdWithRoles(2L)).thenReturn(Optional.of(funcionario));
        assertThrows(BusinessRuleException.class,
                () -> service().marcarConsulta(request(LocalDateTime.now().plusDays(1))));
    }

    @Test
    void horarioOcupadoProducesConflict() {
        when(pacientes.buscarPorId(1L)).thenReturn(mock(Paciente.class));
        Funcionario doctor = funcionarioWithRole();
        when(funcionarios.findByIdWithRoles(2L)).thenReturn(Optional.of(doctor));
        when(consultas.existsByMedico_IdAndDataHoraAndStatusNot(eq(2L), any(), eq(StatusConsulta.CANCELADA)))
                .thenReturn(true);
        assertThrows(ConflictException.class, () -> service().marcarConsulta(request(LocalDateTime.now().plusDays(1))));
    }

    private ConsultaRequestDTO request(LocalDateTime when) {
        return new ConsultaRequestDTO(when, 1L, 2L, null);
    }

    private Funcionario funcionarioWithRole() {
        Funcionario funcionario = mock(Funcionario.class);
        Role role = new Role(1L, "ROLE_MEDICO");
        when(funcionario.getId()).thenReturn(2L);
        when(funcionario.getRoles()).thenReturn(Set.of(role));
        return funcionario;
    }
}
