package projeto_clinica.com.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull
    @DecimalMin("25.0")
    @DecimalMax("45.0")
    @Column(nullable = false, precision = 4, scale = 1)
    private BigDecimal temperatura;

    @NotNull
    @Column(nullable = false, length = 20)
    private String pressaoArterial;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer frequenciaCardiaca;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("100.0")
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal saturacaoOxigenio;

    @Column(length = 1000)
    private String observacoes;
}
