package projeto_clinica.com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;
import projeto_clinica.com.dto.Request.AdministracaoMedicamentoRequestDTO;
import projeto_clinica.com.dto.Response.AdministracaoMedicamentoResponseDTO;
import projeto_clinica.com.service.AdministracaoMedicamentoService;

@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'MEDICO', 'ENFERMEIRO', 'TECNICO_ENFERMAGEM')")
@RequestMapping("/administracoes-medicamentos")
public class AdministracaoMedicamentoController {
    private final AdministracaoMedicamentoService service;

    public AdministracaoMedicamentoController(AdministracaoMedicamentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AdministracaoMedicamentoResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministracaoMedicamentoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<AdministracaoMedicamentoResponseDTO> create(
            @Valid @RequestBody AdministracaoMedicamentoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministracaoMedicamentoResponseDTO> update(
            @PathVariable Long id, @Valid @RequestBody AdministracaoMedicamentoRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
