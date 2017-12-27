package sg.logica.funciones;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.funciones.FUsuario;
import sg.logica.entidades.FormaPago;

public class FFormaPago {

    public static List<FormaPago> obtenerFormasPagoActivas() throws Exception {
        List<FormaPago> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        FormaPago formaPago;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM sch_social_group.f_obtener_formas_pago_activas();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                formaPago = new FormaPago();
                formaPago.setIdFormaPago(resultSet.getInt("sr_id_forma_pago"));
                formaPago.setFormaPago(resultSet.getString("chv_forma_pago"));
                formaPago.setDescripcion(resultSet.getString("chv_descripcion"));
                formaPago.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                formaPago.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                formaPago.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                formaPago.setSessionUsuario(FUsuario.obtenerUsuarioDadoCodigo(resultSet.getInt("int_id_usuario")));
                lst.add(formaPago);

            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    
  //<editor-fold defaultstate="collapsed" desc="Ingresar FORMA PAGO">
 public static String insertarFormaPago(FormaPago fp) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_social_group.f_insertar_forma_pago(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, fp.getFormaPago());
            prstm.setString(2, fp.getDescripcion());
            prstm.setInt(3, fp.getSessionUsuario().getIdUsuario());
           
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            if (resultSet.next()) {
                respuesta = resultSet.getString(1);
                return respuesta;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }
 
   


    //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="MODIFICAR FORMA PAGO">
  public static String editarFormaPago(FormaPago fp) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_social_group.f_actualizar_forma_pago(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, fp.getFormaPago());
            prstm.setString(2, fp.getDescripcion());
            prstm.setInt(3, fp.getSessionUsuario().getIdUsuario());
            //prstm.setInt(3, fp.getSessionUsuario().getIdUsuario());
            prstm.setInt(4, fp.getIdFormaPago());
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            if (resultSet.next()) {
                respuesta = resultSet.getString(1);
                return respuesta;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }
 
 
 //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="ELIMINAAR FORMA PAGO">
   public static String eliminarFormaPago(FormaPago fp) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_social_group.f_eliminar_forma_pago(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, fp.getSessionUsuario().getIdPersona());
            prstm.setInt(2, fp.getIdFormaPago());
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            if (resultSet.next()) {
                respuesta = resultSet.getString(1);
                return respuesta;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }

   //</editor-fold>
    
    
    
}
