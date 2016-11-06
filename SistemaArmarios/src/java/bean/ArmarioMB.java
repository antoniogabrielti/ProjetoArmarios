package bean;
import model.Armario;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Emprestimo;
import rn.ArmarioRN;


@Named
@ApplicationScoped 
public class ArmarioMB {

    
    private List<Armario> listaArmarios;
    private Armario novoArmario;
    private Armario armarioSelecionado;
    private Emprestimo emprestimo;
    private boolean estaNoSubmenu;
    private String numBusca;
    private List<Armario> listaBusca;
    
    @Inject
    private ArmarioRN armarioRN;


    public ArmarioMB() {
        estaNoSubmenu=false;
        listaArmarios = new ArrayList<Armario>();
        listaBusca = new ArrayList<Armario>();
        armarioSelecionado=new Armario();
        novoArmario=new Armario();
        emprestimo=new Emprestimo();
    }

    public List<Armario> getListaBusca() {
        return listaBusca;
    }

    public void setListaBusca(List<Armario> listaBusca) {
        this.listaBusca = listaBusca;
    }
    
    

    public String getNumBusca() {
        return numBusca;
    }

    public void setNumBusca(String numBusca) {
        this.numBusca = numBusca;
    }
    
    
    
    public Armario getArmarioSelecionado() {
            return this.armarioSelecionado;
    }

    public Armario getNovoArmario() {
        return novoArmario;
    }

    public void setNovoArmario(Armario novoArmario) {
        this.novoArmario = novoArmario;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }
    
    

    public List<Armario> getListaArmariosDisponiveis(){
        List<Armario> listaArmarioDisponivel = new ArrayList<Armario>();
        for(Armario arm : this.getListaArmarios()){
            if(arm.isEstaDisponivel()){
                listaArmarioDisponivel.add(arm);
            }
        }
        return listaArmarioDisponivel;
    }


    public List<Armario> getListaArmarios() {
        return armarioRN.listar();
    }

    public void setListaArmarios(List<Armario> listaArmarios) {
        this.listaArmarios = listaArmarios;
    }
    

    public String novoArmario(){
            this.novoArmario=new Armario();
        if(this.estaNoSubmenu){
            return("/admin/crudArmario/formularioArmarioCadastro.xhtml?faces-redirect=true");
        }else{
            this.estaNoSubmenu=true;
            return("/admin/crudArmario/formularioArmarioCadastro.xhtml?faces-redirect=true");
        } 
    }
        public String visualizarArmarios(){
        if(this.estaNoSubmenu){
            return("/admin/crudArmario/visualizarArmarios.xhtml?faces-redirect=true");
        }else{
            this.estaNoSubmenu=true;
            return("/admin/crudArmario/visualizarArmarios.xhtml?faces-redirect=true");
        }
    }

    public String adicionarArmario()
    {
        novoArmario.setEstaDisponivel(true);
        armarioRN.salvar(this.novoArmario);
        return("/admin/crudArmario/visualizarArmarios?faces-redirect=true");
    }

    public String editarArmario(Armario a){
            this.armarioSelecionado=new Armario();
            this.armarioSelecionado=a;
            return("/admin/crudArmario/formularioArmarioEditar?faces-redirect=true");
    }
    public String atualizarArmario()
    {
        armarioRN.salvar(this.armarioSelecionado);
        return("/admin/crudArmario/visualizarArmarios?faces-redirect=true");
    }

    public void removerArmario(Armario armario){
        armarioRN.remover(armario);
    }
    public String getStatus(Armario armario){
        if(armario.isEstaDisponivel()){
            return "Disponível";
        }else{
            return "Ocupado";
        }
    }
    public String getLabel(Armario armario){
        if(armario.isEstaDisponivel()){
            return "label-success";
        }else{
            return "label-danger";
        }
    }
    public String DevolverArmario(Emprestimo emp){
        this.setEmprestimo(emp);
        return ("/admin/crudArmario/visualizarDadosEmprestimo?faces-redirect=true");
    }
    public String irBuscaArmario(){
        this.setNumBusca("");
        return ("/admin/crudArmario/buscaArmario.xhtml?faces-redirect=true");
    }
    public String resultadoBuscaArmario(){
        listaBusca = new ArrayList<Armario>();
        for(Armario a : this.getListaArmarios()){
            String num = String.valueOf(a.getNumero());
            if(num.equals(numBusca)){
                listaBusca.add(a);
                return("resultadoArmario?faces-redirect=true");
            }
        }
        this.setNumBusca("");
        return ("/admin/crudArmario/buscaArmario?faces-redirect=true");
    }
    public String statusButton1(){
        if(this.getListaBusca().get(0).isEstaDisponivel()){
            return "false";
        }else{
            return "true";
        }
    }
        public String statusButton2(){
        if(!this.getListaBusca().get(0).isEstaDisponivel()){
            return "false";
        }else{
            return "true";
        }
    }
}

