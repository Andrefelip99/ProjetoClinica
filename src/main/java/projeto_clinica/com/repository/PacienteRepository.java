package projeto_clinica.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projeto_clinica.com.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    

    
}
