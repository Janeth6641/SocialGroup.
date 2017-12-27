package sg.logica.entidades;

import java.sql.Timestamp;
import master.logica.entidades.Usuario;

public class EstadoCuenta {

    private int idEstadoCuenta;
    private String estado;
    private String descripcion;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaBaja;
    private Usuario sessionUsuario;

    public EstadoCuenta() {
        sessionUsuario = new Usuario();
    }

    /**
     * @return the idEstadoCuenta
     */
    public int getIdEstadoCuenta() {
        return idEstadoCuenta;
    }

    /**
     * @param idEstadoCuenta the idEstadoCuenta to set
     */
    public void setIdEstadoCuenta(int idEstadoCuenta) {
        this.idEstadoCuenta = idEstadoCuenta;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
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

}
