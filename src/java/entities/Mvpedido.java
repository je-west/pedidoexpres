/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Valentina
 */
@Entity
@Table(name = "mvpedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mvpedido.findAll", query = "SELECT m FROM Mvpedido m"),
    @NamedQuery(name = "Mvpedido.findByIdmvpedido", query = "SELECT m FROM Mvpedido m WHERE m.idmvpedido = :idmvpedido"),
    @NamedQuery(name = "Mvpedido.findByCantidad", query = "SELECT m FROM Mvpedido m WHERE m.cantidad = :cantidad"),
    @NamedQuery(name = "Mvpedido.findByValorunit", query = "SELECT m FROM Mvpedido m WHERE m.valorunit = :valorunit")})
public class Mvpedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmvpedido")
    private Integer idmvpedido;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorunit")
    private Double valorunit;
    @JoinColumn(name = "idpedido", referencedColumnName = "idpedido")
    @ManyToOne
    private Pedido idpedido;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto idproducto;

    public Mvpedido() {
    }

    public Mvpedido(Integer idmvpedido) {
        this.idmvpedido = idmvpedido;
    }

    public Integer getIdmvpedido() {
        return idmvpedido;
    }

    public void setIdmvpedido(Integer idmvpedido) {
        this.idmvpedido = idmvpedido;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getValorunit() {
        return valorunit;
    }

    public void setValorunit(Double valorunit) {
        this.valorunit = valorunit;
    }

    public Pedido getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Pedido idpedido) {
        this.idpedido = idpedido;
    }

    public Producto getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Producto idproducto) {
        this.idproducto = idproducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmvpedido != null ? idmvpedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mvpedido)) {
            return false;
        }
        Mvpedido other = (Mvpedido) object;
        if ((this.idmvpedido == null && other.idmvpedido != null) || (this.idmvpedido != null && !this.idmvpedido.equals(other.idmvpedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Mvpedido[ idmvpedido=" + idmvpedido + " ]";
    }
    
}
