package sg.logica.funciones;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.funciones.FUsuario;
import sg.logica.entidades.PlanComisiones;


public class FPlanComisiones {
    
     
     public static List<PlanComisiones> obtenerPlanComisiones() throws Exception {
        List<PlanComisiones> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PlanComisiones planCom;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM sch_social_group.f_obtener_planComisiones();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                planCom = new PlanComisiones();
                planCom.setIdPlanComsion(resultSet.getInt("sr_id_plan_comsion"));
                planCom.setPif(FPif.obtenerPifDadoId(resultSet.getInt("int_id_pif")));
                planCom.setComisionDirecta(resultSet.getDouble("db_comision_directa"));
                planCom.setComisionIndirecta(resultSet.getDouble("db_comision_indirecta"));
                planCom.setComisionRecategoria(resultSet.getDouble("db_comision_recateg"));                
                planCom.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                planCom.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                planCom.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                planCom.setObservaciones(resultSet.getString("chv_iobservaciones"));
                planCom.setSessionUsuario(FUsuario.obtenerUsuarioDadoCodigo(resultSet.getInt("int_id_usuario")));
                lst.add(planCom);

            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }     
    
    
   
public static String insertarPlanComisiones(PlanComisiones planCom, int idPif) throws Exception {
        String respuesta="";
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_social_group.f_insertar_plan_comisiones(?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            
            prstm.setInt(1, idPif);
            prstm.setDouble(2, planCom.getComisionDirecta());
            prstm.setDouble(3, planCom.getComisionIndirecta());
            prstm.setDouble(4, planCom.getComisionRecategoria());
            prstm.setString(5, planCom.getObservaciones());
            prstm.setInt(6, planCom.getSessionUsuario().getIdPersona());
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
    
    
    public static String actualizarPlanComisiones(PlanComisiones planCom) throws Exception {
        String respuesta="";
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_social_group.f_actualizar_plan_comisiones(?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, planCom.getPif().getIdPif());
            prstm.setDouble(2, planCom.getComisionDirecta());
            prstm.setDouble(3, planCom.getComisionIndirecta());
            prstm.setDouble(4, planCom.getComisionRecategoria());
            prstm.setString(5, planCom.getObservaciones());
            prstm.setInt(6, planCom.getSessionUsuario().getIdPersona());
            prstm.setInt(7, planCom.getIdPlanComsion());
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
    
    
       public static String eliminarPlanComisiones(PlanComisiones planCom) throws Exception {
        String respuesta="";
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_social_group.f_eliminar_plan_comisiones(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            //prstm.setInt(1, planCom.getPif().getIdPif());
            prstm.setInt(1, planCom.getSessionUsuario().getIdPersona());
            prstm.setInt(2, planCom.getIdPlanComsion());
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
    
    
}
