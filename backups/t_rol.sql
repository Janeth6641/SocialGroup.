create or replace function sch_admin.f_obtener_roles_activos()
    returns setof sch_admin.t_rol as
$body$
begin
    return query
    select *
    from sch_admin.t_rol
    where ch_estado_logico = 'A'
    order by chv_rol;
end;
$body$
    language plpgsql volatile;
    
create or replace function sch_admin.f_insertar_rol(_chv_rol character varying, _chv_descripcion character varying,_int_id_usuario integer)
    returns text as
$body$
declare
    msg text;
begin
    begin
        insert into sch_admin.t_rol(chv_rol,chv_descripcion,int_id_usuario)
        values(_chv_rol,_chv_descripcion,_int_id_usuario);
        msg='Rol insertado exitosamente.';
    end;
    return msg;
end;
$body$
    language plpgsql volatile;

create or replace function sch_admin.f_editar_rol(_chv_rol character varying, _chv_descripcion character varying,_int_id_usuario integer,_sr_id_rol integer)
    returns text as
$body$
declare
    msg text;
begin
    begin
        update sch_admin.t_rol
        set chv_rol=_chv_rol,
            chv_descripcion=_chv_descripcion,
            int_id_usuario=_int_id_usuario
        where sr_id_rol=_sr_id_rol;
        msg='Rol actualizado exitosamente.';
    end;
    return msg;
end;
$body$
    language plpgsql volatile;

create or replace function sch_admin.f_eliminar_rol(_int_id_usuario integer,_sr_id_rol integer)
    returns text as
$body$
declare
    msg text;
begin
    begin
        update sch_admin.t_rol
        set ch_estado_logico='D',
            int_id_usuario=_int_id_usuario
        where sr_id_rol=_sr_id_rol;
        msg='Rol eliminado exitosamente.';
    end;
    return msg;
end;
$body$
    language plpgsql volatile;    