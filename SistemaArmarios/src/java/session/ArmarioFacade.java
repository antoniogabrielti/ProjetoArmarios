/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Armario;

/**
 *
 * @author juliano
 */
@Stateless
public class ArmarioFacade extends AbstractFacade<Armario> {

    @PersistenceContext(unitName = "ArmariosJPAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArmarioFacade() {
        super(Armario.class);
    }
    
}
