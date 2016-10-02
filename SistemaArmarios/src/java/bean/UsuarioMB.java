
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
    private Aluno usuarioAluno;
    private Funcionario usuarioFuncionario;
    private Aluno usuarioSelecionadoAluno;
    private Funcionario usuarioSelecionadoFunc;

    public UsuarioMB() {
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
    
    public Aluno getUsuarioSelecionadoAluno() {
            return this.usuarioSelecionadoAluno;
    }

    public Aluno getUsuarioAluno() {
        return usuarioAluno;
    }

    public void setUsuarioAluno(Aluno usuarioAluno) {
        this.usuarioAluno = usuarioAluno;
    }

    public Funcionario getUsuarioFuncionario() {
        return usuarioFuncionario;
    }

    public void setUsuarioFuncionario(Funcionario usuarioFuncionario) {
        this.usuarioFuncionario = usuarioFuncionario;
    }
    
    

    
    public Funcionario getUsuarioSelecionadoFunc(){
        return this.usuarioSelecionadoFunc;
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
    

    public String novoAluno(){
            usuarioAluno=new Aluno();
            return("crudAluno/formularioAlunoCadastro.xhtml?faces-redirect=true");
        
    }

    public String adicionarAluno()
    {
        listaUsuarios.add(usuarioAluno);
        return("/admin/crudAluno/visualizarAlunos?faces-redirect=true");
    }

    public String editarUsuario(Usuario u){
        if(u.isEFuncionario()){
            this.usuarioSelecionadoFunc=new Funcionario();
            usuarioSelecionadoFunc=(Funcionario)u;
            return("/admin/formularioEditar?faces-redirect=true");
        }else{
            this.usuarioSelecionadoAluno=new Aluno();
            usuarioSelecionadoAluno=(Aluno)u;
            return("/admin/crudAluno/formularioAlunoEditar?faces-redirect=true");
        }
    }
    public String atualizarAluno()
    {
        return("/admin/crudAluno/visualizarAlunos?faces-redirect=true");
    }

    public void removerUsuario(Usuario usuario){
        listaUsuarios.remove(usuario);
    }
}
