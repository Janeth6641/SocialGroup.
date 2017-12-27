package sg.presentacion.bean;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.Application;
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
import sg.logica.entidades.Pif;
import sg.logica.funciones.FPif;


@ManagedBean
@ViewScoped
public class CtPif implements Serializable {

    private Pif objPif;
    private Pif pifSel;
    private List<Pif> lstPif;
    private HttpServletRequest httpServletRequest;
    private FacesContext facesContext;
    private Usuario sessionUsuario;

    public CtPif() {
        objPif = new Pif();
        pifSel = new Pif();
        sessionUsuario = new Usuario();
        facesContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        obtenerPif();

    }

    @PostConstruct
    public void init() {
        obtenerSession();
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
    
    //<editor-fold defaultstate="collapsed" desc="Obtener PIF">    
    public void obtenerPif() {
        try {
            lstPif = FPif.obtenerPifActivas();
        } catch (Exception e) {
            System.out.println("public void obtenerPif() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="insertar PIF">
    public void insertarPif() {
        try {
           
            System.out.println("Id Usuario: " + getSessionUsuario().getIdPersona());         
            String msg = FPif.insertarPif(getObjPif(), getSessionUsuario().getIdPersona());
            objPif = new Pif();
            obtenerPif();
            Util.addSuccessMessage(msg);
            resetearDataTable("frmPrincipal:tblPif");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgInsertar').hide()");
            
            
        } catch (Exception e) {
            System.out.println("public void insertarPif() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Editar PIF">
    public void editarPif() {
        try {
            pifSel.setSessionUsuario(sessionUsuario);
            String msg = FPif.editarPif(pifSel);
            Util.addSuccessMessage(msg);
            pifSel = new Pif();
            resetearDataTable("frmPrincipal:tblPif");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditar').hide()");
            obtenerPif();
        } catch (Exception e) {
            System.out.println("public void editarPif() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Eliminar PIF">

    public void eliminarPif() {
        try {
            pifSel.setSessionUsuario(sessionUsuario);
            String msg = FPif.eliminarPif(pifSel);
            Util.addSuccessMessage(msg);
            pifSel = new Pif();
            resetearDataTable("frmPrincipal:tblPif");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide()");
            obtenerPif();
        } catch (Exception e) {
            System.out.println("public void eliminarPif() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getter and setters">
    public void resetearDataTable(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }

    public Pif getObjPif() {
        return objPif;
    }

    public void setObjPif(Pif objPif) {
        this.objPif = objPif;
    }

    public Pif getPifSel() {
        return pifSel;
    }

    public void setPifSel(Pif pifSel) {
        this.pifSel = pifSel;
    }

    public List<Pif> getLstPif() {
        return lstPif;
    }

    public void setLstPif(List<Pif> lstPif) {
        this.lstPif = lstPif;
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

    public Usuario getSessionUsuario() {
        return sessionUsuario;
    }

    public void setSessionUsuario(Usuario sessionUsuario) {
        this.sessionUsuario = sessionUsuario;
    }

   //</editor-fold>
    
     }


    