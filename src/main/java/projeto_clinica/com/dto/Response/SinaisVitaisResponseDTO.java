package projeto_clinica.com.dto.Response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import projeto_clinica.com.model.SinaisVitais;

public record SinaisVitaisResponseDTO(
    Long id,
    Long pacienteId,
    Long funcionarioId,
    LocalDateTime dataHora,
    BigDecimal temperatura,
    String pressaoArterial,
    Integer frequenciaCardiaca,
    BigDecimal saturacaoOxigenio,
    String observacoes
) {

    public SinaisVitaisResponseDTO(SinaisVitais entity) {
        this(
            entity.getId(),
            entity.getPaciente().getId(),
            entity.getFuncionario().getId(),
            entity.getDataHora(),
            entity.getTemperatura(),
            entity.getPressaoArterial(),
            entity.getFrequenciaCardiaca(),
            entity.getSaturacaoOxigenio(),
            entity.getObservacoes()
        );
    }
}
