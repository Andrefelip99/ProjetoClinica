package projeto_clinica.com.service;

import java.util.List;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import projeto_clinica.com.model.Medico;
import projeto_clinica.com.repository.MedicoRepository;

@AllArgsConstructor
@Service
public class MedicoService {
    
    private final MedicoRepository medicoRepository;



    public Medico criarMedico(Medico medico){
        return medicoRepository.save(medico);
    }

    public Medico buscarPorId(Long id){
        return medicoRepository.findById(id).orElse(null);
    }

    public List<Medico> listarMedico(){
        return medicoRepository.findAll();
    }
}
