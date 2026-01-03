package projeto_clinica.com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import projeto_clinica.com.model.Consulta;
import projeto_clinica.com.service.ConsultaService;

@AllArgsConstructor
@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;


    @PostMapping
    public ResponseEntity<Consulta> marcarConsulta(@Valid @RequestBody Consulta consulta){
        Consulta novaConsulta = consultaService.marcarConsulta(consulta);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConsulta);
    }

    @PutMapping("/{id}/remarcar")
public ResponseEntity<Consulta> remarcarConsulta(
       @PathVariable Long id,
        @RequestBody Consulta consulta) {

    Consulta atualizada =
            consultaService.remarcarConsulta(id, consulta.getDataHora());

    if (atualizada == null) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(atualizada);
} 
  
    @PutMapping("/{id}/cancelar")
public ResponseEntity<Consulta> cancelarConsulta(@PathVariable Long id) {
    Consulta cancelada = consultaService.cancelarConsulta(id);
    if (cancelada == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(cancelada);
}

    @GetMapping("/paciente/{pacienteId}")
public ResponseEntity<List<Consulta>> listarConsultasPorPaciente(
            @PathVariable Long pacienteId) {
                return ResponseEntity.ok(
                    consultaService.listarConsultasPorPaciente(pacienteId)
                );
            }
   
    @GetMapping("/medico/{medicoId}")
public ResponseEntity<List<Consulta>> listarConsultasPorMedico(
        @PathVariable Long medicoId) {

    return ResponseEntity.ok(
            consultaService.listarConsultasPorMedico(medicoId)
    );
}   

    
            
    }


