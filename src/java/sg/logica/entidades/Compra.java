/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.logica.entidades;

import java.sql.Timestamp;
import master.logica.entidades.Usuario;

public class Compra {

    private int idCompra;
    private String recategorizacion;
    private Cuenta cuenta;
    private FormaPago formaPago;
    private double valorCompra;
    private EstadoCuenta estadoCompra;
    private String comprobante;
    private Timestamp fechaRegistro;
    private Timestamp fechaVerificacion;
    private String observaciones;
    private Usuario sessionUsuario;
    private Timestamp fechaSolicitud;

    public Compra() {
        cuenta = new Cuenta();
        formaPago = new FormaPago();
        estadoCompra = new EstadoCuenta();
        sessionUsuario = new Usuario();
    }

    /**
     * @return the idCompra
     */
    public int getIdCompra() {
        return idCompra;
    }

    /**
     * @param idCompra the idCompra to set
     */
    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    /**
     * @return the recategorizacion
     */
    public String getRecategorizacion() {
        return recategorizacion;
    }

    /**
     * @param recategorizacion the recategorizacion to set
     */
    public void setRecategorizacion(String recategorizacion) {
        this.recategorizacion = recategorizacion;
    }

    /**
     * @return the cuenta
     */
    public Cuenta getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the formaPago
     */
    public FormaPago getFormaPago() {
        return formaPago;
    }

    /**
     * @param formaPago the formaPago to set
     */
    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    /**
     * @return the valorCompra
     */
    public double getValorCompra() {
        return valorCompra;
    }

    /**
     * @param valorCompra the valorCompra to set
     */
    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    /**
     * @return the estadoCompra
     */
    public EstadoCuenta getEstadoCompra() {
        return estadoCompra;
    }

    /**
     * @param estadoCompra the estadoCompra to set
     */
    public void setEstadoCompra(EstadoCuenta estadoCompra) {
        this.estadoCompra = estadoCompra;
    }

    /**
     * @return the comprobante
     */
    public String getComprobante() {
        return comprobante;
    }

    /**
     * @param comprobante the comprobante to set
     */
    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
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
     * @return the fechaVerificacion
     */
    public Timestamp getFechaVerificacion() {
        return fechaVerificacion;
    }

    /**
     * @param fechaVerificacion the fechaVerificacion to set
     */
    public void setFechaVerificacion(Timestamp fechaVerificacion) {
        this.fechaVerificacion = fechaVerificacion;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
     * @return the fechaSolicitud
     */
    public Timestamp getFechaSolicitud() {
        return fechaSolicitud;
    }

    /**
     * @param fechaSolicitud the fechaSolicitud to set
     */
    public void setFechaSolicitud(Timestamp fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

}
