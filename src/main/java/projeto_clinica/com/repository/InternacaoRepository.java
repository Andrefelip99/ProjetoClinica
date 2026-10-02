package projeto_clinica.com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projeto_clinica.com.model.Internacao;

public interface InternacaoRepository extends JpaRepository<Internacao, Long> {

        @Query("""
                        select i from Internacao i
                        join fetch i.paciente
                        join fetch i.leito
                        join fetch i.leito.quarto
                        where i.id = :id
                        """)
        Optional<Internacao> findByIdWithAssociations(@Param("id") Long id);

        @Query("""
                        select i from Internacao i
                        join fetch i.paciente
                        join fetch i.leito
                        join fetch i.leito.quarto
                        where i.paciente.id = :pacienteId
                        """)
        List<Internacao> findByPacienteIdWithAssociations(@Param("pacienteId") Long pacienteId);
}
