package projeto_clinica.com.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sinais_vitais")
@Getter
@Setter
@NoArgsConstructor
public class SinaisVitais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHora = LocalDateTime.now();

    @Column(nullable = false, precision = 4, scale = 1)
    private BigDecimal temperatura;

    @Column(nullable = false, length = 20)
    private String pressaoArterial;

    @Column(nullable = false)
    private Integer frequenciaCardiaca;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal saturacaoOxigenio;

    @Column(length = 1000)
    private String observacoes;
}
