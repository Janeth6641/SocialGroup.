/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.logica.entidades;

import java.sql.Timestamp;
import master.logica.entidades.Usuario;

/**
 *
 * @author Geovanny
 */
public class Pif {

    private int idPif;
    private String pif;
    private String descripcion;
    private double costo;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaBaja;
    private Usuario sessionUsuario;
    private String foto;

    public Pif() {
        sessionUsuario = new Usuario();
    }

    /**
     * @return the idPif
     */
    public int getIdPif() {
        return idPif;
    }

    /**
     * @param idPif the idPif to set
     */
    public void setIdPif(int idPif) {
        this.idPif = idPif;
    }

    /**
     * @return the pif
     */
    public String getPif() {
        return pif;
    }

    /**
     * @param pif the pif to set
     */
    public void setPif(String pif) {
        this.pif = pif;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the costo
     */
    public double getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }

    /**
     * @return the estadoLogico
     */
    public String getEstadoLogico() {
        return estadoLogico;
    }

    /**
     * @param estadoLogico the estadoLogico to set
     */
    public void setEstadoLogico(String estadoLogico) {
        this.estadoLogico = estadoLogico;
    }

    /**
     * @return the fechaRegistro
     */
    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the fechaBaja
     */
    public Timestamp getFechaBaja() {
        return fechaBaja;
    }

    /**
     * @param fechaBaja the fechaBaja to set
     */
    public void setFechaBaja(Timestamp fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /**
     * @return the sessionUsuario
     */
    public Usuario getSessionUsuario() {
        return sessionUsuario;
    }

    /**
     * @param sessionUsuario the sessionUsuario to set
     */
    public void setSessionUsuario(Usuario sessionUsuario) {
        this.sessionUsuario = sessionUsuario;
    }

    /**
     * @return the foto
     */
    public String getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

}
