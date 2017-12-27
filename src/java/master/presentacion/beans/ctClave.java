package master.presentacion.beans;

import recursos.Util;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Usuario;
import master.logica.entidades.Persona;
import master.logica.entidades.RolUsuario;
import master.logica.funciones.FRolUsuario;
import master.logica.funciones.FUsuario;
import recursos.Util;

@ManagedBean
@ViewScoped
public class ctClave {

    private Usuario objUsuario;
    private String strClaveAnterior, strClaveNueva, strClaveNuevaR;
    private String strMsjEnviarCorreo;
    private RolUsuario objRolUsuario;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private String strMensaje;
    private FacesContext facesContext;
    private Usuario sessionUsuario;

    //<editor-fold defaultstate="collapsed" desc="Constructor del Controlador cClave">
    public ctClave() {
        objUsuario = new Usuario();
        strMsjEnviarCorreo = String.valueOf(ResourceBundle.getBundle("/recursos/Mensajes").getString("msjEnviarCorreo"));
        sessionUsuario = new Usuario();
        facesContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    }
    //</editor-fold>   

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
    //<editor-fold defaultstate="collapsed" desc="Modificar Contraseña de la Persona">
    public void modificarClave() throws Exception {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        int intIdUsuario = (int) httpServletRequest.getSession().getAttribute("idUsuario");        
        Usuario objUsuario;
        ctClave objClave = new ctClave();
        try {
            strMensaje = FUsuario.cambiarContrasenia(intIdUsuario, getStrClaveAnterior(), getStrClaveNueva(), getStrClaveNuevaR());
            Util.addSuccessMessage(strMensaje);

        } catch (Exception e) {
            System.out.println("public void modificarContrasenia() dice: " + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }
    // </editor-fold>  

    //<editor-fold defaultstate="collapsed" desc="Enviar Mensaje al correo">

    public void cEnviarMensaje(String strEmail, String strPass) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        final String strUsername = "yelouec@gmail.com";
        final String strPassWord = "Khipucom2016";
        String To = strEmail;
        String Subject = "Satu";
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(strUsername, strPassWord);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(strUsername));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(To));
            message.setSubject(Subject);
            message.setContent(" <html style=\"font-family:calibri\">\n"
                    + "     <div style=\"-webkit-box-shadow: 20px 20px 30px -6px rgba(0,0,0,0.3);\n"
                    + "-moz-box-shadow: 20px 20px 30px -6px rgba(0,0,0,0.3);\n"
                    + "box-shadow: 20px 20px 30px -6px rgba(0,0,0,0.3); width:600px; \">\n"
                    + "        <div style=\"background-color:#2B2E2E; width:auto; marging-left:10px\"><img style=\"padding-left:250px; padding-top:20px; padding-bottom:20px;width:100px;\" src=\"http://64.15.146.126/WebAppYelou/resources/imagenes/logo256-only.png\">\n"
                    + "    </div>\n"
                    + "        <div style=\"background-color:#F5F4F3;\">\n"
                    + "        <h2 style=\"text-align:center;\">CAMBIO DE CLAVE</h2>\n"
                    + "            <p style=\"padding-left:40px; padding-right:40; text-align:justify;\">\n"
                    + "                USUARIO: " + strEmail + "\n"
                    + "                <br>\n"
                    + "                CLAVE: " + strPass + "\n"
                    + "                <br>\n"
                    + "                <br>\n"
                    + "                NOTA: Debe cambiar su clave al ingresar a su perfil.\n"
                    + "                <br>\n"
                    + "                <br>\n"
                    + "                 Ingreso al Sistema <a href=http://64.15.146.126/WepAppYelou>www.yelou.com</a></p>\n"
                    + "            <br>\n"
                    + "                \n"
                    + "            </p>\n"
                    + "        </div>\n"
                    + "        <div style=\"background-color:#2B2E2E; width:600px; color:#fff; padding-left:150px\">www.yelou.com - Derechos Reservados &#169;\n"
                    + "    </div>\n"
                    + "         <br>\n"
                    + "    </div>  \n"
                    + "    \n"
                    + "</html>", "text / html");
            Transport.send(message);
        } catch (MessagingException e) {
            Util.addErrorMessage("Problemas de conexión a Internet, no se ha enviado su correo electrónico, Intentelo más tarde.");
            throw new RuntimeException(e);
        }
    }

    //</editor-fold>     
    //<editor-fold defaultstate="collapsed" desc="get and set">
    
    public Usuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(Usuario objUsuario) {
        this.objUsuario = objUsuario;
    }

    public String getStrClaveAnterior() {
        return strClaveAnterior;
    }

    public void setStrClaveAnterior(String strClaveAnterior) {
        this.strClaveAnterior = strClaveAnterior;
    }

    public String getStrClaveNueva() {
        return strClaveNueva;
    }

    public void setStrClaveNueva(String strClaveNueva) {
        this.strClaveNueva = strClaveNueva;
    }

    public String getStrClaveNuevaR() {
        return strClaveNuevaR;
    }

    public void setStrClaveNuevaR(String strClaveNuevaR) {
        this.strClaveNuevaR = strClaveNuevaR;
    }

    public String getStrMsjEnviarCorreo() {
        return strMsjEnviarCorreo;
    }

    public void setStrMsjEnviarCorreo(String strMsjEnviarCorreo) {
        this.strMsjEnviarCorreo = strMsjEnviarCorreo;
    }

    public RolUsuario getObjRolUsuario() {
        return objRolUsuario;
    }

    public void setObjRolUsuario(RolUsuario objRolUsuario) {
        this.objRolUsuario = objRolUsuario;
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

    public String getStrMensaje() {
        return strMensaje;
    }

    public void setStrMensaje(String strMensaje) {
        this.strMensaje = strMensaje;
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
