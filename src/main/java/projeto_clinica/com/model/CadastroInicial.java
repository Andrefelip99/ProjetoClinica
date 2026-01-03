package projeto_clinica.com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class CadastroInicial {
    @Id
    @GeneratedValue
    private long id;

    private String nome;
    private String email;
    private String senha;
    
 
}
