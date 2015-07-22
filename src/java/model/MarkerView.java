/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Domicilio;
import entities.Pedido;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import view.DomicilioFacade;
import view.PedidoFacade;

/**
 *
 * @author JE-West
 */
@ManagedBean
@RequestScoped
public class MarkerView {

    private MapModel simpleModel;

    public MapModel getSimpleModel() {
        return simpleModel;

    }

    @EJB
    private view.PedidoFacade ejbFacade;

    public PedidoFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(PedidoFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    @PostConstruct

    public void init() {

        List<Pedido> pedido1 = getEjbFacade().cargarPedido();
        LatLng coordenada;

        simpleModel = new DefaultMapModel();
        
        
        for (int i = 0; i <pedido1.size(); i++) {
            
            System.out.println("i"+i);
            
            coordenada = new LatLng(pedido1.get(i).getLatitudSol(), pedido1.get(i).getLongitudSol());

            simpleModel.addOverlay(new Marker(coordenada, "Pedido No "+ pedido1.get(i).getIdpedido()));
            
            System.out.println("i"+i);
            
        }

    }
    /**
     * Creates a new instance of MarkerView
     */
    public MarkerView() {

    }

}
