package projeto_clinica.com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;
import projeto_clinica.com.dto.Request.PrescricaoItemRequestDTO;
import projeto_clinica.com.dto.Response.PrescricaoItemResponseDTO;
import projeto_clinica.com.service.PrescricaoItemService;

@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'MEDICO')")
@RequestMapping("/prescricao-itens")
public class PrescricaoItemController {
    private final PrescricaoItemService service;

    public PrescricaoItemController(PrescricaoItemService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PrescricaoItemResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescricaoItemResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PrescricaoItemResponseDTO> create(@Valid @RequestBody PrescricaoItemRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrescricaoItemResponseDTO> update(
            @PathVariable Long id, @Valid @RequestBody PrescricaoItemRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
