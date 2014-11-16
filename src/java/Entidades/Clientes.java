/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mao
 */
@Entity
@Table(name = "clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c"),
    @NamedQuery(name = "Clientes.findByCuentaid", query = "SELECT c FROM Clientes c WHERE c.cuentaid = :cuentaid"),
    @NamedQuery(name = "Clientes.findByDocumentoid", query = "SELECT c FROM Clientes c WHERE c.documentoid = :documentoid"),
    @NamedQuery(name = "Clientes.findByNombre", query = "SELECT c FROM Clientes c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Clientes.findByApellido", query = "SELECT c FROM Clientes c WHERE c.apellido = :apellido")})
public class Clientes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "cuentaid")
    private int cuentaid;
    @Id
    @Basic(optional = false)
    @Column(name = "documentoid")
    private Integer documentoid;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;
    @OneToMany(mappedBy = "documentoId")
    private Collection<Transacciones> transaccionesCollection;

    public Clientes() {
    }

    public Clientes(Integer documentoid) {
        this.documentoid = documentoid;
    }

    public Clientes(Integer documentoid, int cuentaid, String nombre, String apellido) {
        this.documentoid = documentoid;
        this.cuentaid = cuentaid;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getCuentaid() {
        return cuentaid;
    }

    public void setCuentaid(int cuentaid) {
        this.cuentaid = cuentaid;
    }

    public Integer getDocumentoid() {
        return documentoid;
    }

    public void setDocumentoid(Integer documentoid) {
        this.documentoid = documentoid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @XmlTransient
    public Collection<Transacciones> getTransaccionesCollection() {
        return transaccionesCollection;
    }

    public void setTransaccionesCollection(Collection<Transacciones> transaccionesCollection) {
        this.transaccionesCollection = transaccionesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (documentoid != null ? documentoid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.documentoid == null && other.documentoid != null) || (this.documentoid != null && !this.documentoid.equals(other.documentoid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Clientes[ documentoid=" + documentoid + " ]";
    }
    
}
