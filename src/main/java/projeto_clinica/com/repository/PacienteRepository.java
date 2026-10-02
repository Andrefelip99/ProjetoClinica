package projeto_clinica.com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import projeto_clinica.com.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    @Query("select distinct p from Paciente p left join fetch p.consultas where p.id = :id")
    Optional<Paciente> findByIdWithConsultas(@Param("id") Long id);
}
