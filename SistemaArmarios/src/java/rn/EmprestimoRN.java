
package rn;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}
