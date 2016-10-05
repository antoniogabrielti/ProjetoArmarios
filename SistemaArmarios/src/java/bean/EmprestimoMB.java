
package bean;
import java.text.ParseException;
import model.Armario;
import model.Aluno;
import model.Emprestimo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Usuario;
import static util.DateUtil.dateToString;
import static util.DateUtil.stringToDate;


@Named
@ApplicationScoped 
public class EmprestimoMB {
    private Armario armarioSelecionado;
    private Aluno alunoSelecionado;
    private List<Aluno> ListaAlunos;
    private List<Emprestimo> ListaEmprestimos;
    private String ElementoBusca;
    private String dtProcessada;
    private String dtEmprestimo;
    private String dtDevolucao;
    @Inject
    UsuarioMB userMB;
    
    public EmprestimoMB(){
        armarioSelecionado=new Armario();
        alunoSelecionado=new Aluno();
        ListaAlunos=new ArrayList<Aluno>();
        ListaEmprestimos = new ArrayList<Emprestimo>();
    }

    public List<Emprestimo> getListaEmprestimos() {
        return ListaEmprestimos;
    }

    public void setListaEmprestimos(List<Emprestimo> ListaEmprestimos) {
        this.ListaEmprestimos = ListaEmprestimos;
    }

    
    public String getElementoBusca() {
        return ElementoBusca;
    }

    public List<Aluno> getListaAlunos() {
        return ListaAlunos;
    }

    public void setListaAlunos(List<Aluno> ListaAlunos) {
        this.ListaAlunos = ListaAlunos;
    }

    public String getDtEmprestimo() {
        return dtEmprestimo;
    }

    public void setDtEmprestimo(String dtEmprestimo) {
        this.dtEmprestimo = dtEmprestimo;
    }

    public String getDtDevolucao() {
        return dtDevolucao;
    }

    public void setDtDevolucao(String dtDevolucao) {
        this.dtDevolucao = dtDevolucao;
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
        this.ElementoBusca= new String();
        this.setArmarioSelecionado(armarioSelecionado);
        return("/admin/crudEmprestimo/buscaAluno?faces-redirect=true");
    }
    public List<Aluno> BuscaPorMatricula(String Matricula){
        Aluno AlunoAchou = new Aluno();
        for(Aluno alum : this.userMB.getListaAlunos()){
            if(alum.getMatricula().equalsIgnoreCase(Matricula)){
                    AlunoAchou=alum;
                    this.ListaAlunos.add(AlunoAchou);

                }
            }
                     return this.ListaAlunos;
    }
    public List<Aluno> BuscaPorNome(String Nome){
        Aluno AlunoEncontrou = new Aluno();
        for(Aluno Using : this.userMB.getListaAlunos()){
            int verifica = Using.getNome().indexOf(Nome);
            if(verifica>=0){
                AlunoEncontrou = Using;
                this.ListaAlunos.add(AlunoEncontrou);
            }
        }

            return this.ListaAlunos;
    }
    public String EmprestimoFormulario(Aluno al){
        this.alunoSelecionado= new Aluno();
        this.setAlunoSelecionado(al);
        return"formEmprestimo.xhtml";
    }
    
    public String finalizarEmprestimo() throws ParseException{
        Emprestimo emp = new Emprestimo();
        this.armarioSelecionado.setEstaDisponivel(false);
        emp.setArmario(this.armarioSelecionado);
        emp.setProprietario(this.alunoSelecionado);
        Date dataEmprestimo = stringToDate(this.dtEmprestimo);
        emp.setDataEmprestimo(dataEmprestimo);
        Date dataDevolucao = stringToDate(this.dtDevolucao);
        emp.setDataDevolucao(dataDevolucao);
        ListaEmprestimos.add(emp);
        return "/admin/crudEmprestimo/visualizarEmprestimos?faces-redirect=true";
    }
    
    public String processarData(Date data){
        this.dtProcessada = dateToString(data);
        return this.dtProcessada;
    }
    
    
    
    public String resultadoBuscaAluno(){
        this.ListaAlunos = new ArrayList<Aluno>();
        if(!this.BuscaPorMatricula(this.ElementoBusca).isEmpty() || !this.BuscaPorNome(this.ElementoBusca).isEmpty()){
            return "/admin/crudEmprestimo/resultadoAluno?faces-redirect=true";
        }else{
        return "/admin/crudEmprestimo/buscaAluno?faces-redirect=true";
        }
    }
}
