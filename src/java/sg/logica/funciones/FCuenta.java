package sg.logica.funciones;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import sg.logica.entidades.Cuenta;

public class FCuenta {

    public static List<Cuenta> obtenerCuentasDadoEstado(int idEstado) throws Exception {
        List<Cuenta> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement stm;
        Cuenta cuenta;
        ResultSet resultSet;
        String consulta;
        try {
            accesoDatos = new AccesoDatos();
            consulta = "select * from sch_social_group.f_obtener_cuentas_dado_estado(?)";
            stm = accesoDatos.creaPreparedSmt(consulta);
            stm.setInt(1, idEstado);
            resultSet = accesoDatos.ejecutaPrepared(stm);
            while (resultSet.next()) {
                cuenta = new Cuenta();
                //datos cuenta
                cuenta.setIdCuenta(resultSet.getInt("id_cuenta"));
                cuenta.setCodigo(resultSet.getString("codigo_cuenta"));

                //datos del titular
                cuenta.getPersona().setIdPersona(resultSet.getInt("id_titular"));
                cuenta.getPersona().setIdUsuario(resultSet.getInt("sr_id_persona"));
                cuenta.getPersona().setCedula(resultSet.getString("chv_cedula"));
                cuenta.getPersona().setPasaporte(resultSet.getString("chv_pasaporte"));
                cuenta.getPersona().setRuc(resultSet.getString("chv_ruc"));
                cuenta.getPersona().setNombres(resultSet.getString("chv_nombres"));
                cuenta.getPersona().setApellidos(resultSet.getString("chv_apellidos"));
                cuenta.getPersona().setTelefono(resultSet.getString("chv_telefono"));
                cuenta.getPersona().setCelular(resultSet.getString("chv_celular"));
                cuenta.getPersona().setFechaNacimiento(resultSet.getDate("dt_fecha_nacimiento"));
                cuenta.getPersona().setGenero(resultSet.getString("ch_genero"));
                cuenta.getPersona().setEstadoCivil(resultSet.getString("chv_estado_civil"));
                cuenta.getPersona().setCiudad(resultSet.getString("chv_ciudad"));
                cuenta.getPersona().setDireccion(resultSet.getString("chv_direccion"));
                cuenta.getPersona().setPais(resultSet.getString("chv_pais"));
                cuenta.getPersona().setEdad(resultSet.getInt("edad"));

                //datos del referido
                cuenta.getReferidoCuenta().setIdCuenta(resultSet.getInt("id_cuenta_referido"));
                cuenta.getReferidoCuenta().setCodigo(resultSet.getString("codigo_cuenta_referido"));
                cuenta.getReferidoCuenta().getPersona().setIdPersona(resultSet.getInt("id_titular_referido"));
                cuenta.getReferidoCuenta().getPersona().setCedula(resultSet.getString("cedula_referido"));
                cuenta.getReferidoCuenta().getPersona().setPasaporte(resultSet.getString("pasaporte_referido"));
                cuenta.getReferidoCuenta().getPersona().setRuc(resultSet.getString("ruc_referido"));
                cuenta.getReferidoCuenta().getPersona().setNombres(resultSet.getString("nombres_referido"));
                cuenta.getReferidoCuenta().getPersona().setApellidos(resultSet.getString("apellidos_referido"));

                //datos del pif
                cuenta.getPif().setIdPif(resultSet.getInt("sr_id_pif"));
                cuenta.getPif().setPif(resultSet.getString("chv_pif"));
                cuenta.getPif().setDescripcion(resultSet.getString("desc_estado_pif"));
                cuenta.getPif().setCosto(resultSet.getDouble("db_costo"));

                //datos del estado de la cuenta
                cuenta.getEstadoCuenta().setIdEstadoCuenta(resultSet.getInt("sr_id_estado_cuenta"));
                cuenta.getEstadoCuenta().setEstado(resultSet.getString("estado_cuenta"));
                cuenta.getEstadoCuenta().setDescripcion(resultSet.getString("desc_estado_cuenta"));

                ///datos generales de la cuenta
                cuenta.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                cuenta.setFechaActivacion(resultSet.getTimestamp("ts_fecha_activacion"));
                cuenta.setFechaCaducidad(resultSet.getTimestamp("ts_fecha_caducidad"));

                //session de usuario
                cuenta.getSessionUsuario().setIdPersona(resultSet.getInt("id_usuario_sess"));
                cuenta.getSessionUsuario().setIdUsuario(resultSet.getInt("id_usuario_sess"));
                cuenta.getSessionUsuario().setNick(resultSet.getString("chv_nick"));

                lst.add(cuenta);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String registrarCuentaConReferido(Cuenta cuenta) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_social_group.f_insertar_cuenta_con_referido(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, cuenta.getPersona().getCedula());
            prstm.setString(2, cuenta.getPersona().getRuc());
            prstm.setString(3, cuenta.getPersona().getNombres());
            prstm.setString(4, cuenta.getPersona().getApellidos());
            prstm.setString(5, cuenta.getPersona().getTelefono());
            prstm.setString(6, cuenta.getPersona().getCelular());
            prstm.setDate(7, new java.sql.Date(cuenta.getPersona().getFechaNacimiento().getTime()));
            prstm.setString(8, cuenta.getPersona().getGenero());
            prstm.setString(9, cuenta.getPersona().getEstadoCivil());
            prstm.setString(10, cuenta.getPersona().getCiudad());
            prstm.setString(11, cuenta.getPersona().getDireccion());
            prstm.setString(12, cuenta.getPersona().getPais());
            prstm.setInt(13, cuenta.getPersona().getTipoPersona().getIdTipoPersona());
            prstm.setString(14, cuenta.getPersona().getNick());
            prstm.setString(15, cuenta.getPersona().getMail());
            prstm.setString(16, cuenta.getPersona().getPassword());
            prstm.setInt(17, cuenta.getReferidoCuenta().getIdCuenta());
            prstm.setInt(18, cuenta.getPif().getIdPif());
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
    
    
    public static Cuenta obtenerCuenta(String codigo) throws Exception {
        Cuenta cuenta = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM sch_admin.f_obtener_cuenta_dado_codigo_referido(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
               cuenta = new Cuenta();
               cuenta.setIdCuenta(resultSet.getInt("sr_id_cuenta"));
               cuenta.setCodigo(resultSet.getString("chv_codigo"));
               cuenta.getPif().setIdPif(resultSet.getInt("int_id_pif"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return cuenta;
    }
    
    
    
    
    
    
    
}
