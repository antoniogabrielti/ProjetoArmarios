
package bean;
import model.Armario;
import model.Aluno;
import model.Emprestimo;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Usuario;


@Named
@ApplicationScoped 
public class EmprestimoMB {
    private Armario armarioSelecionado;
    private Aluno alunoSelecionado;
    private List<Aluno> ListaAlunos;
    private String ElementoBusca;
    @Inject
    UsuarioMB userMB;
    
    public EmprestimoMB(){
        armarioSelecionado=new Armario();
        alunoSelecionado=new Aluno();
        ListaAlunos=new ArrayList<Aluno>();
    }

    public String getElementoBusca() {
        return ElementoBusca;
    }

    public void setElementoBusca(String ElementoBusca) {
        this.ElementoBusca = ElementoBusca;
    }

    
    public Armario getArmarioSelecionado() {
        return armarioSelecionado;
    }

    public void setArmarioSelecionado(Armario armarioSelecionado) {
        this.armarioSelecionado = armarioSelecionado;
    }

    public Aluno getAlunoSelecionado() {
        return alunoSelecionado;
    }

    public void setAlunoSelecionado(Aluno alunoSelecionado) {
        this.alunoSelecionado = alunoSelecionado;
    }
    public String SelecionaArmario(Armario armarioSelecionado){
        this.setArmarioSelecionado(armarioSelecionado);
        return("/admin/crudEmprestimo/buscaAluno?faces-redirect=true");
    }
    public List<Aluno> BuscaPorMatricula(String Matricula){
        Aluno AlunoAchou = new Aluno();
        for(Aluno alum : this.userMB.getListaAlunos()){
            if(alum.getMatricula().equalsIgnoreCase(Matricula)){
                    AlunoAchou=alum;
                    this.ListaAlunos.add(AlunoAchou);
                    return this.ListaAlunos;
                }
            }
        return null;
    }
    public List<Aluno> BuscaPorNome(String Nome){
        Aluno AlunoEncontrou = new Aluno();
        for(Aluno Using : this.userMB.getListaAlunos()){
            if(Using.getNome().equalsIgnoreCase(Nome)){
                AlunoEncontrou = Using;
                this.ListaAlunos.add(AlunoEncontrou);
            }
        }
        if(this.ListaAlunos.isEmpty()){
            return null;
        }else{
            return this.ListaAlunos;
        }
    }
    public String ResultadoBuscaAluno(){

        if(!this.BuscaPorMatricula(this.ElementoBusca).equals(null) || !this.BuscaPorNome(this.ElementoBusca).equals(null)){
            return "/admin/crudEmprestimo/resultadoAluno?faces-redirect=true";
        }
        return "/admin/crudEmprestimo/buscaAluno?faces-redirect=true";
    }
}
