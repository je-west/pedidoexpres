/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Valentina
 */
@Entity
@Table(name = "productoxcliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productoxcliente.findAll", query = "SELECT p FROM Productoxcliente p"),
    @NamedQuery(name = "Productoxcliente.findByIdproducto", query = "SELECT p FROM Productoxcliente p WHERE p.productoxclientePK.idproducto = :idproducto"),
    @NamedQuery(name = "Productoxcliente.findByPrecio", query = "SELECT p FROM Productoxcliente p WHERE p.precio = :precio"),
    @NamedQuery(name = "Productoxcliente.findByImagen", query = "SELECT p FROM Productoxcliente p WHERE p.imagen = :imagen"),
    @NamedQuery(name = "Productoxcliente.findByNit", query = "SELECT p FROM Productoxcliente p WHERE p.productoxclientePK.nit = :nit"),
    @NamedQuery(name = "Productoxcliente.findByDescripcion", query = "SELECT p FROM Productoxcliente p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Productoxcliente.findByDetalle", query = "SELECT p FROM Productoxcliente p WHERE p.detalle = :detalle"),
    @NamedQuery(name = "Productoxcliente.findByHabilitado", query = "SELECT p FROM Productoxcliente p WHERE p.habilitado = :habilitado")})
public class Productoxcliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductoxclientePK productoxclientePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @Size(max = 2147483647)
    @Column(name = "imagen")
    private String imagen;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 2147483647)
    @Column(name = "detalle")
    private String detalle;
    @Column(name = "habilitado")
    private Boolean habilitado;
    @JoinColumn(name = "nit", referencedColumnName = "nit", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;
    @JoinColumn(name = "idtipo", referencedColumnName = "idtipo")
    @ManyToOne
    private Tipo idtipo;

    public Productoxcliente() {
    }

    public Productoxcliente(ProductoxclientePK productoxclientePK) {
        this.productoxclientePK = productoxclientePK;
    }

    public Productoxcliente(int idproducto, String nit) {
        this.productoxclientePK = new ProductoxclientePK(idproducto, nit);
    }

    public ProductoxclientePK getProductoxclientePK() {
        return productoxclientePK;
    }

    public void setProductoxclientePK(ProductoxclientePK productoxclientePK) {
        this.productoxclientePK = productoxclientePK;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Tipo getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(Tipo idtipo) {
        this.idtipo = idtipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productoxclientePK != null ? productoxclientePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productoxcliente)) {
            return false;
        }
        Productoxcliente other = (Productoxcliente) object;
        if ((this.productoxclientePK == null && other.productoxclientePK != null) || (this.productoxclientePK != null && !this.productoxclientePK.equals(other.productoxclientePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Productoxcliente[ productoxclientePK=" + productoxclientePK + " ]";
    }
    
}
