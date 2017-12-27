package sg.presentacion.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Usuario;
import master.logica.funciones.FUsuario;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtActvarCuentas implements Serializable {

    private List<Usuario> lstClientes;
    private Usuario objCliente;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;

    public CtActvarCuentas() {
        objCliente = new Usuario();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        obtenerSolicitudes();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
    }

    public void obtenerSession() {
        try {
            int intIdUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setSessionUsuario(FUsuario.obtenerUsuarioDadoCodigo(intIdUsuario));
            System.out.println("Usuario Logueado: " + getSessionUsuario().getApellidos() + "id: " + getSessionUsuario().getIdUsuario());
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void activarCuenta() {
        try {
            String msg = FUsuario.validarCuenta(objCliente.getIdUsuario(), sessionUsuario.getIdPersona());
            Util.addSuccessMessage(msg);
            obtenerSolicitudes();
            objCliente = new Usuario();
            resetearDataTable("frmPrincipal:tblSolicitudes");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgActivarCuenta').hide()");
        } catch (Exception e) {
            System.out.println("public void activarCuenta() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void obtenerSolicitudes() {
        try {
            setLstClientes(FUsuario.obtenerSolictudesActivacion());
            Util.addSuccessMessage("Tiene " + getLstClientes().size() + " solicitud(es) pendientes.");
        } catch (Exception e) {
            System.out.println("public void obtenerSolicitudes() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerSolicitudes() dice: " + e.getMessage());
        }
    }

    public void resetearDataTable(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }

    /**
     * @return the httpServletRequest
     */
    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    /**
     * @param httpServletRequest the httpServletRequest to set
     */
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the faceContext
     */
    public FacesContext getFaceContext() {
        return faceContext;
    }

    /**
     * @param faceContext the faceContext to set
     */
    public void setFaceContext(FacesContext faceContext) {
        this.faceContext = faceContext;
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
     * @return the lstClientes
     */
    public List<Usuario> getLstClientes() {
        return lstClientes;
    }

    /**
     * @param lstClientes the lstClientes to set
     */
    public void setLstClientes(List<Usuario> lstClientes) {
        this.lstClientes = lstClientes;
    }

    /**
     * @return the objCliente
     */
    public Usuario getObjCliente() {
        return objCliente;
    }

    /**
     * @param objCliente the objCliente to set
     */
    public void setObjCliente(Usuario objCliente) {
        this.objCliente = objCliente;
    }

}
