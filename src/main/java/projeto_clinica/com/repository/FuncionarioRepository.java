package projeto_clinica.com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projeto_clinica.com.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    @Query("select distinct f from Funcionario f left join fetch f.roles where f.email = :email")
    Optional<Funcionario> findByEmail(String email);

    @Query("select distinct f from Funcionario f left join fetch f.roles where f.id = :id")
    Optional<Funcionario> findByIdWithRoles(@Param("id") Long id);

    boolean existsByCpf(String cpf);
}
