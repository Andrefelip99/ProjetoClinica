package projeto_clinica.com.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dataDeNascimento;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String endereco;

    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas;

    @Column(unique = true)
    private String email;

    private String tipoSanguineo;

    

    


    public Paciente(Long id, String nome, String cpf, LocalDate dataDeNascimento, String telefone,
            String endereco, List<Consulta> consultas, String email, String tipoSanguineo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.consultas = consultas;
        this.email = email;
        this.tipoSanguineo = tipoSanguineo;
    }






    @Transient
    public Integer getIdade() {
        if (dataDeNascimento == null) {
            return null;
        }

        return Period.between(dataDeNascimento, LocalDate.now()).getYears();
    }

}
