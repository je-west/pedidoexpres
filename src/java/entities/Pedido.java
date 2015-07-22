/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Valentina
 */
@Entity
@Table(name = "pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findByIdpedido", query = "SELECT p FROM Pedido p WHERE p.idpedido = :idpedido"),
    @NamedQuery(name = "Pedido.findByFechaHoraEnt", query = "SELECT p FROM Pedido p WHERE p.fechaHoraEnt = :fechaHoraEnt"),
    @NamedQuery(name = "Pedido.findByFechaHoraSol", query = "SELECT p FROM Pedido p WHERE p.fechaHoraSol = :fechaHoraSol"),
    @NamedQuery(name = "Pedido.findByLatitudSol", query = "SELECT p FROM Pedido p WHERE p.latitudSol = :latitudSol"),
    @NamedQuery(name = "Pedido.findByLongitudSol", query = "SELECT p FROM Pedido p WHERE p.longitudSol = :longitudSol"),
    @NamedQuery(name = "Pedido.findByLatitudEnt", query = "SELECT p FROM Pedido p WHERE p.latitudEnt = :latitudEnt"),
    @NamedQuery(name = "Pedido.findByLongitudEnt", query = "SELECT p FROM Pedido p WHERE p.longitudEnt = :longitudEnt"),
    @NamedQuery(name = "Pedido.findByDireccionEnt", query = "SELECT p FROM Pedido p WHERE p.direccionEnt = :direccionEnt"),
    @NamedQuery(name = "Pedido.findByValorBruto", query = "SELECT p FROM Pedido p WHERE p.valorBruto = :valorBruto"),
    @NamedQuery(name = "Pedido.findByDescuento", query = "SELECT p FROM Pedido p WHERE p.descuento = :descuento"),
    @NamedQuery(name = "Pedido.findByIva", query = "SELECT p FROM Pedido p WHERE p.iva = :iva"),
    @NamedQuery(name = "Pedido.findByFechaHoraDes", query = "SELECT p FROM Pedido p WHERE p.fechaHoraDes = :fechaHoraDes"),
    @NamedQuery(name = "Pedido.findByObservacion", query = "SELECT p FROM Pedido p WHERE p.observacion = :observacion"),
    @NamedQuery(name = "Pedido.findByCalificacion", query = "SELECT p FROM Pedido p WHERE p.calificacion = :calificacion")})
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpedido")
    private Integer idpedido;
    @Column(name = "fecha_hora_ent")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraEnt;
    @Column(name = "fecha_hora_sol")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraSol;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitud_sol")
    private Double latitudSol;
    @Column(name = "longitud_sol")
    private Double longitudSol;
    @Column(name = "latitud_ent")
    private Double latitudEnt;
    @Column(name = "longitud_ent")
    private Double longitudEnt;
    @Size(max = 2147483647)
    @Column(name = "direccion_ent")
    private String direccionEnt;
    @Column(name = "valor_bruto")
    private Integer valorBruto;
    @Column(name = "descuento")
    private Double descuento;
    @Column(name = "iva")
    private Double iva;
    @Column(name = "fecha_hora_des")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraDes;
    @Size(max = 2147483647)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "calificacion")
    private Integer calificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<Logestado> logestadoList;
    @OneToMany(mappedBy = "idpedido")
    private List<Mvpedido> mvpedidoList;
    @JoinColumn(name = "nit", referencedColumnName = "nit")
    @ManyToOne
    private Cliente nit;
    @JoinColumn(name = "iddomicilio", referencedColumnName = "iddomicilio")
    @ManyToOne
    private Domicilio iddomicilio;
    @JoinColumn(name = "idestado", referencedColumnName = "idestado")
    @ManyToOne
    private Estado idestado;
    @JoinColumn(name = "idmed_pago", referencedColumnName = "idmed_pago")
    @ManyToOne
    private MedioPago idmedPago;
    @JoinColumn(name = "usuario", referencedColumnName = "usuario")
    @ManyToOne
    private Usuario usuario;

    public Pedido() {
    }

    public Pedido(Integer idpedido) {
        this.idpedido = idpedido;
    }

    public Integer getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Integer idpedido) {
        this.idpedido = idpedido;
    }

    public Date getFechaHoraEnt() {
        return fechaHoraEnt;
    }

    public void setFechaHoraEnt(Date fechaHoraEnt) {
        this.fechaHoraEnt = fechaHoraEnt;
    }

    public Date getFechaHoraSol() {
        return fechaHoraSol;
    }

    public void setFechaHoraSol(Date fechaHoraSol) {
        this.fechaHoraSol = fechaHoraSol;
    }

    public Double getLatitudSol() {
        return latitudSol;
    }

    public void setLatitudSol(Double latitudSol) {
        this.latitudSol = latitudSol;
    }

    public Double getLongitudSol() {
        return longitudSol;
    }

    public void setLongitudSol(Double longitudSol) {
        this.longitudSol = longitudSol;
    }

    public Double getLatitudEnt() {
        return latitudEnt;
    }

    public void setLatitudEnt(Double latitudEnt) {
        this.latitudEnt = latitudEnt;
    }

    public Double getLongitudEnt() {
        return longitudEnt;
    }

    public void setLongitudEnt(Double longitudEnt) {
        this.longitudEnt = longitudEnt;
    }

    public String getDireccionEnt() {
        return direccionEnt;
    }

    public void setDireccionEnt(String direccionEnt) {
        this.direccionEnt = direccionEnt;
    }

    public Integer getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(Integer valorBruto) {
        this.valorBruto = valorBruto;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Date getFechaHoraDes() {
        return fechaHoraDes;
    }

    public void setFechaHoraDes(Date fechaHoraDes) {
        this.fechaHoraDes = fechaHoraDes;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    @XmlTransient
    public List<Logestado> getLogestadoList() {
        return logestadoList;
    }

    public void setLogestadoList(List<Logestado> logestadoList) {
        this.logestadoList = logestadoList;
    }

    @XmlTransient
    public List<Mvpedido> getMvpedidoList() {
        return mvpedidoList;
    }

    public void setMvpedidoList(List<Mvpedido> mvpedidoList) {
        this.mvpedidoList = mvpedidoList;
    }

    public Cliente getNit() {
        return nit;
    }

    public void setNit(Cliente nit) {
        this.nit = nit;
    }

    public Domicilio getIddomicilio() {
        return iddomicilio;
    }

    public void setIddomicilio(Domicilio iddomicilio) {
        this.iddomicilio = iddomicilio;
    }

    public Estado getIdestado() {
        return idestado;
    }

    public void setIdestado(Estado idestado) {
        this.idestado = idestado;
    }

    public MedioPago getIdmedPago() {
        return idmedPago;
    }

    public void setIdmedPago(MedioPago idmedPago) {
        this.idmedPago = idmedPago;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpedido != null ? idpedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idpedido == null && other.idpedido != null) || (this.idpedido != null && !this.idpedido.equals(other.idpedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pedido[ idpedido=" + idpedido + " ]";
    }
    
}
