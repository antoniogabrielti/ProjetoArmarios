
package rn;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import model.Emprestimo;
@Stateless
public class EmprestimoRN extends AbstractRN<Emprestimo> {
@PersistenceContext(unitName="ArmariosJPAPU")
private EntityManager manager;

public EmprestimoRN(){
    super(Emprestimo.class);
}
    @Override
    protected EntityManager getEntityManager() {
        return manager;
    }
    
    public void salvar(Emprestimo e){
        //validar parametros
        if(e.getId()==null){
            super.adicionar(e);
        }else{
            super.atualizar(e);
        }
    }
    
    public List<Emprestimo> buscaFiltroAluno(Long IDbusca){
       CriteriaBuilder cb = manager.getCriteriaBuilder();
       CriteriaQuery<Emprestimo> cq = cb.createQuery(Emprestimo.class);
       Root <Emprestimo> a = cq.from(Emprestimo.class);
       cq.select(a);       
       Predicate predicate = cb.equal(a.get("proprietario_id"), IDbusca);
       cq.where(predicate);
       return (manager.createQuery(cq).getResultList());
    }
    
    public List<Emprestimo> buscaFiltroArmario(Long IDbusca){
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Emprestimo> cq = cb.createQuery(Emprestimo.class);
        Root <Emprestimo> a = cq.from(Emprestimo.class);
        cq.select(a);
        Predicate predicate = cb.equal(a.get("armario_id"), IDbusca);
        cq.where(predicate);
        return (manager.createQuery(cq).getResultList());
    }
    public List<Emprestimo> buscaDataInicio(Date data){
        java.sql.Date datasql = new java.sql.Date(data.getTime());
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Emprestimo> cq = cb.createQuery(Emprestimo.class);
        Root <Emprestimo> a = cq.from(Emprestimo.class);
        cq.select(a);
        Predicate predicate = cb.equal(a.get("dataemprestimo"), datasql);
        cq.where(predicate);
        return (manager.createQuery(cq).getResultList());
    }
    public List<Emprestimo> buscaDataDevolucao(Date data){
        java.sql.Date datasql = new java.sql.Date(data.getTime());
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Emprestimo> cq = cb.createQuery(Emprestimo.class);
        Root <Emprestimo> a = cq.from(Emprestimo.class);
        cq.select(a);
        Predicate predicate = cb.equal(a.get("datadevolucao"), datasql);
        cq.where(predicate);
        return (manager.createQuery(cq).getResultList());
    }
}
