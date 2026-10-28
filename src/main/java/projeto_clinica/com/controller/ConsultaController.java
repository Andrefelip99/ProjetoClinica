package projeto_clinica.com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;
import projeto_clinica.com.dto.Request.ConsultaRequestDTO;
import projeto_clinica.com.dto.Request.RemarcarConsultaRequestDTO;
import projeto_clinica.com.dto.Response.ConsultaResponseDTO;
import projeto_clinica.com.service.ConsultaService;

@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'MEDICO')")
@RequestMapping("/consultas")
public class ConsultaController {
    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> marcarConsulta(
            @Valid @RequestBody ConsultaRequestDTO dto) {
        ConsultaResponseDTO novaConsulta = consultaService.marcarConsulta(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConsulta);
    }

    @PutMapping("/{id}/remarcar")
    public ResponseEntity<ConsultaResponseDTO> remarcarConsulta(
            @PathVariable Long id,
            @Valid @RequestBody RemarcarConsultaRequestDTO dto) {

        ConsultaResponseDTO atualizada = consultaService.remarcarConsulta(id, dto.dataHora());

        return ResponseEntity.ok(atualizada);
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<ConsultaResponseDTO> cancelarConsulta(@PathVariable Long id) {
        ConsultaResponseDTO cancelada = consultaService.cancelarConsulta(id);
        return ResponseEntity.ok(cancelada);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ConsultaResponseDTO>> listarConsultasPorPaciente(
            @PathVariable Long pacienteId) {
        return ResponseEntity.ok(consultaService.listarConsultasPorPaciente(pacienteId));
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<ConsultaResponseDTO>> listarConsultasPorMedico(
            @PathVariable Long medicoId) {
        return ResponseEntity.ok(consultaService.listarConsultasPorMedico(medicoId));
    }
}
