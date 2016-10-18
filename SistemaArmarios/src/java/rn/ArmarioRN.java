
package rn;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Armario;
@Stateless
public class ArmarioRN extends AbstractRN<Armario> {
@PersistenceContext(unitName="ArmariosJPAPU")
private EntityManager manager;

public ArmarioRN(){
    super(Armario.class);
}
    @Override
    protected EntityManager getEntityManager() {
        return manager;
    }
    
    public void salvar(Armario a){
        //validar parametros
        if(a.getId()==null){
            super.adicionar(a);
        }else{
            super.atualizar(a);
        }
    }
    
}
