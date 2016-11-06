
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
import util.DateUtil;
import static util.DateUtil.dateToString;
import static util.DateUtil.stringToDate;


@Named
@ApplicationScoped 
public class EmprestimoMB {
    private Armario armarioSelecionado;
    private Aluno alunoSelecionado;
    private List<Aluno> ListaAlunos;
    private List<Emprestimo> ListaEmprestimos;
    private List<Emprestimo> ListaBuscaEmprestimo;
    private String ElementoBusca;
    private String dtProcessada;
    private String dtEmprestimo;
    private String dtDevolucao;
    private String buscamsg;
    private String opcaoBusca;
    
    @Inject
    EmprestimoRN emprestimoRN;
    
    @Inject
    UsuarioRN userRN;
    
    @Inject
    UsuarioMB userMB;
    
    @Inject
    ArmarioRN armarioRN;
    
    @Inject
    ArmarioMB armarioMB;
    
    public EmprestimoMB() throws ParseException{
        buscamsg = "hidden";
        armarioSelecionado=new Armario();
        alunoSelecionado=new Aluno();
        ListaAlunos=new ArrayList<Aluno>();
        ListaEmprestimos = new ArrayList<Emprestimo>();
        ListaBuscaEmprestimo = new ArrayList<Emprestimo>();
        this.ElementoBusca="";
    }

    public List<Emprestimo> getListaEmprestimos() {
        return emprestimoRN.listar();
    }

    public List<Emprestimo> getListaBuscaEmprestimo() {
        return ListaBuscaEmprestimo;
    }

    public void setListaBuscaEmprestimo(List<Emprestimo> ListaBuscaEmprestimo) {
        this.ListaBuscaEmprestimo = ListaBuscaEmprestimo;
    }

    
    public String getOpcaoBusca() {
        return opcaoBusca;
    }

    public void setOpcaoBusca(String opcaoBusca) {
        this.opcaoBusca = opcaoBusca;
    }
    
    public void setListaEmprestimos(List<Emprestimo> ListaEmprestimos) {
        this.ListaEmprestimos = ListaEmprestimos;
    }

    public String getBuscamsg() {
        return buscamsg;
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
        buscamsg = "hidden";
        this.ElementoBusca= new String();
        this.setArmarioSelecionado(armarioSelecionado);
        return("/admin/crudEmprestimo/buscaAluno?faces-redirect=true");
    }
    
    public String EmprestimoFormulario(Aluno al){
        this.alunoSelecionado= new Aluno();
        this.setAlunoSelecionado(al);
        return"formEmprestimo.xhtml";
    }
        public String novoAluno(){
            userMB.setUsuarioAluno(new Aluno());
            return("/admin/crudEmprestimo/formularioAlunoCadastro.xhtml?faces-redirect=true");    
    }
    
    public String adicionarAluno(Aluno al){
        userRN.salvar(al);
        String result = this.EmprestimoFormulario(al);
        return result;
    }
    
    public String IrBuscaElemento(){
        this.ElementoBusca="";
        this.opcaoBusca="";
        buscamsg = "hidden";
        return("/admin/crudEmprestimo/buscaEmprestimo.xhtml?faces-redirect=true");
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
    public String detalheProprietario(String Matricula){
        userMB.setListaAlunoBusca(this.userRN.buscaFiltroMatriculaAluno(Matricula));
        return "/admin/crudAluno/resultadoAluno.xhtml?faces-redirect=true";
    }
    
    
    public String resultadoBuscaAluno(){
        buscamsg = "hidden";
        this.ListaAlunos = new ArrayList<Aluno>();
        ListaAlunos = userRN.buscaFiltroMatriculaAluno(ElementoBusca);
        if(ListaAlunos.isEmpty()){
            ListaAlunos = userRN.buscaFiltroNomeAluno(ElementoBusca);
            if(ListaAlunos.isEmpty()){
                this.setElementoBusca("");
                buscamsg = "visible";
                return "buscaAluno?faces-redirect=true";
            }else{
                buscamsg = "hidden";
                return "resultadoAluno?faces-redirect=true";
            }
        }else{
          buscamsg = "hidden";
          return "resultadoAluno?faces-redirect=true";
        }
    }
    public Emprestimo buscaEmprestimoArmario(Armario arm){
        for(Emprestimo emp : this.getListaEmprestimos()){
            String numeroArmEmprestimo = String.valueOf(emp.getArmario().getNumero());
            String busca = String.valueOf(arm.getNumero());
            if(numeroArmEmprestimo.equals(busca)){
                return emp;
            }
        }
        return null;
    }
    
    public String buscaEmprestimoFiltros() throws ParseException{
        this.ListaBuscaEmprestimo = new ArrayList<Emprestimo>();
        if(this.opcaoBusca.equals("1")){
            List<Aluno> alunoaux = new ArrayList<Aluno>();
            alunoaux = this.userRN.buscaFiltroNomeAluno(this.ElementoBusca);
            if(!alunoaux.isEmpty()){
                for(Aluno al : alunoaux){
                    for(Emprestimo emp : this.getListaEmprestimos()){
                        if(al.getId().equals(emp.getProprietario().getId())){
                            this.ListaBuscaEmprestimo.add(emp);
                        }
                    }
                }
            }
            this.ElementoBusca="";
            if(!this.ListaBuscaEmprestimo.isEmpty()){
              buscamsg = "hidden";
              return "/admin/crudEmprestimo/visualizarEmprestimoBusca?faces-redirect=true";  
            }else{
              buscamsg = "visible";
              return "/admin/crudEmprestimo/buscaEmprestimo.xhtml?faces-redirect=true";  
            }
        }
        if(this.opcaoBusca.equals("2")){
            List<Aluno> alunoaux = new ArrayList<Aluno>();
            alunoaux = this.userRN.buscaFiltroMatriculaAluno(this.ElementoBusca);
            if(!alunoaux.isEmpty()){
                for(Aluno al : alunoaux){
                    for(Emprestimo emp : this.getListaEmprestimos()){
                        if(al.getId().equals(emp.getProprietario().getId())){
                            this.ListaBuscaEmprestimo.add(emp);
                        }
                    }
                }
            }
            this.ElementoBusca="";
            if(!this.ListaBuscaEmprestimo.isEmpty()){
              buscamsg = "hidden";
              return "/admin/crudEmprestimo/visualizarEmprestimoBusca?faces-redirect=true";  
            }else{
              buscamsg = "visible";
              return "/admin/crudEmprestimo/buscaEmprestimo.xhtml?faces-redirect=true";  
            }
        }
        if(this.opcaoBusca.equals("3")){
            Armario aux = new Armario();
            boolean pesquisa = false;
            for(Armario a : this.armarioMB.getListaArmarios()){
                if(this.ElementoBusca.equals(String.valueOf(a.getNumero()))){
                    aux=a;
                    pesquisa=true;
                }
            }
            if(pesquisa){
            Emprestimo emp = this.buscaEmprestimoArmario(aux);
            if(emp!=null)
            this.ListaBuscaEmprestimo.add(emp);
            }
            this.ElementoBusca="";
            if(!this.ListaBuscaEmprestimo.isEmpty()){
                buscamsg = "hidden";
                return "/admin/crudEmprestimo/visualizarEmprestimoBusca?faces-redirect=true";
            }else{
                buscamsg = "visible";
                return "/admin/crudEmprestimo/buscaEmprestimo.xhtml?faces-redirect=true";
            }
        }
        if(this.opcaoBusca.equals("4")){
            for(Emprestimo empr : this.getListaEmprestimos()){
                String DataEmprestimo = DateUtil.dateToString(empr.getDataEmprestimo());
                if(this.ElementoBusca.equals(DataEmprestimo)){
                    this.ListaBuscaEmprestimo.add(empr);
                }
            }
            this.ElementoBusca="";
            if(!this.ListaBuscaEmprestimo.isEmpty()){
                buscamsg = "hidden";
                return "/admin/crudEmprestimo/visualizarEmprestimoBusca?faces-redirect=true";
            }else{
                buscamsg = "visible";
                return "/admin/crudEmprestimo/buscaEmprestimo.xhtml?faces-redirect=true";
            }
        }
        if(this.opcaoBusca.equals("5")){
            for(Emprestimo empr : this.getListaEmprestimos()){
                String DataEmprestimo = DateUtil.dateToString(empr.getDataDevolucao());
                if(this.ElementoBusca.equals(DataEmprestimo)){
                    this.ListaBuscaEmprestimo.add(empr);
                }
            }
            this.ElementoBusca="";
            if(!this.ListaBuscaEmprestimo.isEmpty()){
                buscamsg = "hidden";
                return "/admin/crudEmprestimo/visualizarEmprestimoBusca?faces-redirect=true";
            }else{
                buscamsg = "visible";
                return "/admin/crudEmprestimo/buscaEmprestimo.xhtml?faces-redirect=true";
            }
        }
        return "";
    }
}
