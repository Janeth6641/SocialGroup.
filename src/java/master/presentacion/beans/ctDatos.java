
package master.presentacion.beans;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
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
public class ctDatos implements Serializable{
    
    //private String codigo;
    private String strMensaje;
    private String strOpcion;
    private String strMsjEnviarCorreo;
    private String strEliminarFoto;
    private String strClaveAnterior;
    private String strClaveNueva;
    private String strDestino;
    private String strRutaFoto;
    private String strFotoPorDefecto;
    private String strFotoPorDefectoCoop;
    private Usuario nuevoUsuario, selectUsuario;
    private RolUsuario selecRolUsuario;
    ArrayList<Usuario> lstUsuario;
    //static String eliminarFoto;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private UploadedFile file;
    private String nombreUbicacion;

    


   //<editor-fold defaultstate="collapsed" desc="Constructor del Controlador cDatos">
    public ctDatos() {
        this.nuevoUsuario = new Usuario();
        this.selectUsuario = new Usuario();
        this.lstUsuario = new ArrayList<>();
        this.selecRolUsuario = new RolUsuario();
        strRutaFoto = String.valueOf(ResourceBundle.getBundle("/recursos/VariablesGlobales").getString("rutaFoto"));
        strMsjEnviarCorreo = String.valueOf(ResourceBundle.getBundle("recursos/Mensajes").getString("msjEnviarCorreo"));
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Post Constructor del Controlador cDatos">
    @PostConstruct
    public void init() {
        cargarUsuarioSeccion();
        int idRol = (int) httpServletRequest.getSession().getAttribute("idRol");
        
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Modificar Datos de la Usuario Perfil">
    
    public void actualizarDatosUsuarioPerfil() throws Exception {
        try {
            strMensaje = FUsuario.actualizarDatosUsuarioPerfil(selectUsuario);
            Util.addSuccessMessage(strMensaje);
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    
    //</editor-fold> 

    //<editor-fold defaultstate="collapsed" desc="Cargar datos del Usuario a Actualizar el Perfil">
    
        
    public String cargarUsuarioSeccion() {
        int intIdUsuario = (int) httpServletRequest.getSession().getAttribute("idUsuario");
        int intIdRol = (int) httpServletRequest.getSession().getAttribute("idRol");
        try {
            //selecRolUsuario = FRolUsuario.obtenerRolUsuario(intIdRol, intIdUsuario);
           selectUsuario = FUsuario.obtenerUsuarioDadoId(intIdUsuario);
            //setStrEliminarFoto(selectUsuario.getFoto());
            //setStrEliminarFoto(selectUsuario.getUsuario().getFoto());
            setStrEliminarFoto(selectUsuario.getFoto());
            
        } catch (Exception e) {
            Util.addErrorMessage(e, getStrMensaje());
        }
        
        return getStrEliminarFoto();
    }
    
    //</editor-fold> 

    //<editor-fold defaultstate="collapsed" desc="Cambiar Foto del Perfil Usuario ">

    //Este método permite Cambiar Foto del Perfil Usuario
     
    public void cambiarFotoUsuarioPerfil(String strNombFoto) {
        int intIdUsuario = (int) httpServletRequest.getSession().getAttribute("idUsuario");
        setStrEliminarFoto(cargarUsuarioSeccion());
        try {
            strMensaje = FUsuario.actualizarFotoUsuario(intIdUsuario, strNombFoto);
            selecRolUsuario.getUsuario().setFoto(strNombFoto);
            RequestContext.getCurrentInstance().update("frmEditarUsuario:panelFoto");
            File file = new File(strRutaFoto + getStrEliminarFoto());
            if (!strEliminarFoto.equals(strFotoPorDefecto)) {
                file.delete();
                Util.addSuccessMessage(strMensaje);
                cargarUsuarioSeccion();
                httpServletRequest.getSession().setAttribute("fotoUsuario", strNombFoto);
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage()); //(original)
        }
    }
    //</editor-fold>

    
    //<editor-fold defaultstate="collapsed" desc="GET AND SETT"> 
  
    public String getStrMensaje() {
        return strMensaje;
    }

    public void setStrMensaje(String strMensaje) {
        this.strMensaje = strMensaje;
    }

    public String getStrOpcion() {
        return strOpcion;
    }

    public void setStrOpcion(String strOpcion) {
        this.strOpcion = strOpcion;
    }

    public String getStrMsjEnviarCorreo() {
        return strMsjEnviarCorreo;
    }

    public void setStrMsjEnviarCorreo(String strMsjEnviarCorreo) {
        this.strMsjEnviarCorreo = strMsjEnviarCorreo;
    }

    public String getStrEliminarFoto() {
        return strEliminarFoto;
    }

    public void setStrEliminarFoto(String strEliminarFoto) {
        this.strEliminarFoto = strEliminarFoto;
    }

    public String getStrClaveAnterior() {
        return strClaveAnterior;
    }

    public void setStrClaveAnterior(String strClaveAnterior) {
        this.strClaveAnterior = strClaveAnterior;
    }

    public String getStrClaveNueva() {
        return strClaveNueva;
    }

    public void setStrClaveNueva(String strClaveNueva) {
        this.strClaveNueva = strClaveNueva;
    }

    public String getStrDestino() {
        return strDestino;
    }

    public void setStrDestino(String strDestino) {
        this.strDestino = strDestino;
    }

    public String getStrRutaFoto() {
        return strRutaFoto;
    }

    public void setStrRutaFoto(String strRutaFoto) {
        this.strRutaFoto = strRutaFoto;
    }

    public String getStrFotoPorDefecto() {
        return strFotoPorDefecto;
    }

    public void setStrFotoPorDefecto(String strFotoPorDefecto) {
        this.strFotoPorDefecto = strFotoPorDefecto;
    }

    public String getStrFotoPorDefectoCoop() {
        return strFotoPorDefectoCoop;
    }

    public void setStrFotoPorDefectoCoop(String strFotoPorDefectoCoop) {
        this.strFotoPorDefectoCoop = strFotoPorDefectoCoop;
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

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public FacesContext getFaceContext() {
        return faceContext;
    }

    public void setFaceContext(FacesContext faceContext) {
        this.faceContext = faceContext;
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

 
