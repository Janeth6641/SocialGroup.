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
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;
import sg.logica.entidades.FormaPago;
import sg.logica.entidades.Pif;
import sg.logica.funciones.FFormaPago;
import sg.logica.funciones.FPif;

@ManagedBean
@ViewScoped
public class CtComprarPif implements Serializable {

    private List<FormaPago> lstFormasPago;
    private List<Pif> lstPifs;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;
    private Pif pifSel;
    private int bnd;
    private int idFormaPago;
    private String codigoReferido;

    public CtComprarPif() {
        pifSel = new Pif();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        obtenerPifs();
        obtenerFormasPago();
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

    public void obtenerPifs() {
        try {
            setLstPifs(FPif.obtenerPifActivas());
            System.out.println("imagen " + getLstPifs().get(0).getFoto());
        } catch (Exception e) {
            System.out.println("public void obtenerPifs() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void comprarPifIniciador() {
        try {
            String msg = FPif.comprarPifIniciador(getSessionUsuario().getIdPersona(), getPifSel().getIdPif(), getIdFormaPago());
            Util.addSuccessMessage(msg);
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgComprarIniciador').hide()");
        } catch (Exception e) {
            System.out.println("public void comprarPifIniciador() dice: " + e.getMessage());
            Util.addErrorMessage(e, e.getMessage());
        }
    }

    public void obtenerFormasPago() {
        try {
            setLstFormasPago(FFormaPago.obtenerFormasPagoActivas());
        } catch (Exception e) {
            System.out.println("public void obtenerFormasPago() dice: " + e.getMessage());
            Util.addErrorMessage(e, e.getMessage());
        }
    }

    /**
     * @return the lstPifs
     */
    public List<Pif> getLstPifs() {
        return lstPifs;
    }

    /**
     * @param lstPifs the lstPifs to set
     */
    public void setLstPifs(List<Pif> lstPifs) {
        this.lstPifs = lstPifs;
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
     * @return the pifSel
     */
    public Pif getPifSel() {
        return pifSel;
    }

    /**
     * @param pifSel the pifSel to set
     */
    public void setPifSel(Pif pifSel) {
        this.pifSel = pifSel;
    }

    /**
     * @return the bnd
     */
    public int getBnd() {
        return bnd;
    }

    /**
     * @param bnd the bnd to set
     */
    public void setBnd(int bnd) {
        this.bnd = bnd;
    }

    /**
     * @return the lstFormasPago
     */
    public List<FormaPago> getLstFormasPago() {
        return lstFormasPago;
    }

    /**
     * @param lstFormasPago the lstFormasPago to set
     */
    public void setLstFormasPago(List<FormaPago> lstFormasPago) {
        this.lstFormasPago = lstFormasPago;
    }

    /**
     * @return the idFormaPago
     */
    public int getIdFormaPago() {
        return idFormaPago;
    }

    /**
     * @param idFormaPago the idFormaPago to set
     */
    public void setIdFormaPago(int idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    /**
     * @return the codigoReferido
     */
    public String getCodigoReferido() {
        return codigoReferido;
    }

    /**
     * @param codigoReferido the codigoReferido to set
     */
    public void setCodigoReferido(String codigoReferido) {
        this.codigoReferido = codigoReferido;
    }

}
