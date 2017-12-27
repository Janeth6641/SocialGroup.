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
import sg.logica.entidades.PlanComisiones;
import sg.logica.funciones.FPif;
import sg.logica.funciones.FPlanComisiones;

@ManagedBean
@ViewScoped
public class CtPlanComisiones {

    private PlanComisiones objPlanCom;
    private PlanComisiones planComSel;
    private Pif objPif;
    private List<PlanComisiones> lstPlanCom;
    private List<Pif> LstPifs;
    private HttpServletRequest httpServletRequest;
    private FacesContext facesContext;
    private Usuario sessionUsuario;
    private int idPif;

    public CtPlanComisiones() {
        objPlanCom = new PlanComisiones();
        planComSel = new PlanComisiones();
        objPif = new Pif();
        sessionUsuario = new Usuario();
        facesContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        obtenerPlanComisiones();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
        obtenerPifs();
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
    public void obtenerPlanComisiones() {
        try {
            lstPlanCom = FPlanComisiones.obtenerPlanComisiones();
        } catch (Exception e) {
            System.out.println("public void obtenerPlanComisiones() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Seleccionar  PIF Sin Plan de Comisión
    public void obtenerPifs() {
        try {
           //setLstPifs(FPif.obtenerPifActivas());
           setLstPifs(FPif.obtenerPifSinPlanComision());
        } catch (Exception e) {
            System.out.println("public void obtenerPifs() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="insertar Plan de Comisiones">
    public void insertarPlanComisiones() {
        try {
            objPlanCom.setSessionUsuario(sessionUsuario);
            System.out.println("Id Usuario: " + getSessionUsuario().getIdPersona());
            System.out.println("Id PIF: " + getIdPif());
            String msg = FPlanComisiones.insertarPlanComisiones(objPlanCom, getIdPif());
            objPlanCom = new PlanComisiones();
            obtenerPlanComisiones();
            Util.addSuccessMessage(msg);
            resetearDataTable("frmPrincipal:tblPlanCom");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgInsertar').hide()");

        } catch (Exception e) {
            System.out.println("public void insertarPlanCom() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Editar PIF">
    public void editarPlanComisiones() {
        try {
            planComSel.setSessionUsuario(sessionUsuario);
            String msg = FPlanComisiones.actualizarPlanComisiones(planComSel);
            Util.addSuccessMessage(msg);
            planComSel = new PlanComisiones();
            resetearDataTable("frmPrincipal:tblPlanCom");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEditar').hide()");
            obtenerPlanComisiones();
        } catch (Exception e) {
            System.out.println("public void editarPlanCom() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Eliminar Plan comisiones">
    public void eliminarPlanComisiones() {
        try {

            planComSel.setSessionUsuario(sessionUsuario);
            String msg = FPlanComisiones.eliminarPlanComisiones(planComSel);
            Util.addSuccessMessage(msg);
            planComSel = new PlanComisiones();
            resetearDataTable("frmPrincipal:tblPlanCom");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide()");
            obtenerPlanComisiones();
        } catch (Exception e) {
            System.out.println("public void eliminarPlanComisiones() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="GEt and Set">
    public void resetearDataTable(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }

    public int getIdPif() {
        return idPif;
    }

    public void setIdPif(int idPif) {
        this.idPif = idPif;
    }

    public PlanComisiones getObjPlanCom() {
        return objPlanCom;
    }

    public void setObjPlanCom(PlanComisiones objPlanCom) {
        this.objPlanCom = objPlanCom;
    }

    public PlanComisiones getPlanComSel() {
        return planComSel;
    }

    public void setPlanComSel(PlanComisiones planComSel) {
        this.planComSel = planComSel;
    }

    public Pif getObjPif() {
        return objPif;
    }

    public void setObjPif(Pif objPif) {
        this.objPif = objPif;
    }

    public List<PlanComisiones> getLstPlanCom() {
        return lstPlanCom;
    }

    public void setLstPlanCom(List<PlanComisiones> lstPlanCom) {
        this.lstPlanCom = lstPlanCom;
    }

    public List<Pif> getLstPifs() {
        return LstPifs;
    }

    public void setLstPifs(List<Pif> LstPifs) {
        this.LstPifs = LstPifs;
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
