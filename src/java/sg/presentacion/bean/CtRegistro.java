package sg.presentacion.bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.TipoPersona;
import master.logica.entidades.Usuario;
import master.logica.funciones.FTipoPersona;
import master.logica.funciones.FUsuario;
import recursos.Util;
import sg.logica.entidades.Cuenta;
import sg.logica.entidades.Pif;
import sg.logica.funciones.FPif;

@ManagedBean
@ViewScoped
public class CtRegistro implements Serializable {

    private List<TipoPersona> lstTiposPersonas;
    private Usuario objPersona;
    private int aceptar;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;

    public CtRegistro() {
        objPersona = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        obtenerTiposPersonas();
    }

    public void obtenerTiposPersonas() {
        try {
            setLstTiposPersonas(FTipoPersona.obtenerTiposPersonaDadoEstado("A"));
        } catch (Exception e) {
            System.out.println("public void obtenerTiposPersonas() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void registrar() {
        try {
            String msg = FUsuario.registrarUsuarioVisitante(objPersona);
            Util.addSuccessMessage(msg);

            System.out.println("registrar dice: " + msg);

            Util.addSuccessMessage("Ud se ha registrado exitosamente.");
            faceContext.getExternalContext().redirect("login.jsf");
            objPersona = new Usuario();

        } catch (Exception e) {
            System.out.println("public void registrar() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    /**
     * @return the lstTiposPersonas
     */
    public List<TipoPersona> getLstTiposPersonas() {
        return lstTiposPersonas;
    }

    /**
     * @param lstTiposPersonas the lstTiposPersonas to set
     */
    public void setLstTiposPersonas(List<TipoPersona> lstTiposPersonas) {
        this.lstTiposPersonas = lstTiposPersonas;
    }

    /**
     * @return the objPersona
     */
    public Usuario getObjPersona() {
        return objPersona;
    }

    /**
     * @param objPersona the objPersona to set
     */
    public void setObjPersona(Usuario objPersona) {
        this.objPersona = objPersona;
    }

    /**
     * @return the aceptar
     */
    public int getAceptar() {
        return aceptar;
    }

    /**
     * @param aceptar the aceptar to set
     */
    public void setAceptar(int aceptar) {
        this.aceptar = aceptar;
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

}
