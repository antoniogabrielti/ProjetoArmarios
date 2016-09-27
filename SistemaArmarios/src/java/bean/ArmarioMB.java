package bean;
import model.Armario;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


@Named
@ApplicationScoped 
public class ArmarioMB {


    private List<Armario> listaArmarios;
    private Armario novoArmario;
    private Armario armarioSelecionado;


    public ArmarioMB() {
        listaArmarios = new ArrayList<Armario>();
        Armario a = new Armario();
        a.setEstaDisponivel(true);
        a.setNumero(1);
        listaArmarios.add(a);
        Armario b = new Armario();
        b.setEstaDisponivel(true);
        b.setNumero(2);
        listaArmarios.add(b);
        Armario c = new Armario();
        c.setEstaDisponivel(true);
        c.setNumero(3);
        listaArmarios.add(c);
        Armario d = new Armario();
        d.setEstaDisponivel(true);
        d.setNumero(4);
        listaArmarios.add(d);
        Armario e = new Armario();
        e.setEstaDisponivel(false);
        e.setNumero(5);
        listaArmarios.add(e);
        Armario f = new Armario();
        f.setEstaDisponivel(false);
        f.setNumero(6);
        listaArmarios.add(f);
        
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
        for(Armario arm : listaArmarios){
            if(arm.isEstaDisponivel()){
                listaArmarioDisponivel.add(arm);
            }
        }
        return listaArmarioDisponivel;
    }


    public List<Armario> getListaArmarios() {
        return this.listaArmarios;
    }

    public void setListaArmarios(List<Armario> listaArmarios) {
        this.listaArmarios = listaArmarios;
    }
    

    public String novoArmario(){
            this.novoArmario=new Armario();
            return("crudArmario/formularioArmarioCadastro.xhtml?faces-redirect=true");
        
    }

    public String adicionarArmario()
    {
        novoArmario.setEstaDisponivel(true);
        this.listaArmarios.add(this.novoArmario);
        return("/admin/crudArmario/visualizarArmarios?faces-redirect=true");
    }

    public String editarArmario(Armario a){
            this.armarioSelecionado=new Armario();
            this.armarioSelecionado=a;
            return("/admin/crudArmario/formularioArmarioEditar?faces-redirect=true");
    }
    public String atualizarArmario()
    {
        return("/admin/crudArmario/visualizarArmarios?faces-redirect=true");
    }

    public void removerArmario(Armario armario){
        this.listaArmarios.remove(armario);
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

