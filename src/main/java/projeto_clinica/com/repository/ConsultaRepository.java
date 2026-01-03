package projeto_clinica.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import projeto_clinica.com.model.Consulta;
@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    
    //METODO PARA DAR BASE PARA O SERVICE
    List<Consulta> findByPacienteId(Long pacienteId);
    List<Consulta> findByMedicoId(Long medicoId);
  
    
}
