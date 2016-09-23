
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
        f.setEFuncionario(true);
        listaUsuarios.add(f);
        Aluno a = new Aluno("Jussamara Fillipin","jussa@live.com","(51)8899-7766","1008698-4");
        a.setLogin("aluno");
        a.setSenha("aluno");
        listaUsuarios.add(a);
        Aluno b = new Aluno("Antonio Gabriel Fernandes Miranda","antoniogabrielmiranda@gmail.com","(51)8494-0123","1008587-1");
        b.setLogin("aluno2");
        b.setSenha("aluno2");
        listaUsuarios.add(b);
        Aluno c = new Aluno("Mariana","mariana@terra.com","(51)8240-8543","1008741-9");
        c.setLogin("aluno3");
        c.setSenha("aluno3");
        listaUsuarios.add(c);
        Aluno d = new Aluno("Franck","franck@yahoo.com","(51)9504-3377","1009541-3");
        d.setLogin("aluno4");
        d.setSenha("aluno4");
        listaUsuarios.add(d);
    }
    
    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }
    public List<Funcionario> getListaFuncionario(){
        List<Funcionario> listafunc = new ArrayList<Funcionario>();
        for(Usuario func : listaUsuarios){
            if(func.isEFuncionario()){
                listafunc.add((Funcionario)func);
            }
        }
        return listafunc;
    }
    public List<Aluno> getListaAlunos(){
        List<Aluno> listaAlunos = new ArrayList<Aluno>();
        for(Usuario al : listaUsuarios){
            if(!al.isEFuncionario()){
                listaAlunos.add((Aluno)al);
            }
        }
        return listaAlunos;        
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
            return("/aluno/formularioCadastro?faces-redirect=true");
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
            return("/aluno/formularioCadastro?faces-redirect=true");
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
