package master.logica.funciones;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import master.logica.entidades.TipoPersona;

public class FTipoPersona {

    public static List<TipoPersona> obtenerTiposPersonaDadoEstado(String estado) throws Exception {
        List<TipoPersona> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement stm;
        TipoPersona tp;
        ResultSet resultSet;
        String consulta;
        try {
            accesoDatos = new AccesoDatos();
            consulta = "select * from sch_admin.f_obtener_tipos_persona_dado_estado(?)";
            stm = accesoDatos.creaPreparedSmt(consulta);
            stm.setString(1, estado);
            resultSet = accesoDatos.ejecutaPrepared(stm);
            while (resultSet.next()) {
                tp = new TipoPersona();
                tp.setIdTipoPersona(resultSet.getInt("sr_id_tipo_persona"));
                tp.setTipoPersona(resultSet.getString("chv_tipo_persona"));
                tp.setDescripcion(resultSet.getString("chv_descripcion"));
                tp.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                tp.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                tp.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                tp.setSessionUsuario(FUsuario.obtenerUsuarioDadoCodigo(resultSet.getInt("int_id_usuario")));
                lst.add(tp);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
}
