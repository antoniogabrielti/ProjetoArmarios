
package rn;

import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import model.Aluno;
import model.Usuario;

@Stateless
public class UsuarioRN extends AbstractRN<Usuario> {
@PersistenceContext(unitName="ArmariosJPAPU")
private EntityManager manager;
private Usuario usuarioLogado; 

public UsuarioRN(){
    super(Usuario.class);
}
    @Override
    protected EntityManager getEntityManager() {
        return manager;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
    
    public void salvar(Usuario u){
      //validar parametros
      if(u.getId()==null){
        super.adicionar(u);
      }else{
        super.atualizar(u);
      }
    }
    
    public String validaLogin(String login,String senha){

        FacesContext contexto = FacesContext.getCurrentInstance();

        List<Usuario> listaUsuarios = super.listar();

        for (Usuario usuario : listaUsuarios) {
            if (usuario.verificaLogin(login, senha)) {
                usuarioLogado = usuario;
                if (usuario.isEFuncionario()) {
                    return ("admin/index?faces-redirect=true");
                } else {
                    return ("aluno/index?faces-redirect=true");
                }
            }
        }
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Login inválido!", "Usuário ou senha estão errados!");
        contexto.addMessage("idMensagem", mensagem);
        return ("/login");
    }
    
    public List<Aluno> buscaFiltroNomeAluno(String busca){
       String buscasql=busca.toLowerCase();
       CriteriaBuilder cb = manager.getCriteriaBuilder();
       CriteriaQuery<Aluno> cq = cb.createQuery(Aluno.class);
       Root <Aluno> a = cq.from(Aluno.class);
       cq.select(a);       
       Predicate predicate = cb.like(cb.lower(a.<String>get("nome")), "%"+buscasql+"%");
       cq.where(predicate);
       return (manager.createQuery(cq).getResultList());
    }
    
    public List<Aluno> buscaFiltroMatriculaAluno(String busca){
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Aluno> cq = cb.createQuery(Aluno.class);
        Root <Aluno> a = cq.from(Aluno.class);
        cq.select(a);
        Predicate predicate = cb.equal(a.get("matricula"), busca);
        cq.where(predicate);
        return (manager.createQuery(cq).getResultList());
    }
    
}
