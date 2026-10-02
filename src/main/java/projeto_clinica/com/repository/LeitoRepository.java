package projeto_clinica.com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projeto_clinica.com.model.Leito;
import projeto_clinica.com.model.enums.StatusLeito;

public interface LeitoRepository extends JpaRepository<Leito, Long> {

    @Query("select l from Leito l join fetch l.quarto where l.id = :id")
    Optional<Leito> findByIdWithQuarto(@Param("id") Long id);

    @Query("select l from Leito l join fetch l.quarto where l.status = :status")
    List<Leito> findByStatusWithQuarto(@Param("status") StatusLeito status);
}
