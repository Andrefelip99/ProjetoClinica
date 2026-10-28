package projeto_clinica.com.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projeto_clinica.com.dto.Request.QuartoRequestDTO;
import projeto_clinica.com.dto.Response.QuartoResponseDTO;
import projeto_clinica.com.model.Quarto;
import projeto_clinica.com.repository.QuartoRepository;
import projeto_clinica.com.service.exceptions.ResourceNotFoundException;

@Service
public class QuartoService {
    private final QuartoRepository repository;

    public QuartoService(QuartoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<QuartoResponseDTO> findAll() {
        return repository.findAll().stream().map(QuartoResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public QuartoResponseDTO findById(Long id) {
        return new QuartoResponseDTO(buscarEntidade(id));
    }

    @Transactional
    public QuartoResponseDTO create(QuartoRequestDTO dto) {
        Quarto quarto = new Quarto();
        copiar(dto, quarto);
        return new QuartoResponseDTO(repository.save(quarto));
    }

    @Transactional
    public QuartoResponseDTO update(Long id, QuartoRequestDTO dto) {
        Quarto quarto = buscarEntidade(id);
        copiar(dto, quarto);
        return new QuartoResponseDTO(repository.save(quarto));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(buscarEntidade(id));
    }

    private Quarto buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quarto não encontrado. ID: " + id));
    }

    private void copiar(QuartoRequestDTO dto, Quarto quarto) {
        quarto.setNumero(dto.numero());
        quarto.setAndar(dto.andar());
        quarto.setTipo(dto.tipo());
    }
}
