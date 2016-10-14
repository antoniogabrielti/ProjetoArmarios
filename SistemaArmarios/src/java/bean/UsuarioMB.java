
package bean;
import model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import model.Aluno;
import model.Funcionario;
import rn.UsuarioRN;

@Named
@ApplicationScoped 
public class UsuarioMB {

    private String login, senha;
    private Usuario usuarioLogado;
    private List<Usuario> listaUsuarios;
    private Aluno usuarioAluno;
    private Funcionario usuarioFuncionario;
    private Aluno usuarioSelecionadoAluno;
    private Funcionario usuarioSelecionadoFunc;
    private boolean estaNoSubmenu;
    
    @Inject
    UsuarioRN usuarioRN;

    public UsuarioMB() {
        this.estaNoSubmenu=false;
        listaUsuarios = new ArrayList<Usuario>();
        usuarioAluno = new Aluno();
        usuarioFuncionario = new Funcionario();
        usuarioSelecionadoAluno = new Aluno();
        usuarioSelecionadoFunc = new Funcionario();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
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
    
    public boolean estaLogado() {
        return (usuarioLogado != null);
    }

    public boolean eFuncionario() {
        return this.getUsuarioLogado().isEFuncionario();
    }

    
    public Funcionario getUsuarioSelecionadoFunc(){
        return this.usuarioSelecionadoFunc;
    }


    public List<Funcionario> getListaFuncionario(){
        List<Funcionario> listafunc = new ArrayList<Funcionario>();
        for(Usuario func : this.getListaUsuarios()){
            if(func.isEFuncionario()){
                listafunc.add((Funcionario)func);
            }
        }
        return listafunc;
    }
    public List<Aluno> getListaAlunos(){
        List<Aluno> listaAlunos = new ArrayList<Aluno>();
        for(Usuario al : this.getListaUsuarios()){
            if(!al.isEFuncionario()){
                listaAlunos.add((Aluno)al);
            }
        }
        return listaAlunos;        
    }

    public List<Usuario> getListaUsuarios() {
        this.setListaUsuarios(this.usuarioRN.listar());
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    

    public String novoAluno(){
            usuarioAluno=new Aluno();
            if(this.estaNoSubmenu){
                return("formularioAlunoCadastro?faces-redirect=true"); 
            }else{
                this.estaNoSubmenu=true;
                return("crudAluno/formularioAlunoCadastro.xhtml?faces-redirect=true");
            }
            
        
    }
    public String visualizarAlunos(){
        if(this.estaNoSubmenu){
            return("/admin/crudAluno/visualizarAlunos.xhtml?faces-redirect=true");
        }else{
            this.estaNoSubmenu=true;
            return("/admin/crudAluno/visualizarAlunos.xhtml?faces-redirect=true");
        }
    }

    public String adicionarAluno()
    {
        this.usuarioRN.salvar(usuarioAluno);
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
        if(!this.usuarioSelecionadoAluno.equals(null)){
            this.usuarioRN.salvar(usuarioSelecionadoAluno);
        }else{
            this.usuarioRN.salvar(usuarioSelecionadoFunc);
        }
        return("/admin/crudAluno/visualizarAlunos?faces-redirect=true");
    }

    public void removerUsuario(Usuario usuario){
        this.usuarioRN.remover(usuario);
    }
    public String retornarPrincipal(){
        this.estaNoSubmenu=false;
        return("/SistemaArmarios/faces/admin/index.xhtml");
    }
    
        public String verificaLogin() {
            String resultado = this.usuarioRN.validaLogin(login, senha);
            this.usuarioLogado = this.usuarioRN.getUsuarioLogado();
            return resultado;
        }
          

    public String realizaLogout() {
        String retorno;
        if(usuarioLogado.isEFuncionario()){
            retorno="/faces/login.xhtml?faces-redirect=true";
        }else{
            retorno="login.xhtml?faces-redirect=true";
        }
        usuarioLogado = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return retorno;
    } 
}
