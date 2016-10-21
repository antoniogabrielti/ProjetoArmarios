
package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Emprestimo;

@Stateless
public class EmprestimoFacade extends AbstractFacade<Emprestimo> {

    @PersistenceContext(unitName = "ArmariosJPAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmprestimoFacade() {
        super(Emprestimo.class);
    }
    
}

