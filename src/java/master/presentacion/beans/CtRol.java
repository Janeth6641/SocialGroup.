package master.presentacion.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Rol;
import master.logica.entidades.Usuario;
import master.logica.funciones.FRol;
import master.logica.funciones.FUsuario;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtRol implements Serializable {

    private Rol objRol;
    private Rol rolSel;
    private List<Rol> lstRoles;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;

    public CtRol() {
        objRol = new Rol();
        rolSel = new Rol();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        obtenerRoles();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
    }

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

    public void obtenerRoles() {
        try {
            setLstRoles(FRol.obtenerRoles());
        } catch (Exception e) {
            System.out.println("public void obtenerRoles() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertar() {
        try {
            getObjRol().setSessionUsuario(getSessionUsuario());
            String msg = FRol.insertarRol(getObjRol());
            setObjRol(new Rol());
            Util.addSuccessMessage(msg);
            obtenerRoles();
            resetearDataTable("frmPrincipal:tblRoles");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgInsertar').hide()");
        } catch (Exception e) {
            System.out.println("public void insertar() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void editar() {
        try {
            getRolSel().setSessionUsuario(getSessionUsuario());
            String msg = FRol.editarRol(getRolSel());
            setRolSel(new Rol());
            Util.addSuccessMessage(msg);
            obtenerRoles();
            resetearDataTable("frmPrincipal:tblRoles");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditar').hide()");
        } catch (Exception e) {
            System.out.println("public void editar() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminar() {
        try {
            getRolSel().setSessionUsuario(getSessionUsuario());
            String msg = FRol.eliminarRol(getRolSel());
            setRolSel(new Rol());
            Util.addSuccessMessage(msg);
            obtenerRoles();
            resetearDataTable("frmPrincipal:tblRoles");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide()");
        } catch (Exception e) {
            System.out.println("public void eliminar() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void resetearDataTable(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }

    /**
     * @return the objRol
     */
    public Rol getObjRol() {
        return objRol;
    }

    /**
     * @param objRol the objRol to set
     */
    public void setObjRol(Rol objRol) {
        this.objRol = objRol;
    }

    /**
     * @return the rolSel
     */
    public Rol getRolSel() {
        return rolSel;
    }

    /**
     * @param rolSel the rolSel to set
     */
    public void setRolSel(Rol rolSel) {
        this.rolSel = rolSel;
    }

    /**
     * @return the lstRoles
     */
    public List<Rol> getLstRoles() {
        return lstRoles;
    }

    /**
     * @param lstRoles the lstRoles to set
     */
    public void setLstRoles(List<Rol> lstRoles) {
        this.lstRoles = lstRoles;
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
    
    
    
    
}
