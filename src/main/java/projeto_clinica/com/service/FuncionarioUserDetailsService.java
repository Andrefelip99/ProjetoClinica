package projeto_clinica.com.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import projeto_clinica.com.repository.FuncionarioRepository;

@Service
public class FuncionarioUserDetailsService implements UserDetailsService {
    private final FuncionarioRepository repository;

    public FuncionarioUserDetailsService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Funcionario nao encontrado"));
    }
}
