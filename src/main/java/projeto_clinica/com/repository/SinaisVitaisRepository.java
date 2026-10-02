package projeto_clinica.com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projeto_clinica.com.model.SinaisVitais;

public interface SinaisVitaisRepository extends JpaRepository<SinaisVitais, Long> {

        @Query("""
                        select s from SinaisVitais s
                        join fetch s.paciente
                        join fetch s.funcionario
                        where s.id = :id
                        """)
        Optional<SinaisVitais> findByIdWithAssociations(@Param("id") Long id);

        @Query("""
                        select s from SinaisVitais s
                        join fetch s.paciente
                        join fetch s.funcionario
                        where s.paciente.id = :pacienteId
                        """)
        List<SinaisVitais> findByPacienteIdWithAssociations(@Param("pacienteId") Long pacienteId);
}
