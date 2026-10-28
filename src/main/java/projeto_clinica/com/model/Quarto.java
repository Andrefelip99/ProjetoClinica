package projeto_clinica.com.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import projeto_clinica.com.model.enums.TipoQuarto;

@Entity
@Table(name = "quartos", uniqueConstraints = @UniqueConstraint(columnNames = { "numero", "andar" }))
@Getter
@Setter
@NoArgsConstructor
public class Quarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String numero;

    @Column(nullable = false)
    private int andar;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoQuarto tipo;
}
