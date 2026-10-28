package projeto_clinica.com.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import projeto_clinica.com.model.enums.StatusLeito;

@Entity
@Table(name = "leitos", uniqueConstraints = @UniqueConstraint(columnNames = { "quarto_id", "numero" }))
@Getter
@Setter
@NoArgsConstructor
public class Leito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String numero;

    @ManyToOne(optional = false)
    @JoinColumn(name = "quarto_id", nullable = false)
    private Quarto quarto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusLeito status = StatusLeito.DISPONIVEL;
}
