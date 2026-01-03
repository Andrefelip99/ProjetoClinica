package projeto_clinica.com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import projeto_clinica.com.model.Medico;
import projeto_clinica.com.service.MedicoService;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/medicos")
public class MedicoController {
    
    private final MedicoService medicoService;
   
    
@PostMapping
public ResponseEntity<Medico> criarMedico(@Valid @RequestBody Medico medico){
    Medico novoMedico = medicoService.criarMedico(medico);
    return ResponseEntity.status(201).body(novoMedico);
}

@GetMapping
public ResponseEntity<List<Medico>> listarMedicos() {
    return ResponseEntity.ok(medicoService.listarMedico());
}


@GetMapping("/{id}")
public ResponseEntity<Medico> buscarMedicoPorId(@PathVariable Long id){
    Medico medico = medicoService.buscarPorId(id);

    if (medico == null) {
        return ResponseEntity.notFound().build();
        
    }
    return ResponseEntity.ok(medico);
}



}