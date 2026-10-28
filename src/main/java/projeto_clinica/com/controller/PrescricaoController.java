package projeto_clinica.com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;
import projeto_clinica.com.dto.Request.PrescricaoRequestDTO;
import projeto_clinica.com.dto.Response.PrescricaoResponseDTO;
import projeto_clinica.com.service.PrescricaoService;

@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'MEDICO')")
@RequestMapping("/prescricoes")
public class PrescricaoController {
    private final PrescricaoService service;

    public PrescricaoController(PrescricaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PrescricaoResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescricaoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PrescricaoResponseDTO> create(@Valid @RequestBody PrescricaoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrescricaoResponseDTO> update(
            @PathVariable Long id, @Valid @RequestBody PrescricaoRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
