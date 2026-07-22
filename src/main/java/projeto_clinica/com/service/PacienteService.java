package projeto_clinica.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import projeto_clinica.com.dto.Request.PacienteRequestDTO;
import projeto_clinica.com.dto.Response.PacienteResponseDTO;
import projeto_clinica.com.model.Paciente;
import projeto_clinica.com.repository.PacienteRepository;

@AllArgsConstructor
@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteResponseDTO criarPaciente(PacienteRequestDTO dto) {

        Paciente paciente = new Paciente();
        paciente.setNome(dto.nome());
        paciente.setCpf(dto.cpf());
        paciente.setDataDeNascimento(dto.dataDeNascimento());
        paciente.setTelefone(dto.telefone());
        paciente.setEndereco(dto.endereco());

        paciente = pacienteRepository.save(paciente);

        return new PacienteResponseDTO(paciente);
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public PacienteResponseDTO atualizarPaciente(Long id, PacienteRequestDTO dto) {

        return pacienteRepository.findById(id).map(paciente -> {

            paciente.setNome(dto.nome());
            paciente.setCpf(dto.cpf());
            paciente.setDataDeNascimento(dto.dataDeNascimento());
            paciente.setTelefone(dto.telefone());
            paciente.setEndereco(dto.endereco());

            paciente = pacienteRepository.save(paciente);

            return new PacienteResponseDTO(paciente);

        }).orElse(null);
    }

}