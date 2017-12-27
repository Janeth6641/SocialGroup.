create or replace function sch_admin.f_obtener_usuario_dado_codigo(codigo_usuario int)
    returns setof sch_admin.v_datos_usuario as
$body$
begin
    return query
    select *
    from sch_admin.v_datos_usuario
    where sr_id_usuario = codigo_usuario
        and ch_estado_logico = 'A';
end;
$body$
    language plpgsql volatile;


create or replace function sch_admin.f_obtener_rol_usuario_dado_codigos(codigo_rol int, codigo_usuario int)
    returns setof sch_admin.t_rol_usuario as
$body$
begin
    return query
    select *
    from sch_admin.t_rol_usuario
    where int_id_rol=codigo_rol
        and int_id_usuario=codigo_usuario;
end;
$body$
    language plpgsql volatile;
    
    
select * from sch_admin.f_obtener_rol_usuario_dado_codigos(1,1);


create or replace function sch_admin.f_obtener_rol_dado_codigo(codigo_rol int)
    returns setof sch_admin.t_rol as
$body$
begin
    return query
    select *
    from sch_admin.t_rol
    where sr_id_rol=codigo_rol;
end;
$body$
    language plpgsql volatile;
    
    
select * from sch_admin.f_obtener_rol_dado_codigo(1);


create or replace function sch_admin.f_insertar_persona_generico(_chv_cedula_pasaporte character varying,
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
                                                            _chv_password character varying)
returns text as
$body$
declare
    msg text;
    CODIGO_PERSONA_USUARIO integer;
begin
    select into CODIGO_PERSONA_USUARIO from sch_admin.seq_t_persona_usuario();
    begin
        /*
            inserto en la tabla persona
        */            
        insert into sch_admin.t_persona(sr_id_persona, 
                                        chv_cedula,
                                        chv_ruc,
                                        chv_pasaporte,
                                        chv_nombres,
                                        chv_apellidos,
                                        chv_telefono,
                                        chv_celular,                                        
                                        dt_fecha_nacimiento,
                                        ch_genero,
                                        chv_estado_civil,
                                        chv_ciudad,
                                        chv_direccion,
                                        chv_pais,
                                        id_tipo_persona)
        values(CODIGO_PERSONA_USUARIO,
               _chv_cedula_pasaporte,
               _chv_ruc,
               _chv_cedula_pasaporte,
               _chv_nombres,
               _chv_apellidos,
               _chv_telefono,
               _chv_celular,
               _dt_fecha_nacimiento,
               _ch_genero,
               _chv_estado_civil,
               _chv_ciudad,
               _chv_direccion,
               _chv_pais,
               _id_tipo_persona);

    /*
        inserto en la tabla usuario
    */
        insert into sch_admin.t_usuario(sr_id_usuario,chv_nick,chv_mail,chv_password)
        values(CODIGO_PERSONA_USUARIO,_chv_nick,_chv_mail,_chv_password);
        msg='Usuario registrado exitosamente.';    
    end;
    return msg;
end;
$body$
    language plpgsql volatile 



create or replace function sch_admin.f_obtener_tipos_persona_dado_estado(estado char)
    returns setof sch_admin.t_tipo_persona as
$body$
begin
    return query
    select *
    from sch_admin.t_tipo_persona
    where ch_estado_logico=estado;
end;
$body$
    language plpgsql volatile 




create or replace function sch_admin.f_registrar_usuario_visitante(_chv_cedula_pasaporte character varying,
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
                                                            _chv_password character varying)
returns text as
$body$
declare
    msg text;
    msg1 text;
    CODIGO_USUARIO integer;
    CODIGO_ROL integer;
begin
    begin
        msg1 = sch_admin.f_insertar_persona_generico(_chv_cedula_pasaporte,
                                                    _chv_ruc,                                                            
                                                    _chv_nombres,
                                                    _chv_apellidos,
                                                    _chv_telefono,
                                                    _chv_celular,                                                            
                                                    _dt_fecha_nacimiento,
                                                    _ch_genero,
                                                    _chv_estado_civil,
                                                    _chv_ciudad,
                                                    _chv_direccion,
                                                    _chv_pais,
                                                    _id_tipo_persona,
                                                    _chv_nick,
                                                    _chv_mail,
                                                    _chv_password);
        
        SELECT sr_id_persona INTO CODIGO_USUARIO 
        FROM sch_admin.t_persona
        WHERE chv_cedula = _chv_cedula_pasaporte;
        
        SELECT sr_id_rol INTO CODIGO_ROL
        FROM sch_admin.t_rol
        WHERE UPPER(chv_rol)='VISITANTE';
        
        insert into sch_admin.t_rol_usuario(int_id_rol,int_id_usuario)
        values(CODIGO_ROL, CODIGO_USUARIO);

        msg='Usuario registrado exitosamente.';
        
    end;
    return msg;
end;
$body$
    language plpgsql volatile 
        