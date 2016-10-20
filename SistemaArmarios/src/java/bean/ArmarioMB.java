package bean;
import model.Armario;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import rn.ArmarioRN;


@Named
@ApplicationScoped 
public class ArmarioMB {

    
    private List<Armario> listaArmarios;
    private Armario novoArmario;
    private Armario armarioSelecionado;
    private boolean estaNoSubmenu;
    
    @Inject
    private ArmarioRN armarioRN;
    
     @Inject
    private EmprestimoMB emprestimoMB;
    


    public ArmarioMB() {
        estaNoSubmenu=false;
        listaArmarios = new ArrayList<Armario>();
        armarioSelecionado=new Armario();
        novoArmario=new Armario();
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
                return("formularioArmarioCadastro.xhtml?faces-redirect=true");
            }else{
                this.estaNoSubmenu=true;
                return("crudArmario/formularioArmarioCadastro.xhtml?faces-redirect=true");   
            }
    }
        public String visualizarArmarios(){
            if(this.estaNoSubmenu){
                return("visualizarArmarios.xhtml?faces-redirect=true");
            }else{
                
            this.estaNoSubmenu=true;
            return("crudArmario/visualizarArmarios.xhtml?faces-redirect=true");
            }

    }
        /*public String ArmariosDisponiveis(){
            String caminho = this.emprestimoMB.menuRealizarEmprestimo(estaNoSubmenu);
            if(caminho.contains("crudEmprestimo")){
                this.estaNoSubmenu=true;
            }else{
                this.estaNoSubmenu=false;
            }
            return caminho;
        }
**/
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
}

