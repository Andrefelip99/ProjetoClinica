package projeto_clinica.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projeto_clinica.com.model.CadastroInicial;
import projeto_clinica.com.repository.CadastroInicialRepository;

@Service
public class CadastroInicialService {

    private final CadastroInicialRepository cadastroInicialRepository;

    @Autowired
    public CadastroInicialService(CadastroInicialRepository cadastroInicialRepository) {
        this.cadastroInicialRepository = cadastroInicialRepository;
    }

    
    public CadastroInicial cadastrar(CadastroInicial cadastro) {
       
        if (cadastroInicialRepository.findByEmail(cadastro.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        return cadastroInicialRepository.save(cadastro);
    }

    public boolean login(String email, String senha) {
        CadastroInicial usuario = cadastroInicialRepository.findByEmail(email).orElse(null);
        
        if (usuario == null) {
            return false; 
        }

        return usuario.getSenha().equals(senha);
    }
}
