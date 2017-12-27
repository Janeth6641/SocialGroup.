package master.logica.entidades;

import java.sql.Timestamp;

public class Usuario extends Persona {

    private int idUsuario;
    private String nick;
    private String mail;
    private String password;
    private String validado;
    private Timestamp fechaRegistro;
    private Timestamp fechaBaja;
    private String estadoLogico;
    private Timestamp fechaValidacion;
    private int sessionUsuario;

    public Usuario() {
        //sessionUsuario = new Usuario();
    }

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the nick
     */
    public String getNick() {
        return nick;
    }

    /**
     * @param nick the nick to set
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the validado
     */
    public String getValidado() {
        return validado;
    }

    /**
     * @param validado the validado to set
     */
    public void setValidado(String validado) {
        this.validado = validado;
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
     * @return the fechaValidacion
     */
    public Timestamp getFechaValidacion() {
        return fechaValidacion;
    }

    /**
     * @param fechaValidacion the fechaValidacion to set
     */
    public void setFechaValidacion(Timestamp fechaValidacion) {
        this.fechaValidacion = fechaValidacion;
    }

    /**
     * @return the sessionUsuario
     */
    public int getSessionUsuario() {
        return sessionUsuario;
    }

    /**
     * @param sessionUsuario the sessionUsuario to set
     */
    public void setSessionUsuario(int sessionUsuario) {
        this.sessionUsuario = sessionUsuario;
    }

   

}
