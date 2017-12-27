package master.logica.funciones;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.entidades.Rol;

public class FRol {

    public static Rol obtenerRolDadoCodigo(int idRol) throws Exception {
        Rol rol = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM sch_admin.f_obtener_rol_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idRol);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                rol = new Rol();
                rol.setIdRol(resultSet.getInt("sr_id_rol"));
                rol.setRol(resultSet.getString("chv_rol"));
                rol.setDescripcion(resultSet.getString("chv_descripcion"));
                rol.setIcono(resultSet.getString("chv_icono"));
                rol.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                rol.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                rol.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                rol.setSessionUsuario(FUsuario.obtenerUsuarioDadoCodigo(resultSet.getInt("int_id_usuario")));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return rol;
    }

    public static String insertarRol(Rol rol) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_admin.f_insertar_rol(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, rol.getRol());
            prstm.setString(2, rol.getDescripcion());
            prstm.setInt(3, rol.getSessionUsuario().getIdPersona());
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

    public static String editarRol(Rol rol) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_admin.f_editar_rol(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, rol.getRol());
            prstm.setString(2, rol.getDescripcion());
            prstm.setInt(3, rol.getSessionUsuario().getIdPersona());
            prstm.setInt(4, rol.getIdRol());
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

    public static String eliminarRol(Rol rol) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_admin.f_eliminar_rol(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, rol.getSessionUsuario().getIdPersona());
            prstm.setInt(2, rol.getIdRol());
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

    public static List<Rol> obtenerRoles() throws Exception {
        List<Rol> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        Rol rol;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM sch_admin.f_obtener_roles_activos();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                rol = new Rol();
                rol.setIdRol(resultSet.getInt("sr_id_rol"));
                rol.setRol(resultSet.getString("chv_rol"));
                rol.setDescripcion(resultSet.getString("chv_descripcion"));
                rol.setIcono(resultSet.getString("chv_icono"));
                rol.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                rol.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                rol.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                rol.setSessionUsuario(FUsuario.obtenerUsuarioDadoCodigo(resultSet.getInt("int_id_usuario")));
                lst.add(rol);

            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

}
