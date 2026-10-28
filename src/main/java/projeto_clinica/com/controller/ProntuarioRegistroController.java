package projeto_clinica.com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;
import projeto_clinica.com.dto.Request.ProntuarioRegistroRequestDTO;
import projeto_clinica.com.dto.Response.ProntuarioRegistroResponseDTO;
import projeto_clinica.com.service.ProntuarioRegistroService;

@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'MEDICO', 'ENFERMEIRO', 'TECNICO_ENFERMAGEM')")
@RequestMapping("/prontuario-registros")
public class ProntuarioRegistroController {
    private final ProntuarioRegistroService service;

    public ProntuarioRegistroController(ProntuarioRegistroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProntuarioRegistroResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProntuarioRegistroResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProntuarioRegistroResponseDTO> create(
            @Valid @RequestBody ProntuarioRegistroRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProntuarioRegistroResponseDTO> update(
            @PathVariable Long id, @Valid @RequestBody ProntuarioRegistroRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
