package projeto_clinica.com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;
import projeto_clinica.com.dto.Request.LeitoRequestDTO;
import projeto_clinica.com.dto.Response.LeitoResponseDTO;
import projeto_clinica.com.service.LeitoService;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/leitos")
public class LeitoController {
    private final LeitoService service;

    public LeitoController(LeitoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<LeitoResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeitoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<LeitoResponseDTO> create(@Valid @RequestBody LeitoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeitoResponseDTO> update(
            @PathVariable Long id, @Valid @RequestBody LeitoRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
