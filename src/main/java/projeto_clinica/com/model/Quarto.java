package projeto_clinica.com.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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

    @NotBlank
    @Column(nullable = false, length = 20)
    private String numero;

    @PositiveOrZero
    @Column(nullable = false)
    private int andar;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoQuarto tipo;
}
