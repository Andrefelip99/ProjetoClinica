package projeto_clinica.com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;
import projeto_clinica.com.dto.Request.InternacaoRequestDTO;
import projeto_clinica.com.dto.Response.InternacaoResponseDTO;
import projeto_clinica.com.service.InternacaoService;

@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'MEDICO', 'ENFERMEIRO', 'TECNICO_ENFERMAGEM')")
@RequestMapping("/internacoes")
public class InternacaoController {
    private final InternacaoService service;

    public InternacaoController(InternacaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<InternacaoResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InternacaoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<InternacaoResponseDTO> create(@Valid @RequestBody InternacaoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InternacaoResponseDTO> update(
            @PathVariable Long id, @Valid @RequestBody InternacaoRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
