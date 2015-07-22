package model;

import entities.Productoxcliente;
import model.util.JsfUtil;
import model.util.JsfUtil.PersistAction;
import view.ProductoxclienteFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "productoxclienteController")
@SessionScoped
public class ProductoxclienteController implements Serializable {

    @EJB
    private view.ProductoxclienteFacade ejbFacade;
    private List<Productoxcliente> items = null;
    private Productoxcliente selected;

    public ProductoxclienteController() {
    }

    public Productoxcliente getSelected() {
        return selected;
    }

    public void setSelected(Productoxcliente selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getProductoxclientePK().setNit(selected.getCliente().getNit());
        selected.getProductoxclientePK().setIdproducto(selected.getProducto().getIdproducto());
    }

    protected void initializeEmbeddableKey() {
        selected.setProductoxclientePK(new entities.ProductoxclientePK());
    }

    private ProductoxclienteFacade getFacade() {
        return ejbFacade;
    }

    public Productoxcliente prepareCreate() {
        selected = new Productoxcliente();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProductoxclienteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProductoxclienteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProductoxclienteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Productoxcliente> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Productoxcliente> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Productoxcliente> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Productoxcliente.class)
    public static class ProductoxclienteControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProductoxclienteController controller = (ProductoxclienteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "productoxclienteController");
            return controller.getFacade().find(getKey(value));
        }

        entities.ProductoxclientePK getKey(String value) {
            entities.ProductoxclientePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entities.ProductoxclientePK();
            key.setIdproducto(Integer.parseInt(values[0]));
            key.setNit(values[1]);
            return key;
        }

        String getStringKey(entities.ProductoxclientePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdproducto());
            sb.append(SEPARATOR);
            sb.append(value.getNit());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Productoxcliente) {
                Productoxcliente o = (Productoxcliente) object;
                return getStringKey(o.getProductoxclientePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Productoxcliente.class.getName()});
                return null;
            }
        }

    }

}
