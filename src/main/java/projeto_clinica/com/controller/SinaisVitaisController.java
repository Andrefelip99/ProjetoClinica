package projeto_clinica.com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;
import projeto_clinica.com.dto.Request.SinaisVitaisRequestDTO;
import projeto_clinica.com.dto.Response.SinaisVitaisResponseDTO;
import projeto_clinica.com.service.SinaisVitaisService;

@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'MEDICO', 'ENFERMEIRO', 'TECNICO_ENFERMAGEM')")
@RequestMapping("/sinais-vitais")
public class SinaisVitaisController {
    private final SinaisVitaisService service;

    public SinaisVitaisController(SinaisVitaisService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<SinaisVitaisResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SinaisVitaisResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<SinaisVitaisResponseDTO> create(@Valid @RequestBody SinaisVitaisRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SinaisVitaisResponseDTO> update(
            @PathVariable Long id, @Valid @RequestBody SinaisVitaisRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
