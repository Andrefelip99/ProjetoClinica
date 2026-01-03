package projeto_clinica.com.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import projeto_clinica.com.model.Consulta;
import projeto_clinica.com.model.StatusConsulta;
import projeto_clinica.com.repository.ConsultaRepository;

@AllArgsConstructor
@Service
public class ConsultaService {
    
    private final ConsultaRepository consultaRepository;


    public Consulta marcarConsulta(Consulta consulta){
        return  consultaRepository.save(consulta);
    } 

    public Consulta remarcarConsulta(Long id, LocalDateTime novaDataHora){
        return consultaRepository.findById(id).map(consulta -> {
            consulta.setDataHora(novaDataHora);
            return consultaRepository.save(consulta);
        }).orElse(null);
    }

    public Consulta cancelarConsulta(Long id){
        Consulta consulta = consultaRepository.findById(id).orElse(null);
        if (consulta != null) {
            consulta.setStatus(StatusConsulta.CANCELADA);
            consultaRepository.save(consulta);
        }
        return consulta;
    }

    public Consulta buscarPorId(Long id){
        return consultaRepository.findById(id).orElse(null);
    }

    public List<Consulta> listarConsultasPorPaciente(Long pacienteId) {
    return consultaRepository.findByPacienteId(pacienteId);
    }

    public List<Consulta> listarConsultasPorMedico(Long medicoId) {
        return consultaRepository.findByMedicoId(medicoId);
    }

    public List<Consulta> historicoConsultasPorPaciente(Long pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId);}

   



}
