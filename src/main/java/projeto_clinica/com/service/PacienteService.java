package projeto_clinica.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import projeto_clinica.com.model.Paciente;
import projeto_clinica.com.repository.PacienteRepository;

@AllArgsConstructor
@Service
public class PacienteService {
    
    private final PacienteRepository pacienteRepository;
    

    public Paciente criarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscarPorId(Long id){
        return pacienteRepository.findById(id);
    }

    public List<Paciente> listarPacientes(){
        return pacienteRepository.findAll();
    } 
    
    public Paciente atualizarPaciente(Long id, Paciente pacienteAtualizado) {
        return pacienteRepository.findById(id).map(paciente -> {
            paciente.setNome(pacienteAtualizado.getNome());
            paciente.setIdade(pacienteAtualizado.getIdade());
            paciente.setEndereco(pacienteAtualizado.getEndereco());
            paciente.setTelefone(pacienteAtualizado.getTelefone());
            return pacienteRepository.save(paciente);
        }).orElse(null);
    }

  
}

    





