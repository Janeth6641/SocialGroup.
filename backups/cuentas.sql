create or replace view sch_social_group.v_datos_cuenta as
select cuenta.sr_id_cuenta as id_cuenta,
    cuenta.chv_codigo as codigo_cuenta,
    cuenta.int_id_persona as id_titular,
    cuenta.int_id_referido,
    cuenta.int_id_pif,
    cuenta.int_id_estado_cuenta,
    cuenta.ts_fecha_registro,
    cuenta.ts_fecha_activacion,
    cuenta.ts_fecha_caducidad,
    cuenta.int_id_usuario as id_session_usuario,
    referido.sr_id_cuenta as id_cuenta_referido,
    referido.chv_codigo as codigo_cuenta_referido,
    referido.int_id_persona as id_titular_referido,    
    referido.int_id_pif as id_pif_referido,
    referido.int_id_estado_cuenta as estado_cuenta_referido,
    referido.ts_fecha_registro as fecha_registro_referido,
    referido.ts_fecha_activacion as fecha_activacion_referido,
    referido.ts_fecha_caducidad as fecha_caducidad_referido,
    pif.sr_id_pif,
    pif.chv_pif,
    pif.chv_descripcion as desc_estado_pif,
    pif.db_costo,
    ec.sr_id_estado_cuenta,
    ec.chv_estado as estado_cuenta,
    ec.chv_descripcion as desc_estado_cuenta,
    usu.sr_id_usuario as id_usuario_sess,
    usu.chv_nick,
    per.sr_id_persona,
    per.chv_cedula,
    per.chv_ruc,
    per.chv_pasaporte,
    per.chv_nombres,
    per.chv_apellidos,
    per.chv_telefono,
    per.chv_celular,
    per.chv_foto,
    per.dt_fecha_nacimiento,
    per.ch_genero,
    per.chv_estado_civil,
    per.chv_ciudad,
    per.chv_direccion,
    per.chv_pais,
    per.id_tipo_persona,
    age(per.dt_fecha_nacimiento) as edad_completa,
    date_part('year', age(per.dt_fecha_nacimiento)) as edad,
    refer.sr_id_persona as id_per_referido,
    refer.chv_cedula as cedula_referido,
    refer.chv_ruc as ruc_referido,
    refer.chv_pasaporte as pasaporte_referido,
    refer.chv_nombres as nombres_referido,
    refer.chv_apellidos as apellidos_referido,
    refer.chv_telefono as telefono_referido,
    refer.chv_celular as celular_referido,    
    refer.chv_ciudad as ciudad_referido,
    refer.chv_direccion as direccion_referido,
    refer.chv_pais as pais_referido    
from sch_social_group.t_cuenta as cuenta   
left join sch_social_group.t_cuenta as referido
    on cuenta.int_id_referido = referido.sr_id_cuenta
left join sch_social_group.t_pif as pif
    on cuenta.int_id_pif = pif.sr_id_pif
left join sch_social_group.t_estado_cuenta as ec
    on cuenta.int_id_estado_cuenta=ec.sr_id_estado_cuenta
left join sch_admin.t_usuario as usu
    on cuenta.int_id_usuario = usu.sr_id_usuario
left join sch_admin.t_persona as per
    on cuenta.int_id_persona=per.sr_id_persona
left join sch_admin.t_persona as refer
    on referido.int_id_persona = refer.sr_id_persona


create or replace function sch_social_group.f_obtener_cuentas_dado_estado(_estado integer)
    returns setof sch_social_group.v_datos_cuenta as
$body$
begin
    return query
    select *
    from sch_social_group.v_datos_cuenta
    where int_id_estado_cuenta = _estado
    order by id_cuenta;
end;
$body$
    language plpgsql volatile; 


create or replace function sch_social_group.f_insertar_cuenta_con_referido(_chv_cedula_pasaporte character varying,
                                                            _chv_ruc character varying,                                                            
                                                            _chv_nombres character varying,
                                                            _chv_apellidos character varying,
                                                            _chv_telefono character varying,
                                                            _chv_celular character varying,                                                            
                                                            _dt_fecha_nacimiento date,
                                                            _ch_genero character,
                                                            _chv_estado_civil character varying,
                                                            _chv_ciudad character varying,
                                                            _chv_direccion character varying,
                                                            _chv_pais character varying,
                                                            _id_tipo_persona integer,
                                                            _chv_nick character varying,
                                                            _chv_mail character varying,
                                                            _chv_password character varying,
                                                            -- datos de la cuenta
                                                            _int_id_referido integer,
                                                            _int_id_pif integer)
    returns text as
$body$
declare
    msg text;
    msg_1 text;
    CODIGO_PERSONA integer;
    CODIGO_CUENTA character varying:='CODIGO';
    ESTADO_CUENTA integer;
begin
    begin
        --- REGISTRO A LA PERSONA EN LA BASE DE DATOS
        msg_1 = sch_admin.f_insertar_persona_generico(_chv_cedula_pasaporte,_chv_ruc, _chv_nombres,_chv_apellidos, _chv_telefono, _chv_celular,  _dt_fecha_nacimiento,_ch_genero,_chv_estado_civil,_chv_ciudad,_chv_direccion, _chv_pais, _id_tipo_persona, _chv_nick,_chv_mail, _chv_password);
        
        ---- OBTENGO EL ID DE LA PERSONA REGISTRADA
        select sr_id_persona into CODIGO_PERSONA
        from sch_admin.t_persona
        where chv_cedula=_chv_cedula_pasaporte;
                
        --- BUSCO EL ID DEL ESTADO DE CUENTA 
        SELECT sr_id_estado_cuenta INTO ESTADO_CUENTA
        FROM sch_social_group.t_estado_cuenta
        WHERE chv_estado='PENDIENTE';
        
        --- REGISTRO EN LA TABLA CUENTA
        INSERT INTO sch_social_group.t_cuenta(chv_codigo,int_id_persona,int_id_referido,int_id_pif,int_id_estado_cuenta,int_id_usuario)
        VALUES(CODIGO_CUENTA,CODIGO_PERSONA,_int_id_referido,_int_id_pif,ESTADO_CUENTA,CODIGO_PERSONA);
        
        msg='Cuenta registrada exitosamente.';
    end;
    return msg;
end;
$body$
    language plpgsql volatile;
