package projeto_clinica.com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto_clinica.com.service.ConsultaService;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import projeto_clinica.com.model.Consulta;
import projeto_clinica.com.model.Paciente;
import projeto_clinica.com.service.PacienteService;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;
    private final ConsultaService consultaService;

    

    @PostMapping
    public ResponseEntity<Paciente> criarPaciente(@Valid  @RequestBody Paciente paciente) {
        Paciente novoPaciente = pacienteService.criarPaciente(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPaciente);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacienteS(){
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

     @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Long id){
        return pacienteService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizarPaciente(
        @PathVariable Long id,
        @RequestBody Paciente paciente){
        Paciente atualizado = pacienteService.atualizarPaciente(id, paciente);

        if(atualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atualizado);
    }

    @GetMapping("/{id}/consultas")
    public ResponseEntity<List<Consulta>> historicoConsultas(@PathVariable Long id) {

         Paciente paciente = pacienteService.buscarPorId(id).orElse(null);
    if (paciente == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(
            consultaService.historicoConsultasPorPaciente(id)
    );
}


    

}   
