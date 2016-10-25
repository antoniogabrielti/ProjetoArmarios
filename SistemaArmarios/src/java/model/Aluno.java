
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="aluno")
@PrimaryKeyJoinColumn(name="id")
public class Aluno extends Usuario{

    private String nome;
    private String email;
    private String telefone;
    private String matricula;
    
    public Aluno(){
        
    }

    public Aluno(String nome, String email, String telefone, String matricula) {
        this.nome=nome;
        this.email=email;
        this.telefone=telefone;
        this.matricula=matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }    
}
