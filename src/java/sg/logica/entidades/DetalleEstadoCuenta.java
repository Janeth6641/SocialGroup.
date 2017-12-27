package sg.logica.entidades;

import java.sql.Timestamp;
import master.logica.entidades.Usuario;

public class DetalleEstadoCuenta {

    private int idDetalle;
    private Cuenta cuenta;
    private EstadoCuenta estadoCuenta;
    private Timestamp fechaEstado;
    private String observaciones;
    private Usuario sessionUsuario;

    public DetalleEstadoCuenta() {
        cuenta = new Cuenta();
        estadoCuenta = new EstadoCuenta();
        sessionUsuario = new Usuario();
    }

    /**
     * @return the idDetalle
     */
    public int getIdDetalle() {
        return idDetalle;
    }

    /**
     * @param idDetalle the idDetalle to set
     */
    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
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
     * @return the estadoCuenta
     */
    public EstadoCuenta getEstadoCuenta() {
        return estadoCuenta;
    }

    /**
     * @param estadoCuenta the estadoCuenta to set
     */
    public void setEstadoCuenta(EstadoCuenta estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    /**
     * @return the fechaEstado
     */
    public Timestamp getFechaEstado() {
        return fechaEstado;
    }

    /**
     * @param fechaEstado the fechaEstado to set
     */
    public void setFechaEstado(Timestamp fechaEstado) {
        this.fechaEstado = fechaEstado;
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

}
