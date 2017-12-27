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
import sg.logica.entidades.Compra;
import sg.logica.funciones.FCompra;

@ManagedBean
@ViewScoped
public class CtActivarCompras implements Serializable {

    private Compra compraSel;
    private List<Compra> lstCompras;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;

    public CtActivarCompras() {
        compraSel = new Compra();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        obtenerCompras();
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

    public void obtenerCompras() {
        try {
            setLstCompras(FCompra.obtenerComprasPorActivar());
            System.out.println("Total compras: " + getLstCompras().size());
        } catch (Exception e) {
            System.out.println("public void obtenerCompras() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerCompras() dice: " + e.getMessage());
        }
    }

    public void activarCompra() {
        try {
            String msg = FCompra.activarCompra(compraSel, sessionUsuario.getIdUsuario());
            obtenerCompras();
            Util.addSuccessMessage(msg);
            compraSel = new Compra();
            resetearDataTable("frmPrincipal:tblCompras");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgActivarCompra').hide()");
        } catch (Exception e) {
            System.out.println("public void activarCompra() dice: " + e.getMessage());
            Util.addErrorMessage("public void activarCompra() dice: " + e.getMessage());
        }
    }

    public void resetearDataTable(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }

    /**
     * @return the compraSel
     */
    public Compra getCompraSel() {
        return compraSel;
    }

    /**
     * @param compraSel the compraSel to set
     */
    public void setCompraSel(Compra compraSel) {
        this.compraSel = compraSel;
    }

    /**
     * @return the lstCompras
     */
    public List<Compra> getLstCompras() {
        return lstCompras;
    }

    /**
     * @param lstCompras the lstCompras to set
     */
    public void setLstCompras(List<Compra> lstCompras) {
        this.lstCompras = lstCompras;
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
