package projeto_clinica.com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;
import projeto_clinica.com.dto.Request.QuartoRequestDTO;
import projeto_clinica.com.dto.Response.QuartoResponseDTO;
import projeto_clinica.com.service.QuartoService;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/quartos")
public class QuartoController {
    private final QuartoService service;

    public QuartoController(QuartoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<QuartoResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuartoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<QuartoResponseDTO> create(@Valid @RequestBody QuartoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuartoResponseDTO> update(
            @PathVariable Long id, @Valid @RequestBody QuartoRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
