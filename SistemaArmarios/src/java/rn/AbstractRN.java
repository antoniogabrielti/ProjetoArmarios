
package rn;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;


public abstract class AbstractRN<T> {
    private Class<T> entidadeClasse;
    protected abstract EntityManager getEntityManager();
    
    public AbstractRN(Class<T> entidadeClasse){
        this.entidadeClasse=entidadeClasse;
    }
    
    public void adicionar(T entidade){
        getEntityManager().persist(entidade);
    }
    
    public void atualizar(T entidade){
        getEntityManager().merge(entidade);
    }
    
    public void remover (T entidade){
        getEntityManager().remove(getEntityManager().merge(entidade));
    }
    
    public T buscar(Long id){
        return getEntityManager().find(entidadeClasse, id);
    }
    
    public List<T> listar(){
       CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
       cq.select(cq.from(entidadeClasse));
       return (getEntityManager().createQuery(cq).getResultList());
    }
}
