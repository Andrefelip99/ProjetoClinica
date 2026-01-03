package projeto_clinica.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projeto_clinica.com.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    
}
