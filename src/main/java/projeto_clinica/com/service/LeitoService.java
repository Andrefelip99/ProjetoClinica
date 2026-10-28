package projeto_clinica.com.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projeto_clinica.com.dto.Request.LeitoRequestDTO;
import projeto_clinica.com.dto.Response.LeitoResponseDTO;
import projeto_clinica.com.model.Leito;
import projeto_clinica.com.model.Quarto;
import projeto_clinica.com.repository.LeitoRepository;
import projeto_clinica.com.repository.QuartoRepository;
import projeto_clinica.com.service.exceptions.ResourceNotFoundException;

@Service
public class LeitoService {
    private final LeitoRepository leitoRepository;
    private final QuartoRepository quartoRepository;

    public LeitoService(LeitoRepository repository, QuartoRepository quartoRepository) {
        this.leitoRepository = repository;
        this.quartoRepository = quartoRepository;
    }

    @Transactional(readOnly = true)
    public List<LeitoResponseDTO> findAll() {
        List<Leito> leitos = leitoRepository.findAll();
        return leitos.stream().map(LeitoResponseDTO::new).toList();

    }

    @Transactional(readOnly = true)
    public LeitoResponseDTO findById(Long id) { 
        Leito leito = leitoRepository.findByIdWithQuarto(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Leito não encontrado. ID: " + id));

        return new LeitoResponseDTO(leito);

    }

    @Transactional
    public LeitoResponseDTO create(LeitoRequestDTO dto) {
        Leito entity = new Leito();
        copyDtoToEntity(dto, entity);
        entity = leitoRepository.save(entity);
        return new LeitoResponseDTO(entity);

    }

    @Transactional
    public LeitoResponseDTO update(Long id, LeitoRequestDTO dto) {
        Leito entity = leitoRepository.findByIdWithQuarto(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leito não encontrado. ID: " + id));
        copyDtoToEntity(dto, entity);
        return new LeitoResponseDTO(leitoRepository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        Leito entity = leitoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leito não encontrado. ID: " + id));
        leitoRepository.delete(entity);
    }

    private void copyDtoToEntity(LeitoRequestDTO dto, Leito entity) {
        entity.setNumero(dto.numero());
        Quarto quarto = quartoRepository.findById(dto.quarto())
                .orElseThrow(() -> new ResourceNotFoundException("Quarto não encontrado"));
        entity.setQuarto(quarto);
        entity.setStatus(dto.status());

    }
}
