package projeto_clinica.com.dto.Response;

import java.time.LocalDateTime;

import projeto_clinica.com.model.Internacao;

public record InternacaoResponseDTO(
        Long id,
        Long pacienteId,
        Long leitoId,
        String motivo,
        LocalDateTime dataAlta,
        LocalDateTime dataEntrada,
        String status) {

    public InternacaoResponseDTO(Internacao internacao) {
        this(internacao.getId(),
                internacao.getPaciente().getId(),
                internacao.getLeito().getId(),
                internacao.getMotivo(),
                internacao.getDataAlta(),
                internacao.getDataEntrada(),
                internacao.getStatus() != null ? internacao.getStatus().toString() : null);
    }

}
