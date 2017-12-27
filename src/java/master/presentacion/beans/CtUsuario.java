package master.presentacion.beans;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;
import master.logica.entidades.Usuario;
import master.logica.entidades.Persona;
import master.logica.entidades.RolUsuario;
import master.logica.funciones.FRolUsuario;
import master.logica.funciones.FUsuario;
import recursos.Util;

@ManagedBean
@RequestScoped
public class CtUsuario implements Serializable {

    private String codigo;
    private int codigo1 = 1;
    private String strMensaje;
    private Usuario nuevoUsuario, selectUsuario;
    private RolUsuario selecRolUsuario;
    ArrayList<Usuario> lstUsuario;
    private Usuario sessionUsuario;
    private HttpServletRequest httpServletRequest;
    private FacesContext facesContext;
    private UploadedFile file;
    private String nombreUbicacion;


        //<editor-fold defaultstate="collapsed" desc="Constructor del Controlador cDatos">
    public CtUsuario() {
        nuevoUsuario = new Usuario();
        selectUsuario = new Usuario();
        lstUsuario = new ArrayList<>();
        sessionUsuario = new Usuario();
        facesContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        obtenerUsuarios();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Post Constructor del Controlador">
    @PostConstruct
    public void init() {
        obtenerSession();
        int idRol = (int) httpServletRequest.getSession().getAttribute("idRol");

    }


    //<editor-fold defaultstate="collapsed" desc="Obtener session">
    public void obtenerSession() {
        try {
            int idUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setSessionUsuario(FUsuario.obtenerUsuarioDadoCodigo(idUsuario));
            System.out.println("Id usuario: " + idUsuario
                    + "Usuario session: " + getSessionUsuario().getNick());
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Obtener usuario por Lider">
    public ArrayList<Usuario> obtenerUsuarios() {
       // ArrayList<Usuario> lstUsuarios = new ArrayList<>();
        try {
            codigo1= getSessionUsuario().getIdPersona();
            System.out.println("Id Usuario: " + getSessionUsuario().getIdPersona());
            setLstUsuario(FUsuario.obtenerUsuariosPorLider(codigo1));            
        } catch (Exception e) {
            System.out.println("public void obtenerPif() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
        return lstUsuario;
    }

    //</editor-fold>
    
         
    
    
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCodigo1() {
        return codigo1;
    }

    public void setCodigo1(int codigo1) {
        this.codigo1 = codigo1;
    }

    public String getStrMensaje() {
        return strMensaje;
    }

    public void setStrMensaje(String strMensaje) {
        this.strMensaje = strMensaje;
    }

    public Usuario getNuevoUsuario() {
        return nuevoUsuario;
    }

    public void setNuevoUsuario(Usuario nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }

    public Usuario getSelectUsuario() {
        return selectUsuario;
    }

    public void setSelectUsuario(Usuario selectUsuario) {
        this.selectUsuario = selectUsuario;
    }

    public RolUsuario getSelecRolUsuario() {
        return selecRolUsuario;
    }

    public void setSelecRolUsuario(RolUsuario selecRolUsuario) {
        this.selecRolUsuario = selecRolUsuario;
    }

    public ArrayList<Usuario> getLstUsuario() {
        return lstUsuario;
    }

    public void setLstUsuario(ArrayList<Usuario> lstUsuario) {
        this.lstUsuario = lstUsuario;
    }

    public Usuario getSessionUsuario() {
        return sessionUsuario;
    }

    public void setSessionUsuario(Usuario sessionUsuario) {
        this.sessionUsuario = sessionUsuario;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public FacesContext getFacesContext() {
        return facesContext;
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getNombreUbicacion() {
        return nombreUbicacion;
    }

    public void setNombreUbicacion(String nombreUbicacion) {
        this.nombreUbicacion = nombreUbicacion;
    }

   //</editor-fold>
   
}
