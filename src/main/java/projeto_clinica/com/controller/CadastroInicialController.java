package projeto_clinica.com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import projeto_clinica.com.model.CadastroInicial;
import projeto_clinica.com.service.CadastroInicialService;

@RestController
@RequestMapping("/auth")
public class CadastroInicialController {

    private final CadastroInicialService cadastroInicialService;

    public CadastroInicialController(CadastroInicialService cadastroInicialService) {
        this.cadastroInicialService = cadastroInicialService;
    }

  
    @PostMapping("/register")
    public ResponseEntity<?> cadastrar(@RequestBody CadastroInicial cadastro) {
        try {
            CadastroInicial novoUsuario = cadastroInicialService.cadastrar(cadastro);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CadastroInicial loginRequest) {
        boolean sucesso = cadastroInicialService.login(
                loginRequest.getEmail(),
                loginRequest.getSenha()
        );

        if (sucesso) {
            return ResponseEntity.ok("Login realizado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Email ou senha inválidos");
        }
    }
}
