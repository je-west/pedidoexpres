/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Valentina
 */
@Entity
@Table(name = "logestado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logestado.findAll", query = "SELECT l FROM Logestado l"),
    @NamedQuery(name = "Logestado.findByIdestado", query = "SELECT l FROM Logestado l WHERE l.logestadoPK.idestado = :idestado"),
    @NamedQuery(name = "Logestado.findByFechaHora", query = "SELECT l FROM Logestado l WHERE l.fechaHora = :fechaHora"),
    @NamedQuery(name = "Logestado.findByIdpedido", query = "SELECT l FROM Logestado l WHERE l.logestadoPK.idpedido = :idpedido")})
public class Logestado implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LogestadoPK logestadoPK;
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @JoinColumn(name = "idestado", referencedColumnName = "idestado", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Estado estado;
    @JoinColumn(name = "idpedido", referencedColumnName = "idpedido", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedido pedido;

    public Logestado() {
    }

    public Logestado(LogestadoPK logestadoPK) {
        this.logestadoPK = logestadoPK;
    }

    public Logestado(int idestado, int idpedido) {
        this.logestadoPK = new LogestadoPK(idestado, idpedido);
    }

    public LogestadoPK getLogestadoPK() {
        return logestadoPK;
    }

    public void setLogestadoPK(LogestadoPK logestadoPK) {
        this.logestadoPK = logestadoPK;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logestadoPK != null ? logestadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logestado)) {
            return false;
        }
        Logestado other = (Logestado) object;
        if ((this.logestadoPK == null && other.logestadoPK != null) || (this.logestadoPK != null && !this.logestadoPK.equals(other.logestadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Logestado[ logestadoPK=" + logestadoPK + " ]";
    }
    
}
