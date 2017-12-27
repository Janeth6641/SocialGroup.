package master.logica.funciones;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.entidades.RolUsuario;
import master.logica.entidades.Usuario;

public class FUsuario {

    public static Usuario selectUsuario;

    public static Usuario loginUsuario(String correo, String clave) throws Exception {
        Usuario usuario = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM sch_admin.f_login_usuario(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, correo);
            prstm.setString(2, clave);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                usuario = new Usuario();
                usuario.setIdPersona(resultSet.getInt("sr_id_persona"));
                usuario.setIdUsuario(resultSet.getInt("sr_id_persona"));
                usuario.setCedula(resultSet.getString("chv_cedula"));
                usuario.setRuc(resultSet.getString("chv_ruc"));
                usuario.setPasaporte(resultSet.getString("chv_pasaporte"));
                usuario.setNombres(resultSet.getString("chv_nombres"));
                usuario.setApellidos(resultSet.getString("chv_apellidos"));
                usuario.setCelular(resultSet.getString("chv_celular"));
                usuario.setTelefono(resultSet.getString("chv_telefono"));
                usuario.setFoto(resultSet.getString("chv_foto"));
                usuario.setFechaNacimiento(resultSet.getDate("dt_fecha_nacimiento"));
                usuario.setGenero(resultSet.getString("ch_genero"));
                usuario.setEstadoCivil(resultSet.getString("chv_estado_civil"));
                usuario.setCiudad(resultSet.getString("chv_ciudad"));
                usuario.setDireccion(resultSet.getString("chv_direccion"));
                usuario.setNick(resultSet.getString("chv_nick"));
                usuario.setMail(resultSet.getString("chv_mail"));
                usuario.setPassword(resultSet.getString("chv_password"));
                usuario.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                usuario.setFechaBaja(resultSet.getTimestamp("ts_fecha_registro"));
                usuario.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                usuario.setValidado(resultSet.getString("ch_validado"));
                usuario.setFechaValidacion(resultSet.getTimestamp("ts_fecha_validacion"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }

    public static Usuario obtenerUsuarioDadoCodigo(int codigo) throws Exception {
        Usuario usuario = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM sch_admin.f_obtener_usuario_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                usuario = new Usuario();
                usuario.setIdPersona(resultSet.getInt("sr_id_persona"));
                usuario.setIdUsuario(resultSet.getInt("sr_id_persona"));
                usuario.setCedula(resultSet.getString("chv_cedula"));
                usuario.setRuc(resultSet.getString("chv_ruc"));
                usuario.setPasaporte(resultSet.getString("chv_pasaporte"));
                usuario.setNombres(resultSet.getString("chv_nombres"));
                usuario.setApellidos(resultSet.getString("chv_apellidos"));
                usuario.setCelular(resultSet.getString("chv_celular"));
                usuario.setTelefono(resultSet.getString("chv_telefono"));
                usuario.setFoto(resultSet.getString("chv_foto"));
                usuario.setFechaNacimiento(resultSet.getDate("dt_fecha_nacimiento"));
                usuario.setGenero(resultSet.getString("ch_genero"));
                usuario.setEstadoCivil(resultSet.getString("chv_estado_civil"));
                usuario.setCiudad(resultSet.getString("chv_ciudad"));
                usuario.setDireccion(resultSet.getString("chv_direccion"));
                usuario.setNick(resultSet.getString("chv_nick"));
                usuario.setMail(resultSet.getString("chv_mail"));
                usuario.setPassword(resultSet.getString("chv_password"));
                usuario.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                usuario.setFechaBaja(resultSet.getTimestamp("ts_fecha_registro"));
                usuario.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                usuario.setValidado(resultSet.getString("ch_validado"));
                usuario.setFechaValidacion(resultSet.getTimestamp("ts_fecha_validacion"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }

    public static Usuario obtenerUsuarioDadoId(int intIdUsuario) throws Exception {
        Usuario usuario;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM sch_admin.f_obtener_usuario_dado_codigo(?);";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, intIdUsuario);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            usuario = new Usuario();
            while (resultSet.next()) {
                usuario.setIdPersona(resultSet.getInt("sr_id_persona"));
                //usuario.setApellidos(resultSet.getString(sql));
                usuario.setIdUsuario(resultSet.getInt("sr_id_persona"));
                usuario.setCedula(resultSet.getString("chv_cedula"));
                usuario.setRuc(resultSet.getString("chv_ruc"));
                usuario.setPasaporte(resultSet.getString("chv_pasaporte"));
                usuario.setNombres(resultSet.getString("chv_nombres"));
                usuario.setApellidos(resultSet.getString("chv_apellidos"));
                usuario.setCelular(resultSet.getString("chv_celular"));
                usuario.setTelefono(resultSet.getString("chv_telefono"));
                usuario.setFoto(resultSet.getString("chv_foto"));
                usuario.setFechaNacimiento(resultSet.getDate("dt_fecha_nacimiento"));
                usuario.setGenero(resultSet.getString("ch_genero"));
                usuario.setEstadoCivil(resultSet.getString("chv_estado_civil"));
                usuario.setCiudad(resultSet.getString("chv_ciudad"));
                usuario.setDireccion(resultSet.getString("chv_direccion"));
                usuario.setNick(resultSet.getString("chv_nick"));
                usuario.setMail(resultSet.getString("chv_mail"));
                usuario.setPassword(resultSet.getString("chv_password"));
                usuario.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                usuario.setFechaBaja(resultSet.getTimestamp("ts_fecha_registro"));
                usuario.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                usuario.setValidado(resultSet.getString("ch_validado"));
                usuario.setFechaValidacion(resultSet.getTimestamp("ts_fecha_validacion"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }

    public static String registrarUsuario(Usuario usuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_admin.f_insertar_persona_generico(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, usuario.getCedula());
            prstm.setString(2, usuario.getRuc());
            prstm.setString(3, usuario.getNombres());
            prstm.setString(4, usuario.getApellidos());
            prstm.setString(5, usuario.getTelefono());
            prstm.setString(6, usuario.getCelular());
            prstm.setDate(7, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            prstm.setString(8, usuario.getGenero());
            prstm.setString(9, usuario.getEstadoCivil());
            prstm.setString(10, usuario.getCiudad());
            prstm.setString(11, usuario.getDireccion());
            prstm.setString(12, usuario.getPais());
            prstm.setInt(13, usuario.getTipoPersona().getIdTipoPersona());
            prstm.setString(14, usuario.getNick());
            prstm.setString(15, usuario.getMail());
            prstm.setString(16, usuario.getPassword());
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

    public static String registrarUsuarioVisitante(Usuario usuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_admin.f_registrar_usuario_visitante(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, usuario.getCedula());
            prstm.setString(2, usuario.getRuc());
            prstm.setString(3, usuario.getNombres());
            prstm.setString(4, usuario.getApellidos());
            prstm.setString(5, usuario.getTelefono());
            prstm.setString(6, usuario.getCelular());
            prstm.setDate(7, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            prstm.setString(8, usuario.getGenero());
            prstm.setString(9, usuario.getEstadoCivil());
            prstm.setString(10, usuario.getCiudad());
            prstm.setString(11, usuario.getDireccion());
            prstm.setString(12, usuario.getPais());
            prstm.setInt(13, usuario.getTipoPersona().getIdTipoPersona());
            prstm.setString(14, usuario.getNick());
            prstm.setString(15, usuario.getMail());
            prstm.setString(16, usuario.getPassword());
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

    public static String solicitudActivacion(int idUsuario, String path) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_admin.f_solicitar_activacion_usuario(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idUsuario);
            prstm.setString(2, path);
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

    public static List<Usuario> obtenerSolictudesActivacion() throws Exception {
        List<Usuario> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        Usuario usuario;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM sch_admin.f_ver_solicitudes_activacion();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                usuario = new Usuario();
                usuario.setIdPersona(resultSet.getInt("sr_id_persona"));
                usuario.setIdUsuario(resultSet.getInt("sr_id_persona"));
                usuario.setCedula(resultSet.getString("chv_cedula"));
                usuario.setRuc(resultSet.getString("chv_ruc"));
                usuario.setPasaporte(resultSet.getString("chv_pasaporte"));
                usuario.setNombres(resultSet.getString("chv_nombres"));
                usuario.setApellidos(resultSet.getString("chv_apellidos"));
                usuario.setCelular(resultSet.getString("chv_celular"));
                usuario.setTelefono(resultSet.getString("chv_telefono"));
                usuario.setFoto(resultSet.getString("chv_foto"));
                usuario.setFechaNacimiento(resultSet.getDate("dt_fecha_nacimiento"));
                usuario.setGenero(resultSet.getString("ch_genero"));
                usuario.setEstadoCivil(resultSet.getString("chv_estado_civil"));
                usuario.setCiudad(resultSet.getString("chv_ciudad"));
                usuario.setDireccion(resultSet.getString("chv_direccion"));
                usuario.setNick(resultSet.getString("chv_nick"));
                usuario.setMail(resultSet.getString("chv_mail"));
                usuario.setPassword(resultSet.getString("chv_password"));
                usuario.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                usuario.setFechaBaja(resultSet.getTimestamp("ts_fecha_registro"));
                usuario.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                usuario.setValidado(resultSet.getString("ch_validado"));
                usuario.setFechaValidacion(resultSet.getTimestamp("ts_fecha_validacion"));
                usuario.setPathCedula(resultSet.getString("chv_path_deula"));
                lst.add(usuario);

            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String validarCuenta(int idUsuario, int idSessionUsuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_admin.f_validar_usuario(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idUsuario);
            prstm.setInt(2, idSessionUsuario);
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
    //<editor-fold defaultstate="collapsed" desc=" Funcion para foto usuario">

    public static String actualizarFotoUsuario(int codigo, String foto) throws Exception {
        //Usuario usuario = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        String respuesta = " ";
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM sch_admin.f_actualizar_foto_usuario(?,?);";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            prstm.setString(2, foto);
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

    //<editor-fold defaultstate="collapsed" desc="Actualizar Datos de Perfil Usuario">
    public static String actualizarDatosUsuarioPerfil(Usuario objUsuario) throws Exception {
        AccesoDatos accesoDatos;
        String strRespuesta;
        String sql;
        int intIdRol, strClave, strRolDescrip;
        PreparedStatement prstm;
        ResultSet resultSet;

        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT sch_admin.f_actualizar_persona_no_foto(?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);

            prstm.setString(1, objUsuario.getCedula());
            prstm.setString(2, objUsuario.getNombres());
            prstm.setString(3, objUsuario.getApellidos());
            prstm.setString(4, objUsuario.getTelefono());
            prstm.setString(5, objUsuario.getCelular());
            prstm.setString(6, objUsuario.getEstadoCivil());
            prstm.setString(7, objUsuario.getCiudad());
            prstm.setString(8, objUsuario.getDireccion());
            prstm.setString(9, objUsuario.getPais());
            //prstm.setInt(10, objUsuario.getIdUsuario());
            prstm.setString(10, objUsuario.getNick());
            prstm.setString(10, objUsuario.getMail());
            //prstm.setString(12, objRolUsuario.getUsuario().getMail().toLowerCase());
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            if (resultSet.next()) {
                strRespuesta = resultSet.getString(1);
                return strRespuesta;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }
    //</editor-fold>  

    public static List<Usuario> obtenerUsuariosDadoLider(int codigoLider) throws Exception {
        List<Usuario> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement stm;
        Usuario usuario;
        ResultSet resultSet;
        String consulta;
        try {
            accesoDatos = new AccesoDatos();
            consulta = "select * from sch_admin.f_obtener_cuentas_dado_lider(?)";
            stm = accesoDatos.creaPreparedSmt(consulta);
            stm.setInt(1, codigoLider);
            resultSet = accesoDatos.ejecutaPrepared(stm);
            while (resultSet.next()) {
                usuario = new Usuario();
                usuario.setIdPersona(resultSet.getInt("sr_id_persona"));
                usuario.setIdUsuario(resultSet.getInt("sr_id_persona"));
                usuario.setCedula(resultSet.getString("chv_cedula"));
                usuario.setRuc(resultSet.getString("chv_ruc"));
                usuario.setPasaporte(resultSet.getString("chv_pasaporte"));
                usuario.setNombres(resultSet.getString("chv_nombres"));
                usuario.setApellidos(resultSet.getString("chv_apellidos"));
                usuario.setCelular(resultSet.getString("chv_celular"));
                usuario.setTelefono(resultSet.getString("chv_telefono"));
                usuario.setFoto(resultSet.getString("chv_foto"));
                usuario.setFechaNacimiento(resultSet.getDate("dt_fecha_nacimiento"));
                usuario.setGenero(resultSet.getString("ch_genero"));
                usuario.setEstadoCivil(resultSet.getString("chv_estado_civil"));
                usuario.setCiudad(resultSet.getString("chv_ciudad"));
                usuario.setDireccion(resultSet.getString("chv_direccion"));
                usuario.setNick(resultSet.getString("chv_nick"));
                usuario.setMail(resultSet.getString("chv_mail"));
                usuario.setPassword(resultSet.getString("chv_password"));
                usuario.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                usuario.setFechaBaja(resultSet.getTimestamp("ts_fecha_registro"));
                usuario.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                usuario.setValidado(resultSet.getString("ch_validado"));
                usuario.setFechaValidacion(resultSet.getTimestamp("ts_fecha_validacion"));
                usuario.setPathCedula(resultSet.getString("chv_path_deula"));
                lst.add(usuario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String registrarUsuarioVisitanteDadoLider(Usuario usuario, int codigoLider) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from sch_admin.f_registrar_cuenta_dado_lider(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, usuario.getCedula());
            prstm.setString(2, usuario.getNombres());
            prstm.setString(3, usuario.getApellidos());
            prstm.setString(4, usuario.getTelefono());
            prstm.setString(5, usuario.getCelular());
            prstm.setDate(6, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            prstm.setString(7, usuario.getGenero());
            prstm.setString(8, usuario.getEstadoCivil());
            prstm.setString(9, usuario.getCiudad());
            prstm.setString(10, usuario.getDireccion());
            prstm.setString(11, usuario.getPais());
            prstm.setString(12, usuario.getNick());
            prstm.setString(13, usuario.getMail());
            prstm.setString(14, usuario.getPassword());
            prstm.setString(15, usuario.getPathCedula());
            prstm.setInt(16, codigoLider);
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
