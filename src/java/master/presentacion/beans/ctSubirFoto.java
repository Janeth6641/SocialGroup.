/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master.presentacion.beans;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.io.InputStream;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import java.io.OutputStream;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import recursos.Util;

@ManagedBean
@ViewScoped
public class ctSubirFoto {
    private String strRutaFoto;
    private String strDestFoto;
    private String strNombFoto;
    private StreamedContent files;
    
     //<editor-fold defaultstate="collapsed" desc="constructor del controlador subir foto">
    public ctSubirFoto() {
        
        strRutaFoto = String.valueOf(ResourceBundle.getBundle("/recursos/VariablesGlobales").getString("rutaFoto"));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructor del controlador subir foto">
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Subir Foto de Usuarios">
  
    public void actualizarFotoPerfil(FileUploadEvent event) {
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
            ctDatos objDatos = new ctDatos();
            objDatos.cambiarFotoUsuarioPerfil(strNombFoto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

   //<editor-fold defaultstate="collapsed" desc="Método para copiar archivos">
    
    public void copyFile(String strFileName, InputStream in) {
        try {
            OutputStream out = new FileOutputStream(new File(strRutaFoto + strFileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
            Date date = new Date();
            String ruta1 = strRutaFoto + strFileName;
            String ruta2 = strRutaFoto + dateFormat.format(date) + "-" + strFileName;
            String ruta3 = dateFormat.format(date) + "-" + strFileName;
            this.setStrDestFoto(ruta2);
            this.setStrNombFoto(ruta3);
            File archivo = new File(ruta1);
            archivo.renameTo(new File(ruta2));
        } catch (IOException e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
   //</editor-fold>

   //<editor-fold defaultstate="collapsed" desc="Métodos Set y Get del Controlador cSubirFoto">
    //Autor: Rubèn Pilco Fecha: 2016-12-14
    public String getStrRutaFoto() {
        return strRutaFoto;
    }

    public void setStrRutaFoto(String strRutaFoto) {
        this.strRutaFoto = strRutaFoto;
    }

    public String getStrDestFoto() {
        return strDestFoto;
    }

    public void setStrDestFoto(String strDestFoto) {
        this.strDestFoto = strDestFoto;
    }

    public String getStrNombFoto() {
        return strNombFoto;
    }

    public void setStrNombFoto(String strNombFoto) {
        this.strNombFoto = strNombFoto;
    }

    public StreamedContent getFiles() {
        return files;
    }

    public void setFiles(StreamedContent files) {
        this.files = files;
    }
   //</editor-fold>
}