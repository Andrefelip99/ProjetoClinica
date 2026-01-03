package projeto_clinica.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import projeto_clinica.com.model.Administrador;
import projeto_clinica.com.repository.AdministradorRepository;

@AllArgsConstructor
@Service
public class AdministradorService {

    private final AdministradorRepository administradorRepository;

    public Administrador criarAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    public Optional<Administrador> buscarPorId(Long id) {
        return administradorRepository.findById(id);
    }

    public List<Administrador> listarAdministradores() {
        return administradorRepository.findAll();
    }

  

     public Optional<Administrador> desativar(Long id) {
        return administradorRepository.findById(id)
                .map(administrador -> {
                    administrador.setAtivo(false);
                    return administradorRepository.save(administrador);
                });
}
}


