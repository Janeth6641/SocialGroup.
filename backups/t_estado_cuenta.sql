create or replace function sch_social_group.f_obtener_estados_cuenta()
    returns setof sch_social_group.t_estado_cuenta as
$body$
begin
    return query
    select *
    from sch_social_group.t_estado_cuenta
    where ch_estado_logico='A'
    ORDER by chv_estado;
end;
$body$
    language plpgsql volatile;



create or replace function sch_social_group.f_obtener_estado_cuenta_dado_id(codigo_estado integer)
    returns setof sch_social_group.t_estado_cuenta as
$body$
begin
    return query
    select *
    from sch_social_group.t_estado_cuenta
    where ch_estado_logico='A'
        and sr_id_estado_cuenta=codigo_estado
    ORDER by chv_estado;
end;
$body$
    language plpgsql volatile;


create or replace function sch_social_group.f_insertar_estado_cuenta(_chv_estado character varying,_chv_descripcion character varying,_int_id_usuario integer)
    returns text as
$body$
declare
    msg text;
begin
    begin
        insert into sch_social_group.t_estado_cuenta(chv_estado,chv_descripcion,int_id_usuario)
        values(_chv_estado,_chv_descripcion,_int_id_usuario);
        msg='Estado de cuenta registrado exitosamente.';
    end;
    return msg;
end;
$body$
    language plpgsql volatile;

create or replace function sch_social_group.f_actualizar_estado_cuenta(_chv_estado character varying,
                                                                       _chv_descripcion character varying,
                                                                       _int_id_usuario integer,
                                                                       _id_estado_cuenta integer)
    returns text as
$body$
declare
    msg text;
begin
    begin
        update sch_social_group.t_estado_cuenta
        set chv_estado=_chv_estado,
            chv_descripcion=_chv_descripcion,
            int_id_usuario=_int_id_usuario
        where sr_id_estado_cuenta=_id_estado_cuenta;
        msg='Estado de cuenta actualizado exitosamente.';
    end;
    return msg;
end;
$body$
    language plpgsql volatile;
    

create or replace function sch_social_group.f_eliminar_estado_cuenta(_int_id_usuario integer,
                                                                     _id_estado_cuenta integer)
    returns text as
$body$
declare
    msg text;
begin
    begin
        update sch_social_group.t_estado_cuenta
        set ts_fecha_baja=now(),
            int_id_usuario=_int_id_usuario
        where sr_id_estado_cuenta=_id_estado_cuenta;
        msg='Estado de cuenta eliminado exitosamente.';
    end;
    return msg;
end;
$body$
    language plpgsql volatile;    