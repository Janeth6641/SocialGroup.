package sg.logica.funciones;

import accesoDatos.AccesoDatos;
import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.funciones.FUsuario;
import sg.logica.entidades.Pif;

public class FPif {

    //<editor-fold defaultstate="collapsed" desc="Obtener PIF ACTIVAs">
    public static List<Pif> obtenerPifActivas() throws Exception {
        List<Pif> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        Pif pif;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM sch_social_group.f_obtener_pif();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                pif = new Pif();
                pif.setIdPif(resultSet.getInt("sr_id_pif"));
                pif.setPif(resultSet.getString("chv_pif"));
                pif.setDescripcion(resultSet.getString("chv_descripcion"));
                pif.setCosto(resultSet.getDouble("db_costo"));
                pif.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                pif.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                pif.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                pif.setSessionUsuario(FUsuario.obtenerUsuarioDadoCodigo(resultSet.getInt("int_id_usuario")));
                pif.setFoto(resultSet.getString("chv_imagen"));
                lst.add(pif);

            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Obtener PIF ACTIVAS SIN PLAN DE COMISIONES">
    public static List<Pif> obtenerPifSinPlanComision() throws Exception {
        List<Pif> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        Pif pif;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM sch_social_group.f_obtener_pif_sin_planComisiones();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                pif = new Pif();
                pif.setIdPif(resultSet.getInt("sr_id_pif"));
                pif.setPif(resultSet.getString("chv_pif"));
                pif.setDescripcion(resultSet.getString("chv_descripcion"));
                pif.setCosto(resultSet.getDouble("db_costo"));
                pif.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                pif.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                pif.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                pif.setSessionUsuario(FUsuario.obtenerUsuarioDadoCodigo(resultSet.getInt("int_id_usuario")));
                pif.setFoto(resultSet.getString("chv_imagen"));
                lst.add(pif);

            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    //</editor-fold>
    
    
    
    
    
    //<editor-fold defaultstate="collapsed" desc="obtener PIF dado Codigo">

    public static Pif obtenerPifDadoId(int codigo) throws Exception {

        AccesoDatos accesoDatos;
        Pif pif = null;
        ResultSet resultSet;
        String sql;
        PreparedStatement prstm;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM sch_social_group.f_obtener_pif_dado_id(?);";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                pif = new Pif();
                pif.setIdPif(resultSet.getInt("sr_id_pif"));
                pif.setPif(resultSet.getString("chv_pif"));
                pif.setDescripcion(resultSet.getString("chv_descripcion"));
                pif.setCosto(resultSet.getDouble("db_costo"));
                pif.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                pif.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                pif.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                pif.setSessionUsuario(FUsuario.obtenerUsuarioDadoCodigo(resultSet.getInt("int_id_usuario")));
                pif.setFoto(resultSet.getString("chv_imagen"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return pif;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="insertar PIF">

    public static String insertarPif(Pif pif, int id) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_social_group.f_insertar_pif(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, pif.getPif());
            prstm.setString(2, pif.getDescripcion());
            prstm.setDouble(3, pif.getCosto());
            prstm.setInt(4, id);
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
    
    //<editor-fold defaultstate="collapsed" desc="editar PIF">

    public static String editarPif(Pif pif) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_social_group.f_actualizar_pif(?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, pif.getPif());
            prstm.setString(2, pif.getDescripcion());
            prstm.setDouble(3, pif.getCosto());
            prstm.setInt(4, pif.getSessionUsuario().getIdPersona());
            prstm.setInt(5, pif.getIdPif());

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

    public static String eliminarPif(Pif pif) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_social_group.f_eliminar_pif(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, pif.getSessionUsuario().getIdPersona());
            prstm.setInt(2, pif.getIdPif());
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
    
    //<editor-fold defaultstate="collapsed" desc=" Comprar PIFIniciador">

    public static String comprarPifIniciador(int idTitular, int idPif, int idFormaPago) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_social_group.f_comprar_mediante_plataforma_iniciador(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idTitular);
            prstm.setInt(2, idPif);
            prstm.setInt(3, idFormaPago);
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
