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
 * @author Janthsita
 */
public class PlanComisiones {
    
    private int idPlanComsion;
    private Pif pif;
    private double comisionDirecta;
    private double comisionIndirecta;
    private double comisionRecategoria;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaBaja;
    private String observaciones;
    private Usuario sessionUsuario;

    public PlanComisiones() {
        pif = new Pif();
        sessionUsuario = new Usuario();
    }

    public int getIdPlanComsion() {
        return idPlanComsion;
    }

    public void setIdPlanComsion(int idPlanComsion) {
        this.idPlanComsion = idPlanComsion;
    }

    public Pif getPif() {
        return pif;
    }

    public void setPif(Pif pif) {
        this.pif = pif;
    }

    public double getComisionDirecta() {
        return comisionDirecta;
    }

    public void setComisionDirecta(double comisionDirecta) {
        this.comisionDirecta = comisionDirecta;
    }

    public double getComisionIndirecta() {
        return comisionIndirecta;
    }

    public void setComisionIndirecta(double comisionIndirecta) {
        this.comisionIndirecta = comisionIndirecta;
    }

    public double getComisionRecategoria() {
        return comisionRecategoria;
    }

    public void setComisionRecategoria(double comisionRecategoria) {
        this.comisionRecategoria = comisionRecategoria;
    }

    public String getEstadoLogico() {
        return estadoLogico;
    }

    public void setEstadoLogico(String estadoLogico) {
        this.estadoLogico = estadoLogico;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Timestamp getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Timestamp fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Usuario getSessionUsuario() {
        return sessionUsuario;
    }

    public void setSessionUsuario(Usuario sessionUsuario) {
        this.sessionUsuario = sessionUsuario;
    }
            
    
}
