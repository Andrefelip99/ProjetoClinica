package projeto_clinica.com.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import projeto_clinica.com.model.enums.StatusPrescricao;

@Entity
@Table(name = "prescricoes")
@Getter
@Setter
@NoArgsConstructor
public class Prescricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
    @ManyToOne(optional = false)
    @JoinColumn(name = "medico_id", nullable = false)
    private Funcionario medico;
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHora = LocalDateTime.now();
    @Column(length = 2000)
    private String observacoes;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusPrescricao status = StatusPrescricao.ATIVA;
}
