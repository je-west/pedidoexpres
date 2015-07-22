/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Valentina
 */
@Entity
@Table(name = "medio_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedioPago.findAll", query = "SELECT m FROM MedioPago m"),
    @NamedQuery(name = "MedioPago.findByIdmedPago", query = "SELECT m FROM MedioPago m WHERE m.idmedPago = :idmedPago"),
    @NamedQuery(name = "MedioPago.findByDescripcion", query = "SELECT m FROM MedioPago m WHERE m.descripcion = :descripcion")})
public class MedioPago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmed_pago")
    private Integer idmedPago;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idmedPago")
    private List<Pedido> pedidoList;

    public MedioPago() {
    }

    public MedioPago(Integer idmedPago) {
        this.idmedPago = idmedPago;
    }

    public Integer getIdmedPago() {
        return idmedPago;
    }

    public void setIdmedPago(Integer idmedPago) {
        this.idmedPago = idmedPago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmedPago != null ? idmedPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedioPago)) {
            return false;
        }
        MedioPago other = (MedioPago) object;
        if ((this.idmedPago == null && other.idmedPago != null) || (this.idmedPago != null && !this.idmedPago.equals(other.idmedPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MedioPago[ idmedPago=" + idmedPago + " ]";
    }
    
}
