package projeto_clinica.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import projeto_clinica.com.model.Enfermeiro;
import projeto_clinica.com.repository.EnfermeiroRepository;

@AllArgsConstructor
@Service
public class EnfermeiroService {
    
    private final  EnfermeiroRepository enfermeiroRepository;


    public Enfermeiro criarEnfermeiro(Enfermeiro enfermeiro){
        return enfermeiroRepository.save(enfermeiro);

    }

    public Optional<Enfermeiro> buscarPorId(Long id){
        return enfermeiroRepository.findById(id);
    }

    public List<Enfermeiro> listarEnfermeiros(){
        return enfermeiroRepository.findAll();
    }
   
}
