
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
import rn.ArmarioRN;
import rn.EmprestimoRN;
import rn.UsuarioRN;
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
    EmprestimoRN emprestimoRN;
    
    @Inject
    UsuarioRN userRN;
    
    @Inject
    ArmarioRN armarioRN;
    
    public EmprestimoMB() throws ParseException{
        armarioSelecionado=new Armario();
        alunoSelecionado=new Aluno();
        ListaAlunos=new ArrayList<Aluno>();
        ListaEmprestimos = new ArrayList<Emprestimo>();
    }

    public List<Emprestimo> getListaEmprestimos() {
        return emprestimoRN.listar();
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
    
    public String EmprestimoFormulario(Aluno al){
        this.alunoSelecionado= new Aluno();
        this.setAlunoSelecionado(al);
        return"formEmprestimo.xhtml";
    }
    
    public String finalizarEmprestimo() throws ParseException{
        Emprestimo emp = new Emprestimo();
        this.armarioSelecionado.setEstaDisponivel(false);
        armarioRN.salvar(armarioSelecionado);
        emp.setArmario(this.armarioSelecionado);
        emp.setProprietario(this.alunoSelecionado);
        Date dataEmprestimo = stringToDate(this.dtEmprestimo);
        emp.setDataEmprestimo(dataEmprestimo);
        Date dataDevolucao = stringToDate(this.dtDevolucao);
        emp.setDataDevolucao(dataDevolucao);
        
        emprestimoRN.salvar(emp);
        return "/admin/crudEmprestimo/visualizarEmprestimos?faces-redirect=true";
    }
    
    public String processarData(Date data){
        this.dtProcessada = dateToString(data);
        return this.dtProcessada;
    }
    
    public String devolucao(Emprestimo emprestimo){
        emprestimo.getArmario().setEstaDisponivel(true);
        armarioRN.salvar(emprestimo.getArmario());
        emprestimoRN.remover(emprestimo);
        return "/admin/crudEmprestimo/visualizarEmprestimos?faces-redirect=true";
    }
    
    public String resultadoBuscaAluno(){
        this.ListaAlunos = new ArrayList<Aluno>();
        ListaAlunos = userRN.buscaFiltroMatriculaAluno(ElementoBusca);
        if(ListaAlunos.isEmpty()){
            ListaAlunos = userRN.buscaFiltroNomeAluno(ElementoBusca);
            if(ListaAlunos.isEmpty()){
                this.setElementoBusca("");
                return "buscaAluno?faces-redirect=true";
            }else{
                return "resultadoAluno?faces-redirect=true";
            }
        }else{
          return "resultadoAluno?faces-redirect=true";
        }
    }
}
