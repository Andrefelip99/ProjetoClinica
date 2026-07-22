package projeto_clinica.com.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import projeto_clinica.com.dto.Request.FuncionarioRequestDTO;
import projeto_clinica.com.dto.Response.FuncionarioResponseDTO;
import projeto_clinica.com.service.FuncionarioService;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> create(@Valid @RequestBody FuncionarioRequestDTO dto) {
        FuncionarioResponseDTO saved = service.criarFuncionario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarFuncionarioPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarFuncionarios());
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        service.desativar(id);
        return ResponseEntity.noContent().build();
    }
}
