package projeto_clinica.com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projeto_clinica.com.model.ProntuarioRegistro;

public interface ProntuarioRegistroRepository extends JpaRepository<ProntuarioRegistro, Long> {

        @Query("""
                        select r from ProntuarioRegistro r
                        join fetch r.prontuario
                        join fetch r.prontuario.paciente
                        join fetch r.funcionario
                        where r.id = :id
                        """)
        Optional<ProntuarioRegistro> findByIdWithAssociations(@Param("id") Long id);

        @Query("""
                        select r from ProntuarioRegistro r
                        join fetch r.prontuario
                        join fetch r.prontuario.paciente
                        join fetch r.funcionario
                        where r.prontuario.id = :prontuarioId
                        """)
        List<ProntuarioRegistro> findByProntuarioIdWithAssociations(
                        @Param("prontuarioId") Long prontuarioId);
}
