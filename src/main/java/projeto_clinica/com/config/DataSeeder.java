package projeto_clinica.com.config;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import projeto_clinica.com.model.Role;
import projeto_clinica.com.repository.RoleRepository;

@Configuration
@Profile("dev")
public class DataSeeder {
    @Bean
    CommandLineRunner seedRoles(RoleRepository roles) {
        return args -> {
            List<String> authorities = List.of("ROLE_ADMIN", "ROLE_MEDICO", "ROLE_ENFERMEIRO",
                    "ROLE_TECNICO_ENFERMAGEM", "ROLE_ADMINISTRACAO");
            for (String authority : authorities) {
                if (roles.findByAuthority(authority).isEmpty()) {
                    Role role = new Role();
                    role.setAuthority(authority);
                    roles.save(role);
                }
            }
        };
    }
}
