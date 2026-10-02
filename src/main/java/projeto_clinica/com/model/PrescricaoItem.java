package projeto_clinica.com.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prescricao_itens")
@Getter
@Setter
@NoArgsConstructor
public class PrescricaoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "prescricao_id", nullable = false)
    private Prescricao prescricao;
    @NotBlank
    @Column(nullable = false, length = 150)
    private String medicamento;
    @NotBlank
    @Column(nullable = false, length = 100)
    private String dosagem;
    @NotBlank
    @Column(nullable = false, length = 100)
    private String frequencia;
    @NotBlank
    @Column(nullable = false, length = 100)
    private String duracao;
    @Column(length = 1000)
    private String observacoes;
}
