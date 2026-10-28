package projeto_clinica.com.service;

import java.util.List;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto_clinica.com.dto.Request.InternacaoRequestDTO;
import projeto_clinica.com.dto.Response.InternacaoResponseDTO;
import projeto_clinica.com.model.Leito;
import projeto_clinica.com.model.Internacao;
import projeto_clinica.com.model.enums.StatusInternacao;
import projeto_clinica.com.model.enums.StatusLeito;
import projeto_clinica.com.repository.InternacaoRepository;
import projeto_clinica.com.repository.PacienteRepository;
import projeto_clinica.com.repository.LeitoRepository;
import projeto_clinica.com.service.exceptions.ResourceNotFoundException;
import projeto_clinica.com.service.exceptions.ConflictException;

@Service
public class InternacaoService {
    private final InternacaoRepository repository;
    private final PacienteRepository pacientes;
    private final LeitoRepository leitos;

    public InternacaoService(InternacaoRepository repository, PacienteRepository pacientes, LeitoRepository leitos) {
        this.repository = repository;
        this.pacientes = pacientes;
        this.leitos = leitos;
    }

    @Transactional(readOnly = true)
    public List<InternacaoResponseDTO> findAll() {
        return repository.findAll().stream().map(InternacaoResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public InternacaoResponseDTO findById(Long id) {
        return new InternacaoResponseDTO(buscar(id));
    }

    @Transactional
    public InternacaoResponseDTO create(InternacaoRequestDTO dto) {
        Internacao internacao = new Internacao();
        copiarDados(dto, internacao);
        Leito leito = buscarLeito(dto.leitoId());
        atualizarOcupacao(null, null, leito, dto.status());
        internacao.setLeito(leito);
        ajustarDataAlta(dto, internacao);
        return new InternacaoResponseDTO(repository.save(internacao));
    }

    @Transactional
    public InternacaoResponseDTO update(Long id, InternacaoRequestDTO dto) {
        Internacao internacao = buscar(id);
        StatusInternacao statusAnterior = internacao.getStatus();
        Leito leitoAnterior = internacao.getLeito();
        copiarDados(dto, internacao);
        Leito novoLeito = buscarLeito(dto.leitoId());
        atualizarOcupacao(statusAnterior, leitoAnterior, novoLeito, dto.status());
        if (statusAnterior == StatusInternacao.INTERNADO
                && (!novoLeito.getId().equals(leitoAnterior.getId()) || dto.status() != StatusInternacao.INTERNADO)) {
            leitoAnterior.setStatus(StatusLeito.DISPONIVEL);
            leitos.save(leitoAnterior);
        }
        internacao.setLeito(novoLeito);
        ajustarDataAlta(dto, internacao);
        return new InternacaoResponseDTO(repository.save(internacao));
    }

    @Transactional
    public void delete(Long id) {
        Internacao internacao = buscar(id);
        if (internacao.getStatus() == StatusInternacao.INTERNADO) {
            internacao.getLeito().setStatus(StatusLeito.DISPONIVEL);
            leitos.save(internacao.getLeito());
        }
        repository.delete(internacao);
    }

    private Internacao buscar(Long id) {
        return repository.findByIdWithAssociations(id)
                .orElseThrow(() -> new ResourceNotFoundException("Internação não encontrada. ID: " + id));
    }

    private void copiarDados(InternacaoRequestDTO dto, Internacao i) {
        i.setPaciente(pacientes.findById(dto.pacienteId()).orElseThrow(
                () -> new ResourceNotFoundException("Paciente não encontrado. ID: " + dto.pacienteId())));
        i.setMotivo(dto.motivo());
        i.setStatus(dto.status());
    }

    private Leito buscarLeito(Long id) {
        return leitos.findByIdWithQuarto(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leito não encontrado. ID: " + id));
    }

    private void atualizarOcupacao(StatusInternacao statusAnterior, Leito leitoAnterior,
            Leito destino, StatusInternacao novoStatus) {
        boolean mantemMesmoLeito = statusAnterior == StatusInternacao.INTERNADO
                && leitoAnterior.getId().equals(destino.getId());
        if (novoStatus == StatusInternacao.INTERNADO && !mantemMesmoLeito
                && destino.getStatus() != StatusLeito.DISPONIVEL) {
            throw new ConflictException("O leito selecionado não está disponível.");
        }
        if (novoStatus == StatusInternacao.INTERNADO && !mantemMesmoLeito) {
            destino.setStatus(StatusLeito.OCUPADO);
            leitos.save(destino);
        }
    }

    private void ajustarDataAlta(InternacaoRequestDTO dto, Internacao internacao) {
        if (dto.status() == StatusInternacao.INTERNADO) {
            internacao.setDataAlta(null);
        } else {
            internacao.setDataAlta(dto.dataAlta() != null ? dto.dataAlta() : LocalDateTime.now());
        }
    }
}
