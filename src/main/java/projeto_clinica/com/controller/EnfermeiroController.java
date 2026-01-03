package projeto_clinica.com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import projeto_clinica.com.model.Enfermeiro;
import projeto_clinica.com.service.EnfermeiroService;

@AllArgsConstructor
@RestController
@RequestMapping("/enfermeiros")
public class EnfermeiroController {

    private final EnfermeiroService enfermeiroService;

    @PostMapping
    public ResponseEntity<Enfermeiro> criarEnfermeiro(@Valid @RequestBody Enfermeiro enfermeiro){
        Enfermeiro novoEnfermeiro = enfermeiroService.criarEnfermeiro(enfermeiro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEnfermeiro);
    }

    @GetMapping
    public ResponseEntity<List<Enfermeiro>> listarEnfermeiros(){
        return ResponseEntity.ok(enfermeiroService.listarEnfermeiros());
    }

    @GetMapping("/{id}")
public ResponseEntity<Enfermeiro> buscarEnfermeiroPorId(@PathVariable Long id) {
    return enfermeiroService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}


   
   


    
}
