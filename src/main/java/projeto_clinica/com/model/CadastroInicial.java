package projeto_clinica.com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
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

    
    public CadastroInicial(long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    

}
