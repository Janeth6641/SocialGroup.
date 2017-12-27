package master.presentacion.beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.RolUsuario;
import master.logica.entidades.Usuario;
import master.logica.funciones.FRolUsuario;
import recursos.Util;

@ManagedBean
@ViewScoped
public class CtSelectRol {

    private List<RolUsuario> lstRoles;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private RolUsuario rolUsuario;

    public CtSelectRol() {
        rolUsuario = new RolUsuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {
        cargarRoles();
    }

    public void cargarRoles() {
        try {
            Usuario us = (Usuario) httpServletRequest.getSession().getAttribute("UsuarioLogueado");
            lstRoles = FRolUsuario.obtenerRolesDadoUsuario(us.getIdPersona());
            Util.addSuccessMessage("total de roles: " + lstRoles.size());
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }

    public List<RolUsuario> getLstRoles() {
        return lstRoles;
    }

    public void setLstRoles(List<RolUsuario> lstRoles) {
        this.lstRoles = lstRoles;
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

    public RolUsuario getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

}
