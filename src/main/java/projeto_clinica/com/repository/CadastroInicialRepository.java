package projeto_clinica.com.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto_clinica.com.model.CadastroInicial;

public interface CadastroInicialRepository
        extends JpaRepository<CadastroInicial, Long> {

    Optional<CadastroInicial> findByEmail(String email);
}
