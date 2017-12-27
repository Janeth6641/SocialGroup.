package sg.logica.funciones;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import sg.logica.entidades.Compra;

public class FCompra {

    public static List<Compra> obtenerComprasInactivasDadoCliente(int idCliente) throws Exception {
        List<Compra> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement stm;
        Compra compra;
        ResultSet resultSet;
        String consulta;
        try {
            accesoDatos = new AccesoDatos();
            consulta = "select * from sch_social_group.f_listar_compras_inactivas_dado_cliente(?)";
            stm = accesoDatos.creaPreparedSmt(consulta);
            stm.setInt(1, idCliente);
            resultSet = accesoDatos.ejecutaPrepared(stm);
            while (resultSet.next()) {
                compra = new Compra();

                compra.setIdCompra(resultSet.getInt("sr_id_compra"));
                compra.setRecategorizacion(resultSet.getString("chv_recategorizacion"));
                compra.setValorCompra(resultSet.getDouble("db_valor_compra"));
                compra.setComprobante(resultSet.getString("chv_comprobante"));
                compra.setFechaRegistro(resultSet.getTimestamp("fecha_registro_compra"));
                compra.setFechaVerificacion(resultSet.getTimestamp("ts_fecha_verificacion"));
                compra.setObservaciones(resultSet.getString("chv_observaciones"));

                //datos cuenta
                compra.getCuenta().setIdCuenta(resultSet.getInt("sr_id_cuenta"));
                compra.getCuenta().setCodigo(resultSet.getString("chv_codigo"));

                //datos pif
                compra.getCuenta().getPif().setIdPif(resultSet.getInt("sr_id_pif"));
                compra.getCuenta().getPif().setPif(resultSet.getString("pif"));
                compra.getCuenta().getPif().setDescripcion(resultSet.getString("descripcion_pif"));
                compra.getCuenta().getPif().setCosto(resultSet.getDouble("db_costo"));
                compra.getCuenta().getPif().setFoto(resultSet.getString("imagen_pif"));

                //forma de pago
                compra.getFormaPago().setIdFormaPago(resultSet.getInt("sr_id_forma_pago"));
                compra.getFormaPago().setFormaPago(resultSet.getString("chv_forma_pago"));
                compra.getFormaPago().setDescripcion(resultSet.getString("descrip_forma_pago"));

                //estado de la cuenta
                compra.getEstadoCompra().setIdEstadoCuenta(resultSet.getInt("sr_id_estado_cuenta"));
                compra.getEstadoCompra().setEstado(resultSet.getString("estado_cuenta_compra"));

                lst.add(compra);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String solicitarActivarCompra(Compra compra, int idUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_social_group.f_solicitar_act_compra(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, compra.getIdCompra());
            prstm.setString(2, compra.getComprobante());
            prstm.setInt(3, idUsuario);
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

    public static List<Compra> obtenerComprasPorActivar() throws Exception {
        List<Compra> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        Compra compra;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM sch_social_group.f_listar_compras_por_activar();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                compra = new Compra();
                //datos compra
                compra.setIdCompra(resultSet.getInt("sr_id_compra"));
                compra.setRecategorizacion(resultSet.getString("chv_recategorizacion"));
                compra.setValorCompra(resultSet.getDouble("db_valor_compra"));
                compra.setComprobante(resultSet.getString("chv_comprobante"));
                compra.setFechaRegistro(resultSet.getTimestamp("fecha_registro_compra"));
                compra.setFechaVerificacion(resultSet.getTimestamp("ts_fecha_verificacion"));
                compra.setObservaciones(resultSet.getString("chv_observaciones"));
                compra.setComprobante(resultSet.getString("chv_comprobante"));
                compra.setFechaSolicitud(resultSet.getTimestamp("ts_fecha_solic_act"));

                //datos cuenta
                compra.getCuenta().setIdCuenta(resultSet.getInt("sr_id_cuenta"));
                compra.getCuenta().setCodigo(resultSet.getString("chv_codigo"));

                //datos pif
                compra.getCuenta().getPif().setIdPif(resultSet.getInt("sr_id_pif"));
                compra.getCuenta().getPif().setPif(resultSet.getString("pif"));
                compra.getCuenta().getPif().setDescripcion(resultSet.getString("descripcion_pif"));
                compra.getCuenta().getPif().setCosto(resultSet.getDouble("db_costo"));
                compra.getCuenta().getPif().setFoto(resultSet.getString("imagen_pif"));

                //forma de pago
                compra.getFormaPago().setIdFormaPago(resultSet.getInt("sr_id_forma_pago"));
                compra.getFormaPago().setFormaPago(resultSet.getString("chv_forma_pago"));
                compra.getFormaPago().setDescripcion(resultSet.getString("descrip_forma_pago"));

                //estado de la cuenta
                compra.getEstadoCompra().setIdEstadoCuenta(resultSet.getInt("sr_id_estado_cuenta"));
                compra.getEstadoCompra().setEstado(resultSet.getString("estado_cuenta_compra"));

                //datos del cliente
                compra.getCuenta().getPersona().setCedula(resultSet.getString("cedula_titular"));
                compra.getCuenta().getPersona().setNombres(resultSet.getString("nombres_titular"));
                compra.getCuenta().getPersona().setApellidos(resultSet.getString("apellidos_titular"));
                lst.add(compra);

            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String activarCompra(Compra compra, int idUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_social_group.f_activar_compra_cuenta(?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, compra.getIdCompra());
            prstm.setInt(2, idUsuario);
            prstm.setString(3, compra.getObservaciones());
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

    public static List<Compra> obtenerComprasActivasDadoCliente(int idCliente) throws Exception {
        List<Compra> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement stm;
        Compra compra;
        ResultSet resultSet;
        String consulta;
        try {
            accesoDatos = new AccesoDatos();
            consulta = "select * from sch_social_group.f_obtener_cuentas_activas_dado_usuario(?)";
            stm = accesoDatos.creaPreparedSmt(consulta);
            stm.setInt(1, idCliente);
            resultSet = accesoDatos.ejecutaPrepared(stm);
            while (resultSet.next()) {
                compra = new Compra();

                compra.setIdCompra(resultSet.getInt("sr_id_compra"));
                compra.setRecategorizacion(resultSet.getString("chv_recategorizacion"));
                compra.setValorCompra(resultSet.getDouble("db_valor_compra"));
                compra.setComprobante(resultSet.getString("chv_comprobante"));
                compra.setFechaRegistro(resultSet.getTimestamp("fecha_registro_compra"));
                compra.setFechaVerificacion(resultSet.getTimestamp("ts_fecha_verificacion"));
                compra.setObservaciones(resultSet.getString("chv_observaciones"));

                //datos cuenta
                compra.getCuenta().setIdCuenta(resultSet.getInt("sr_id_cuenta"));
                compra.getCuenta().setCodigo(resultSet.getString("chv_codigo"));

                //datos pif
                compra.getCuenta().getPif().setIdPif(resultSet.getInt("sr_id_pif"));
                compra.getCuenta().getPif().setPif(resultSet.getString("pif"));
                compra.getCuenta().getPif().setDescripcion(resultSet.getString("descripcion_pif"));
                compra.getCuenta().getPif().setCosto(resultSet.getDouble("db_costo"));
                compra.getCuenta().getPif().setFoto(resultSet.getString("imagen_pif"));

                //forma de pago
                compra.getFormaPago().setIdFormaPago(resultSet.getInt("sr_id_forma_pago"));
                compra.getFormaPago().setFormaPago(resultSet.getString("chv_forma_pago"));
                compra.getFormaPago().setDescripcion(resultSet.getString("descrip_forma_pago"));

                //estado de la cuenta
                compra.getEstadoCompra().setIdEstadoCuenta(resultSet.getInt("sr_id_estado_cuenta"));
                compra.getEstadoCompra().setEstado(resultSet.getString("estado_cuenta_compra"));

                lst.add(compra);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

}
