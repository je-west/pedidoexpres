package model;

import entities.Logestado;
import model.util.JsfUtil;
import model.util.JsfUtil.PersistAction;
import view.LogestadoFacade;

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

@ManagedBean(name = "logestadoController")
@SessionScoped
public class LogestadoController implements Serializable {

    @EJB
    private view.LogestadoFacade ejbFacade;
    private List<Logestado> items = null;
    private Logestado selected;

    public LogestadoController() {
    }

    public Logestado getSelected() {
        return selected;
    }

    public void setSelected(Logestado selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getLogestadoPK().setIdpedido(selected.getPedido().getIdpedido());
        selected.getLogestadoPK().setIdestado(selected.getEstado().getIdestado());
    }

    protected void initializeEmbeddableKey() {
        selected.setLogestadoPK(new entities.LogestadoPK());
    }

    private LogestadoFacade getFacade() {
        return ejbFacade;
    }

    public Logestado prepareCreate() {
        selected = new Logestado();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LogestadoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LogestadoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LogestadoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Logestado> getItems() {
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

    public List<Logestado> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Logestado> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Logestado.class)
    public static class LogestadoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LogestadoController controller = (LogestadoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "logestadoController");
            return controller.getFacade().find(getKey(value));
        }

        entities.LogestadoPK getKey(String value) {
            entities.LogestadoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entities.LogestadoPK();
            key.setIdestado(Integer.parseInt(values[0]));
            key.setIdpedido(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(entities.LogestadoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdestado());
            sb.append(SEPARATOR);
            sb.append(value.getIdpedido());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Logestado) {
                Logestado o = (Logestado) object;
                return getStringKey(o.getLogestadoPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Logestado.class.getName()});
                return null;
            }
        }

    }

}
