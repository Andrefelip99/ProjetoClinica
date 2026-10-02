package projeto_clinica.com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projeto_clinica.com.model.Prescricao;

public interface PrescricaoRepository extends JpaRepository<Prescricao, Long> {

        @Query("""
                        select p from Prescricao p
                        join fetch p.paciente
                        join fetch p.medico
                        where p.id = :id
                        """)
        Optional<Prescricao> findByIdWithAssociations(@Param("id") Long id);

        @Query("""
                        select p from Prescricao p
                        join fetch p.paciente
                        join fetch p.medico
                        where p.paciente.id = :pacienteId
                        """)
        List<Prescricao> findByPacienteIdWithAssociations(@Param("pacienteId") Long pacienteId);
}
