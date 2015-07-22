/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Productoxcliente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Valentina
 */
@Stateless
public class ProductoxclienteFacade extends AbstractFacade<Productoxcliente> {
    @PersistenceContext(unitName = "pedidoexpresPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoxclienteFacade() {
        super(Productoxcliente.class);
    }
    
}
