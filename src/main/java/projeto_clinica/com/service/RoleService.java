package projeto_clinica.com.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projeto_clinica.com.dto.Request.RoleDTO;
import projeto_clinica.com.model.Role;
import projeto_clinica.com.repository.RoleRepository;
import projeto_clinica.com.service.exceptions.ResourceNotFoundException;

@Service
public class RoleService {
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<RoleDTO> findAll() {
        return repository.findAll().stream().map(RoleDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public RoleDTO findById(Long id) {
        return new RoleDTO(buscarEntidade(id));
    }

    @Transactional
    public RoleDTO create(RoleDTO dto) {
        Role role = new Role();
        role.setAuthority(dto.getAuthority());
        return new RoleDTO(repository.save(role));
    }

    @Transactional
    public RoleDTO update(Long id, RoleDTO dto) {
        Role role = buscarEntidade(id);
        role.setAuthority(dto.getAuthority());
        return new RoleDTO(repository.save(role));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(buscarEntidade(id));
    }

    private Role buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role não encontrada. ID: " + id));
    }
}
