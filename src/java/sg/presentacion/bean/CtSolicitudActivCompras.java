/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.presentacion.bean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import master.logica.entidades.Usuario;
import master.logica.funciones.FUsuario;
import org.apache.taglibs.standard.functions.Functions;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import recursos.Util;
import sg.logica.entidades.Compra;
import sg.logica.funciones.FCompra;

/**
 *
 * @author Geovanny Cudco
 */
@ManagedBean
@ViewScoped
public class CtSolicitudActivCompras implements Serializable {

    private Compra compraSel;
    private List<Compra> lstCompras;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private Usuario sessionUsuario;

    //manejo de archivos
    private String nombreDocumento;
    private UploadedFile archivoDocumento;
    //cargar configuracion del  path
    private java.util.ResourceBundle Configuracion = java.util.ResourceBundle.getBundle("recursos.rutasMedia");

    public CtSolicitudActivCompras() {
        compraSel = new Compra();
        sessionUsuario = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    @PostConstruct
    public void init() {
        obtenerSession();
        obtenerCompras();
    }

    public void obtenerSession() {
        try {
            int intIdUsuario = (int) getHttpServletRequest().getSession().getAttribute("idUsuario");
            setSessionUsuario(FUsuario.obtenerUsuarioDadoCodigo(intIdUsuario));
            System.out.println("Usuario Logueado: " + getSessionUsuario().getApellidos() + "id: " + sessionUsuario.getIdUsuario());
        } catch (Exception e) {
            System.out.println("public void obtenerSession() dice: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerCompras() {
        try {
            setLstCompras(FCompra.obtenerComprasInactivasDadoCliente(sessionUsuario.getIdUsuario()));
            System.out.println("Total compras: " + getLstCompras().size());
        } catch (Exception e) {
            System.out.println("public void obtenerCompras() dice: " + e.getMessage());
            Util.addErrorMessage("public void obtenerCompras() dice: " + e.getMessage());
        }
    }

    public void enviarSolicitud() {
        try {
            System.out.println("Id usuario logueado: " + sessionUsuario.getIdUsuario());
            String msg = FCompra.solicitarActivarCompra(compraSel, sessionUsuario.getIdUsuario());
            Util.addSuccessMessage(msg);
            obtenerCompras();
            compraSel = new Compra();
            nombreDocumento = "";
            resetearDataTable("frmPrincipal:tblCompras");
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgSolicitarAct').hide()");
        } catch (Exception e) {
            System.out.println("public void enviarSolicitud() dice: " + e.getMessage());
            Util.addErrorMessage("public void enviarSolicitud() dice: " + e.getMessage());
        }
    }

    public void resetearDataTable(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }

    public void cargarArchivoDocumento(FileUploadEvent e) {
        System.out.println("Entra al método cargar documento");
        UploadedFile file = e.getFile();
        this.setArchivoDocumento(file);
        System.out.println(file.getContentType());
        // getObjDocumento().setTipo(file.getContentType());
        System.out.println(file.getSize());
        System.out.println(file.getFileName());
        setNombreDocumento(file.getFileName());
        //byte[] contenido = file.getContents();
        byte[] contenido;
        try {
            contenido = this.getFileContents(e.getFile().getInputstream());
            if (guardarArchivo(file.getFileName(), contenido)) {
                Util.addSuccessMessage("Documento guardado con éxito!!");
            } else {
                Util.addErrorMessage("Error al cargar el Documento");
            }
        } catch (IOException ex) {
            Logger.getLogger(CtSolicitudActivacion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean guardarArchivo(String nombre, byte[] contenido) {
        String rutaImagenes = getConfiguracion().getString("rutaComprobantes");
        int longitudRelativa = Integer.valueOf(getConfiguracion().getString("logitudRelativa"));
        File f = new File(rutaImagenes + nombre);
        try {
            System.out.println("PATH: " + f.getAbsolutePath());
            System.out.println("Ruta a guardar: " + Functions.substring(f.getAbsolutePath(), longitudRelativa, f.getAbsolutePath().length()));
            // getObjCliente().setPathCedula(Functions.substring(f.getAbsolutePath(), longitudRelativa, f.getAbsolutePath().length()));
            // getObjDocumento().setTitulo(nombre);

            String rutaTemp = Functions.substring(f.getAbsolutePath(), longitudRelativa, f.getAbsolutePath().length());
            getCompraSel().setComprobante(rutaTemp.replace('\\', '/'));

            System.out.println("cargar objeto fos ");
            FileOutputStream fos = new FileOutputStream(f);
            System.out.println("escribir fos ");
            fos.write(contenido);

            return true;
        } catch (Exception e) {
            Util.mostrarMensaje(e.getMessage());
            return false;
        }
    }

    private byte[] getFileContents(InputStream in) {
        byte[] bytes = null;
        try {
            // write the inputStream to a FileOutputStream            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int read = 0;
            bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                bos.write(bytes, 0, read);
            }
            bytes = bos.toByteArray();
            in.close();
            in = null;
            bos.flush();
            bos.close();
            bos = null;
            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return bytes;
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
     * @return the nombreDocumento
     */
    public String getNombreDocumento() {
        return nombreDocumento;
    }

    /**
     * @param nombreDocumento the nombreDocumento to set
     */
    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    /**
     * @return the archivoDocumento
     */
    public UploadedFile getArchivoDocumento() {
        return archivoDocumento;
    }

    /**
     * @param archivoDocumento the archivoDocumento to set
     */
    public void setArchivoDocumento(UploadedFile archivoDocumento) {
        this.archivoDocumento = archivoDocumento;
    }

    /**
     * @return the Configuracion
     */
    public java.util.ResourceBundle getConfiguracion() {
        return Configuracion;
    }

    /**
     * @param Configuracion the Configuracion to set
     */
    public void setConfiguracion(java.util.ResourceBundle Configuracion) {
        this.Configuracion = Configuracion;
    }

}
