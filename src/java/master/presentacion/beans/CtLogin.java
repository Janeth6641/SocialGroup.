/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master.presentacion.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import master.logica.entidades.RolUsuario;
import master.logica.entidades.Usuario;
import master.logica.funciones.FRolUsuario;
import master.logica.funciones.FUsuario;
import recursos.Util;

@ManagedBean
@RequestScoped
public class CtLogin implements Serializable {

    private String txtUsuario;
    private String txtPassword;
    private Usuario usuario;
    private RolUsuario rolUsuarioSel;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private FacesMessage facesMessage;
    private List<RolUsuario> lstRoles;
    private java.util.ResourceBundle Configuracion = java.util.ResourceBundle.getBundle("recursos.DatosAplicacion");

    public CtLogin() {
        rolUsuarioSel = new RolUsuario();
        usuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    public void login() {
        try {
            usuario = FUsuario.loginUsuario(txtUsuario, txtPassword);
            Util.addSuccessMessage("Bienvenido: " + usuario.getNombres() + " " + usuario.getApellidos());

            System.out.println("Id Usuario: " + usuario.getIdPersona());

            httpServletRequest.getSession().setAttribute("UsuarioLogueado", usuario);
            httpServletRequest.getSession().setAttribute("Datos", usuario.getNombres() + " " + usuario.getApellidos());
            httpServletRequest.getSession().setAttribute("idUsuario", usuario.getIdUsuario());
            httpServletRequest.getSession().setAttribute("fotoUsuario", usuario.getFoto());

            ///estado de validacion
            httpServletRequest.getSession().setAttribute("validado", usuario.getValidado());
            System.out.println("Estado vaidado: " + usuario.getValidado());

            lstRoles = FRolUsuario.obtenerRolesDadoUsuario(usuario.getIdPersona());
            System.out.println("Total de roles: " + lstRoles.size());
            if (lstRoles.size() > 1) {
                httpServletRequest.getSession().setAttribute("totalRoles", lstRoles.size()); //total de roles
                faceContext.getExternalContext().redirect("privado/rol.jsf");
            } else if (lstRoles.size() == 1) {
                RolUsuario ru = lstRoles.get(0);
                httpServletRequest.getSession().setAttribute("idRol", ru.getRol().getIdRol());
                httpServletRequest.getSession().setAttribute("rol", ru.getRol().getRol());

                /// privilegios
                httpServletRequest.getSession().setAttribute("privSeleccionar", ru.getPrivSeleccionar());
                httpServletRequest.getSession().setAttribute("privInsertar", ru.getPrivInsertar());
                httpServletRequest.getSession().setAttribute("privEditar", ru.getPrivEditar());
                httpServletRequest.getSession().setAttribute("privEliminar", ru.getPrivEliminar());

                /// testo de la funcion
                System.out.println("Id Rol: " + ru.getRol().getIdRol()
                        + "Rol: " + ru.getRol().getRol() + "\n"
                        + "\n priv insertar: " + ru.getPrivSeleccionar()
                        + "\n priv editar: " + ru.getPrivEditar()
                        + "\n priv seleccionar: " + ru.getPrivSeleccionar()
                        + "\n priv eliminar: " + ru.getPrivEliminar()
                );

                faceContext.getExternalContext().redirect("privado/home.jsf");
            } else {
                Util.addErrorMessage("El Usuario no tiene roles activos en el sistema.");
                faceContext.getExternalContext().redirect("index.jsf");
            }

        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage().replace("\n", "").replace("Hint:", ""));
        }
    }

    public void loginRol() {
        try {
            // int idUsuario = (int) httpServletRequest.getSession().getAttribute("idUsuario");
            // int intIdRol = rolUsuarioSel.getRol().getIdRol();

            //rolUsuarioSel = FRolUsuario.obtenerRolUsuario(intIdRol, idUsuario);
            httpServletRequest.getSession().setAttribute("idRol", rolUsuarioSel.getRol().getIdRol());
            httpServletRequest.getSession().setAttribute("rol", rolUsuarioSel.getRol().getRol());

            /// privilegios
            httpServletRequest.getSession().setAttribute("privSeleccionar", rolUsuarioSel.getPrivSeleccionar());
            httpServletRequest.getSession().setAttribute("privInsertar", rolUsuarioSel.getPrivInsertar());
            httpServletRequest.getSession().setAttribute("privEditar", rolUsuarioSel.getPrivEditar());
            httpServletRequest.getSession().setAttribute("privEliminar", rolUsuarioSel.getPrivEliminar());

            /// testo de la funcion
            System.out.println("Id Rol: " + rolUsuarioSel.getRol().getIdRol()
                    + "Rol: " + rolUsuarioSel.getRol().getRol() + "\n"
                    + "\n priv insertar: " + rolUsuarioSel.getPrivSeleccionar()
                    + "\n priv editar: " + rolUsuarioSel.getPrivEditar()
                    + "\n priv seleccionar: " + rolUsuarioSel.getPrivSeleccionar()
                    + "\n priv eliminar: " + rolUsuarioSel.getPrivEliminar());

            faceContext.getExternalContext().redirect("home.jsf");
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage().replace("\n", "").replace("Hint:", ""));
        }
    }

    public void cerrarSesion() throws Exception {
        try {
            httpServletRequest.getSession().removeAttribute("UsuarioLogueado");
            httpServletRequest.getSession().removeAttribute("Datos");
            httpServletRequest.getSession().removeAttribute("totalRoles");

            System.gc();  //limpiar todo
            FacesContext fc = FacesContext.getCurrentInstance();

            Util.addSuccessMessage("Sesión cerrada");
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.invalidate();

            fc.getExternalContext().redirect("/" + Configuracion.getString("Aplicacion"));
            fc.getExternalContext().invalidateSession();
        } catch (Exception ex) {
            Util.addErrorMessage(ex.getMessage().replace("\n", "").replace("Hint:", ""));
        }
    }

    /**
     * @return the txtUsuario
     */
    public String getTxtUsuario() {
        return txtUsuario;
    }

    /**
     * @param txtUsuario the txtUsuario to set
     */
    public void setTxtUsuario(String txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    /**
     * @return the txtPassword
     */
    public String getTxtPassword() {
        return txtPassword;
    }

    /**
     * @param txtPassword the txtPassword to set
     */
    public void setTxtPassword(String txtPassword) {
        this.txtPassword = txtPassword;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public FacesMessage getFacesMessage() {
        return facesMessage;
    }

    public void setFacesMessage(FacesMessage facesMessage) {
        this.facesMessage = facesMessage;
    }

    public List<RolUsuario> getLstRoles() {
        return lstRoles;
    }

    public void setLstRoles(List<RolUsuario> lstRoles) {
        this.lstRoles = lstRoles;
    }

    public ResourceBundle getConfiguracion() {
        return Configuracion;
    }

    public void setConfiguracion(ResourceBundle Configuracion) {
        this.Configuracion = Configuracion;
    }

    public RolUsuario getRolUsuarioSel() {
        return rolUsuarioSel;
    }

    public void setRolUsuarioSel(RolUsuario rolUsuarioSel) {
        this.rolUsuarioSel = rolUsuarioSel;
    }

}
