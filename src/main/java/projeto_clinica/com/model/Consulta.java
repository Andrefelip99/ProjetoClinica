package projeto_clinica.com.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import projeto_clinica.com.model.enums.StatusConsulta;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Funcionario medico;

    @Column(length = 500)
    private String observacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusConsulta status;

    public Consulta(Long id, LocalDateTime dataHora, Paciente paciente, Funcionario medico, String observacao,
            StatusConsulta status) {
        this.id = id;
        this.dataHora = dataHora;
        this.paciente = paciente;
        this.medico = medico;
        this.observacao = observacao;
        this.status = status;
    }

    

}
