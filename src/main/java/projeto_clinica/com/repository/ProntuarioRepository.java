package projeto_clinica.com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projeto_clinica.com.model.Prontuario;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {

    @Query("select p from Prontuario p join fetch p.paciente where p.id = :id")
    Optional<Prontuario> findByIdWithPaciente(@Param("id") Long id);

    @Query("select p from Prontuario p join fetch p.paciente where p.paciente.id = :pacienteId")
    Optional<Prontuario> findByPacienteIdWithPaciente(@Param("pacienteId") Long pacienteId);
}
