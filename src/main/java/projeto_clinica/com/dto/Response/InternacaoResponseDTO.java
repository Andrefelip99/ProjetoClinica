package projeto_clinica.com.dto.Response;

import projeto_clinica.com.model.Internacao;

public record InternacaoResponseDTO(
        Long id,
        Long pacienteId,
        Long leitoId,
        String motivo,
        String dataAlta,
        String dataEntrada,
        String status) {

    public InternacaoResponseDTO(Internacao internacao) {
        this(internacao.getId(),
                internacao.getPaciente().getId(),
                internacao.getLeito().getId(),
                internacao.getMotivo(),
                internacao.getDataAlta().toString(),
                internacao.getDataEntrada().toString(),
                internacao.getStatus().toString());
    }

}
