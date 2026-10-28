package projeto_clinica.com.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto_clinica.com.dto.Request.PrescricaoItemRequestDTO;
import projeto_clinica.com.dto.Response.PrescricaoItemResponseDTO;
import projeto_clinica.com.model.Prescricao;
import projeto_clinica.com.model.PrescricaoItem;
import projeto_clinica.com.repository.PrescricaoItemRepository;
import projeto_clinica.com.repository.PrescricaoRepository;
import projeto_clinica.com.service.exceptions.ResourceNotFoundException;

@Service
public class PrescricaoItemService {
    private final PrescricaoItemRepository repository;
    private final PrescricaoRepository prescricoes;

    public PrescricaoItemService(PrescricaoItemRepository repository, PrescricaoRepository prescricoes) {
        this.repository = repository;
        this.prescricoes = prescricoes;
    }

    @Transactional(readOnly = true)
    public List<PrescricaoItemResponseDTO> findAll() {
        return repository.findAll().stream().map(PrescricaoItemResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public PrescricaoItemResponseDTO findById(Long id) {
        return new PrescricaoItemResponseDTO(buscar(id));
    }

    @Transactional
    public PrescricaoItemResponseDTO create(PrescricaoItemRequestDTO dto) {
        PrescricaoItem item = new PrescricaoItem();
        copiar(dto, item);
        return new PrescricaoItemResponseDTO(repository.save(item));
    }

    @Transactional
    public PrescricaoItemResponseDTO update(Long id, PrescricaoItemRequestDTO dto) {
        PrescricaoItem item = buscar(id);
        copiar(dto, item);
        return new PrescricaoItemResponseDTO(repository.save(item));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(buscar(id));
    }

    private PrescricaoItem buscar(Long id) {
        return repository.findByIdWithPrescricao(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item de prescrição não encontrado. ID: " + id));
    }

    private void copiar(PrescricaoItemRequestDTO dto, PrescricaoItem item) {
        Prescricao prescricao = prescricoes.findById(dto.prescricaoId())
                .orElseThrow(() -> new ResourceNotFoundException("Prescrição não encontrada. ID: " + dto.prescricaoId()));
        item.setPrescricao(prescricao);
        item.setMedicamento(dto.medicamento());
        item.setDosagem(dto.dosagem());
        item.setFrequencia(dto.frequencia());
        item.setDuracao(dto.duracao());
        item.setObservacoes(dto.observacoes());
    }
}
