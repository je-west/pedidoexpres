/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Valentina
 */
@Embeddable
public class LogestadoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idestado")
    private int idestado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpedido")
    private int idpedido;

    public LogestadoPK() {
    }

    public LogestadoPK(int idestado, int idpedido) {
        this.idestado = idestado;
        this.idpedido = idpedido;
    }

    public int getIdestado() {
        return idestado;
    }

    public void setIdestado(int idestado) {
        this.idestado = idestado;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idestado;
        hash += (int) idpedido;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogestadoPK)) {
            return false;
        }
        LogestadoPK other = (LogestadoPK) object;
        if (this.idestado != other.idestado) {
            return false;
        }
        if (this.idpedido != other.idpedido) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.LogestadoPK[ idestado=" + idestado + ", idpedido=" + idpedido + " ]";
    }
    
}
