/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Logestado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Valentina
 */
@Stateless
public class LogestadoFacade extends AbstractFacade<Logestado> {
    @PersistenceContext(unitName = "pedidoexpresPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LogestadoFacade() {
        super(Logestado.class);
    }
    
}
