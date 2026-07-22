package projeto_clinica.com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import projeto_clinica.com.dto.Request.CadastroInicialRequestDTO;
import projeto_clinica.com.dto.Request.LoginRequestDTO;
import projeto_clinica.com.dto.Response.CadastroInicialResponseDTO;
import projeto_clinica.com.service.CadastroInicialService;

@RestController
@RequestMapping("/auth")
public class CadastroInicialController {

    private final CadastroInicialService cadastroInicialService;

    public CadastroInicialController(CadastroInicialService cadastroInicialService) {
        this.cadastroInicialService = cadastroInicialService;
    }

    @PostMapping("/register")
    public ResponseEntity<CadastroInicialResponseDTO> cadastrar(
            @Valid @RequestBody CadastroInicialRequestDTO dto) {
        CadastroInicialResponseDTO novoUsuario = cadastroInicialService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        boolean sucesso = cadastroInicialService.login(
                loginRequest.email(),
                loginRequest.senha());

        if (sucesso) {
            return ResponseEntity.ok("Login realizado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Email ou senha inválidos");
        }
    }
}
