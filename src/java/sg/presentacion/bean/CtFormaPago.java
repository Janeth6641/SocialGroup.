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
import sg.logica.entidades.FormaPago;
import sg.logica.funciones.FFormaPago;
import master.logica.entidades.Usuario;
import master.logica.funciones.FUsuario;

@ManagedBean
@ViewScoped
public class CtFormaPago implements Serializable {

    private FormaPago objFormaPago;
    private FormaPago formaPagoSel;
    private List<FormaPago> lstFormaPago;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;

    public CtFormaPago() {
        objFormaPago = new FormaPago();
        formaPagoSel = new FormaPago();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        obtenerFormaPago();
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

    public void obtenerFormaPago() {
        try {
            lstFormaPago = FFormaPago.obtenerFormasPagoActivas();
        } catch (Exception e) {
            System.out.println("public void obtenerFormaPago() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void insertarFormaPago() {
        try {
            objFormaPago.setSessionUsuario(sessionUsuario);
            //objFormaPago.getSessionUsuario().setIdUsuario((int) getHttpServletRequest().getSession().getAttribute("idUsuario"));
            String msg = FFormaPago.insertarFormaPago(objFormaPago);
            Util.addSuccessMessage(msg);
            objFormaPago = new FormaPago();
            obtenerFormaPago();
            resetearDataTable("frmPrincipal:tblForma");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgInsertar').hide()");

        } catch (Exception e) {
            System.out.println("public void insertarFormaPago() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void editarFormaPago() {
        try {

            String msg = FFormaPago.editarFormaPago(formaPagoSel);
            Util.addSuccessMessage(msg);
            formaPagoSel = new FormaPago();
            resetearDataTable("frmPrincipal:tblForma");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditarFP').hide()");
            obtenerFormaPago();
        } catch (Exception e) {
            System.out.println("public void editarFormaPago() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void eliminarFormaPago() {
        try {
            //formaPagoSel.setSessionUsuario(sessionUsuario);
            String msg = FFormaPago.eliminarFormaPago(formaPagoSel);
            Util.addSuccessMessage(msg);
            formaPagoSel = new FormaPago();
            resetearDataTable("frmPrincipal:tblForma");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminarFP').hide()");
            obtenerFormaPago();
        } catch (Exception e) {
            System.out.println("public void eliminarFormaPago() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void resetearDataTable(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }

    public FormaPago getObjFormaPago() {
        return objFormaPago;
    }

    public void setObjFormaPago(FormaPago objFormaPago) {
        this.objFormaPago = objFormaPago;
    }

    public FormaPago getFormaPagoSel() {
        return formaPagoSel;
    }

    public void setFormaPagoSel(FormaPago formaPagoSel) {
        this.formaPagoSel = formaPagoSel;
    }

    public List<FormaPago> getLstFormaPago() {
        return lstFormaPago;
    }

    public void setLstFormaPago(List<FormaPago> lstFormaPago) {
        this.lstFormaPago = lstFormaPago;
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
