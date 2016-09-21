
package bean;
import model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import model.Aluno;
import model.Funcionario;

@Named
@ApplicationScoped 
public class UsuarioMB {


    private List<Usuario> listaUsuarios;
    private Usuario usuarioSelecionado;

    public UsuarioMB() {
        usuarioSelecionado = new Usuario();
        listaUsuarios = new ArrayList<Usuario>();
        Funcionario f = new Funcionario();
        f.setLogin("admin");
        f.setSenha("admin");
        listaUsuarios.add(f);
    }
    
    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    

    public String novoUsuario(int opcao){
        if(opcao==1){
            usuarioSelecionado=new Funcionario();
            return("/admin/formularioCadastro?faces-redirect=true");
        }else{
            usuarioSelecionado=new Aluno();
            return("/admin/formularioCadastro?faces-redirect=true");
        }
    }

    public String adicionarUsuario(int opcao)
    {
        listaUsuarios.add(usuarioSelecionado);
        return(this.novoUsuario(opcao));
    }

    public String editarUsuario(Usuario u){
        if(u.isEFuncionario()){
            usuarioSelecionado=u;
            return("/admin/formularioCadastro?faces-redirect=true");
        }else{
            usuarioSelecionado=u;
            return("/admin/formularioCadastro?faces-redirect=true");
        }
    }
    public String atualizarUsuario()
    {
        return("/admin/index?faces-redirect=true");
    }

    public void removerUsuario(Usuario usuario){
        listaUsuarios.remove(usuario);
    }
}
