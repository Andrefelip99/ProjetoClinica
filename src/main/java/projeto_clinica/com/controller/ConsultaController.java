package projeto_clinica.com.controller;

import java.util.List;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projeto_clinica.com.dto.Request.ConsultaRequestDTO;
import projeto_clinica.com.dto.Response.ConsultaResponseDTO;
import projeto_clinica.com.service.ConsultaService;

@AllArgsConstructor
@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> marcarConsulta(
            @Valid @RequestBody ConsultaRequestDTO dto) {
        ConsultaResponseDTO novaConsulta = consultaService.marcarConsulta(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConsulta);
    }

    @PutMapping("/{id}/remarcar")
    public ResponseEntity<ConsultaResponseDTO> remarcarConsulta(
            @PathVariable Long id,
            @Valid @RequestBody ConsultaRequestDTO dto) {

        ConsultaResponseDTO atualizada = consultaService.remarcarConsulta(id, dto.dataHora());

        if (atualizada == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(atualizada);
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<ConsultaResponseDTO> cancelarConsulta(@PathVariable Long id) {
        ConsultaResponseDTO cancelada = consultaService.cancelarConsulta(id);
        if (cancelada == null) {
            return ResponseEntity.notFound().build();
        }
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
