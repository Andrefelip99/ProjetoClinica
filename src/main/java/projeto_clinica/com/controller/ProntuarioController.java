package projeto_clinica.com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;
import projeto_clinica.com.dto.Request.ProntuarioRequestDTO;
import projeto_clinica.com.dto.Response.ProntuarioResponseDTO;
import projeto_clinica.com.service.ProntuarioService;

@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'MEDICO', 'ENFERMEIRO', 'TECNICO_ENFERMAGEM')")
@RequestMapping("/prontuarios")
public class ProntuarioController {
    private final ProntuarioService service;

    public ProntuarioController(ProntuarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProntuarioResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProntuarioResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProntuarioResponseDTO> create(@Valid @RequestBody ProntuarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProntuarioResponseDTO> update(
            @PathVariable Long id, @Valid @RequestBody ProntuarioRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
