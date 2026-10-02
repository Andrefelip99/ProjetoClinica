package projeto_clinica.com.repository;

import java.util.List;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import projeto_clinica.com.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByIdIn(Collection<Long> ids);
    java.util.Optional<Role> findByAuthority(String authority);
}
