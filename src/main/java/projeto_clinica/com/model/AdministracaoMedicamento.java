package projeto_clinica.com.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import projeto_clinica.com.model.enums.StatusAdministracao;

@Entity
@Table(name = "administracoes_medicamentos")
@Getter
@Setter
@NoArgsConstructor
public class AdministracaoMedicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "prescricao_item_id", nullable = false)
    private PrescricaoItem prescricaoItem;
    @ManyToOne(optional = false)
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;
    @Column(nullable = false)
    private LocalDateTime dataHora = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusAdministracao status = StatusAdministracao.PENDENTE;
    @Column(length = 1000)
    private String observacao;
}
