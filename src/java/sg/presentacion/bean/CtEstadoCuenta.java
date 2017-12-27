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
import sg.logica.entidades.EstadoCuenta;
import sg.logica.funciones.FEstadoCuenta;

@ManagedBean
@ViewScoped
public class CtEstadoCuenta implements Serializable {

    private EstadoCuenta objEstadoCuenta;
    private EstadoCuenta estadoCuentaSel;
    private List<EstadoCuenta> lstEstadosCuenta;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;

    public CtEstadoCuenta() {
        objEstadoCuenta = new EstadoCuenta();
        estadoCuentaSel = new EstadoCuenta();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        obtenerEstados();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
    }

    public void obtenerSession() {
        try {
            int idUsuario = (int) httpServletRequest.getSession().getAttribute("idUsuario");
            sessionUsuario = FUsuario.obtenerUsuarioDadoCodigo(idUsuario);
            System.out.println("Id usuario: " + idUsuario
                    + "Usuario session: " + sessionUsuario.getNick());
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
    }

    public void obtenerEstados() {
        try {
            lstEstadosCuenta = FEstadoCuenta.obtenerEstadosCuentaActivas();
        } catch (Exception e) {
            System.out.println("public void obtenerEstados() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertarEstadoCuenta() {
        try {
            objEstadoCuenta.setSessionUsuario(sessionUsuario);
            String msg = FEstadoCuenta.insertarEstadoCuenta(objEstadoCuenta);
            Util.addSuccessMessage(msg);
            objEstadoCuenta = new EstadoCuenta();
            obtenerEstados();
            resetearDataTable("frmPrincipal:tblEstados");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgInsertar').hide()");
        } catch (Exception e) {
            System.out.println("public void insertarEstadoCuenta() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void editarEstadoCuenta() {
        try {
            estadoCuentaSel.setSessionUsuario(sessionUsuario);
            String msg = FEstadoCuenta.editarEstadoCuenta(estadoCuentaSel);
            Util.addSuccessMessage(msg);
            estadoCuentaSel = new EstadoCuenta();
            obtenerEstados();
            resetearDataTable("frmPrincipal:tblEstados");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditar').hide()");
        } catch (Exception e) {
            System.out.println("public void editarEstadoCuenta() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminarEstadoCuenta() {
        try {
            estadoCuentaSel.setSessionUsuario(sessionUsuario);
            String msg = FEstadoCuenta.eliminarEstadoCuenta(estadoCuentaSel);
            Util.addSuccessMessage(msg);
            estadoCuentaSel = new EstadoCuenta();
            obtenerEstados();
            resetearDataTable("frmPrincipal:tblEstados");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide()");
        } catch (Exception e) {
            System.out.println("public void eliminarEstadoCuenta() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void resetearDataTable(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }

    public EstadoCuenta getObjEstadoCuenta() {
        return objEstadoCuenta;
    }

    public void setObjEstadoCuenta(EstadoCuenta objEstadoCuenta) {
        this.objEstadoCuenta = objEstadoCuenta;
    }

    public EstadoCuenta getEstadoCuentaSel() {
        return estadoCuentaSel;
    }

    public void setEstadoCuentaSel(EstadoCuenta estadoCuentaSel) {
        this.estadoCuentaSel = estadoCuentaSel;
    }

    public List<EstadoCuenta> getLstEstadosCuenta() {
        return lstEstadosCuenta;
    }

    public void setLstEstadosCuenta(List<EstadoCuenta> lstEstadosCuenta) {
        this.lstEstadosCuenta = lstEstadosCuenta;
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

    public Usuario getSessionUsuario() {
        return sessionUsuario;
    }

    public void setSessionUsuario(Usuario sessionUsuario) {
        this.sessionUsuario = sessionUsuario;
    }

}
