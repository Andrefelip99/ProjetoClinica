package projeto_clinica.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projeto_clinica.com.dto.Request.CadastroInicialRequestDTO;
import projeto_clinica.com.dto.Response.CadastroInicialResponseDTO;
import projeto_clinica.com.model.CadastroInicial;
import projeto_clinica.com.repository.CadastroInicialRepository;

@Service
public class CadastroInicialService {

    private final CadastroInicialRepository cadastroInicialRepository;

    @Autowired
    public CadastroInicialService(CadastroInicialRepository cadastroInicialRepository) {
        this.cadastroInicialRepository = cadastroInicialRepository;
    }

    public CadastroInicialResponseDTO cadastrar(CadastroInicialRequestDTO dto) {
        if (cadastroInicialRepository.findByEmail(dto.email()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        CadastroInicial cadastro = new CadastroInicial();
        cadastro.setNome(dto.nome());
        cadastro.setEmail(dto.email());
        cadastro.setSenha(dto.senha());

        cadastro = cadastroInicialRepository.save(cadastro);
        return new CadastroInicialResponseDTO(cadastro);
    }

    public boolean login(String email, String senha) {
        CadastroInicial usuario = cadastroInicialRepository.findByEmail(email).orElse(null);

        if (usuario == null) {
            return false;
        }

        return usuario.getSenha().equals(senha);
    }
}
