package projeto_clinica.com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;
import projeto_clinica.com.dto.Request.RoleDTO;
import projeto_clinica.com.service.RoleService;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/roles")
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> create(@Valid @RequestBody RoleDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> update(@PathVariable Long id, @Valid @RequestBody RoleDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
