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

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import projeto_clinica.com.dto.Request.PacienteRequestDTO;
import projeto_clinica.com.dto.Response.ConsultaResponseDTO;
import projeto_clinica.com.dto.Response.PacienteResponseDTO;
import projeto_clinica.com.service.ConsultaService;
import projeto_clinica.com.service.PacienteService;

@AllArgsConstructor
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;
    private final ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> criarPaciente(
            @Valid @RequestBody PacienteRequestDTO dto) {

        PacienteResponseDTO response = pacienteService.criarPaciente(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listarPacientes() {
        return ResponseEntity.ok(
                pacienteService.listarPacientes()
                        .stream()
                        .map(PacienteResponseDTO::new)
                        .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> buscarPacientePorId(@PathVariable Long id) {
        return pacienteService.buscarPorId(id)
                .map(PacienteResponseDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> atualizarPaciente(
            @PathVariable Long id,
            @Valid @RequestBody PacienteRequestDTO dto) {

        PacienteResponseDTO response = pacienteService.atualizarPaciente(id, dto);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/consultas")
    public ResponseEntity<List<ConsultaResponseDTO>> historicoConsultas(@PathVariable Long id) {

        return pacienteService.buscarPorId(id)
                .map(paciente -> ResponseEntity.ok(
                        consultaService.listarConsultasPorPaciente(paciente.getId())))
                .orElse(ResponseEntity.notFound().build());
    }
}