package projeto_clinica.com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;
import projeto_clinica.com.dto.Request.CadastroInicialRequestDTO;
import projeto_clinica.com.dto.Request.LoginCadastroInicialRequestDTO;
import projeto_clinica.com.dto.Response.CadastroInicialResponseDTO;
import projeto_clinica.com.service.CadastroInicialService;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/cadastros-iniciais")
public class CadastroInicialController {
    private final CadastroInicialService service;

    public CadastroInicialController(CadastroInicialService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CadastroInicialResponseDTO> create(
            @Valid @RequestBody CadastroInicialRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginCadastroInicialRequestDTO dto) {
        service.login(dto.email(), dto.senha());
        return ResponseEntity.ok().build();
    }
}
