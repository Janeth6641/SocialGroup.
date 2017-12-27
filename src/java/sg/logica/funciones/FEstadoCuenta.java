package sg.logica.funciones;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.funciones.FUsuario;
import sg.logica.entidades.EstadoCuenta;

public class FEstadoCuenta {

    public static List<EstadoCuenta> obtenerEstadosCuentaActivas() throws Exception {
        List<EstadoCuenta> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        EstadoCuenta ec;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM sch_social_group.f_obtener_estados_cuenta();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                ec = new EstadoCuenta();
                ec.setIdEstadoCuenta(resultSet.getInt("sr_id_estado_cuenta"));
                ec.setEstado(resultSet.getString("chv_estado"));
                ec.setDescripcion(resultSet.getString("chv_descripcion"));
                ec.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                ec.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                ec.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                ec.setSessionUsuario(FUsuario.obtenerUsuarioDadoCodigo(resultSet.getInt("int_id_usuario")));
                lst.add(ec);

            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static EstadoCuenta obtenerEstadoCuentaDadoCodigo(int codigo) throws Exception {
        EstadoCuenta ec = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM sch_social_group.f_obtener_estado_cuenta_dado_id(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                ec = new EstadoCuenta();
                ec.setIdEstadoCuenta(resultSet.getInt("sr_id_estado_cuenta"));
                ec.setEstado(resultSet.getString("chv_estado"));
                ec.setDescripcion(resultSet.getString("chv_descripcion"));
                ec.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                ec.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                ec.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                ec.setSessionUsuario(FUsuario.obtenerUsuarioDadoCodigo(resultSet.getInt("int_id_usuario")));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return ec;
    }

    public static String insertarEstadoCuenta(EstadoCuenta ec) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_social_group.f_insertar_estado_cuenta(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, ec.getEstado());
            prstm.setString(2, ec.getDescripcion());
            prstm.setInt(3, ec.getSessionUsuario().getIdPersona());
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

    public static String editarEstadoCuenta(EstadoCuenta ec) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_social_group.f_actualizar_estado_cuenta(?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, ec.getEstado());
            prstm.setString(2, ec.getDescripcion());
            prstm.setInt(3, ec.getSessionUsuario().getIdPersona());
            prstm.setInt(4, ec.getIdEstadoCuenta());
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

    public static String eliminarEstadoCuenta(EstadoCuenta ec) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_social_group.f_eliminar_estado_cuenta(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, ec.getSessionUsuario().getIdPersona());
            prstm.setInt(2, ec.getIdEstadoCuenta());
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
