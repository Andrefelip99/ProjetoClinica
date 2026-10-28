package projeto_clinica.com.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projeto_clinica.com.dto.Request.AdministracaoMedicamentoRequestDTO;
import projeto_clinica.com.dto.Response.AdministracaoMedicamentoResponseDTO;
import projeto_clinica.com.model.AdministracaoMedicamento;
import projeto_clinica.com.model.Funcionario;
import projeto_clinica.com.model.PrescricaoItem;
import projeto_clinica.com.repository.AdministracaoMedicamentoRepository;
import projeto_clinica.com.repository.FuncionarioRepository;
import projeto_clinica.com.repository.PrescricaoItemRepository;
import projeto_clinica.com.service.exceptions.ResourceNotFoundException;

@Service
public class AdministracaoMedicamentoService {

    private final AdministracaoMedicamentoRepository administracaoMedicamentoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final PrescricaoItemRepository prescricaoItemRepository;

    public AdministracaoMedicamentoService(AdministracaoMedicamentoRepository repository,
            FuncionarioRepository funcionarioRepository, PrescricaoItemRepository prescricaoItemRepository) {
        this.administracaoMedicamentoRepository = repository;
        this.funcionarioRepository = funcionarioRepository;
        this.prescricaoItemRepository = prescricaoItemRepository;
    }

    @Transactional(readOnly = true)
    public List<AdministracaoMedicamentoResponseDTO> findAll() {
        List<AdministracaoMedicamento> administracaoMedicamentos = administracaoMedicamentoRepository.findAll();
        return administracaoMedicamentos.stream().map(AdministracaoMedicamentoResponseDTO::new).toList();

    }

    @Transactional(readOnly = true)
    public AdministracaoMedicamentoResponseDTO findById(Long id) {
        AdministracaoMedicamento entity = administracaoMedicamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado: " + id));
        return new AdministracaoMedicamentoResponseDTO(entity);
    }

    @Transactional
    public AdministracaoMedicamentoResponseDTO create(AdministracaoMedicamentoRequestDTO dto) {
        AdministracaoMedicamento entity = new AdministracaoMedicamento();
        copyDtoEntity(dto, entity);
        entity = administracaoMedicamentoRepository.save(entity);
        return new AdministracaoMedicamentoResponseDTO(entity);
    }

    @Transactional
    public AdministracaoMedicamentoResponseDTO update(Long id, AdministracaoMedicamentoRequestDTO dto) {
        AdministracaoMedicamento entity = administracaoMedicamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado: " + id));
        copyDtoEntity(dto, entity);
        return new AdministracaoMedicamentoResponseDTO(administracaoMedicamentoRepository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        AdministracaoMedicamento entity = administracaoMedicamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado: " + id));
        administracaoMedicamentoRepository.delete(entity);
    }

    private void copyDtoEntity(AdministracaoMedicamentoRequestDTO dto, AdministracaoMedicamento entity) {
        entity.setDataHora(dto.dataHora());
        Funcionario funcionario = funcionarioRepository.findById(dto.funcionario()).orElseThrow(() -> new ResourceNotFoundException("Funcionario nao encontrado"));
        PrescricaoItem p = prescricaoItemRepository.findById(dto.prescricaoItem()).orElseThrow(()-> new ResourceNotFoundException("Item de Prescrição não encontrado"));
        entity.setFuncionario(funcionario);
        entity.setObservacao(dto.observacao());
        entity.setPrescricaoItem(p);
        entity.setStatus(dto.status());

    }
}
