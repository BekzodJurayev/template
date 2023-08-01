drop table if exists users;

create table users
(
    id                   integer not null
        primary key,
    name                 varchar,
    username             varchar not null
        unique,
    password             varchar,
    password_expire_date timestamp,
    enabled              boolean default false,
    created_date         timestamp
);

drop table if exists roles;

create table roles
(
    id               integer not null
        primary key,
    name             varchar,
    enabled          boolean   default false,
    order_id         integer   default 0,
    description      varchar,
    created_users_id integer,
    created_date     timestamp default CURRENT_TIMESTAMP,
    updated_users_id integer,
    updated_date     timestamp default CURRENT_TIMESTAMP,
    is_default       boolean   default false
);

drop table if exists user_roles;

create table user_roles
(
    id               integer not null
        primary key,
    users_id         integer
        references users,
    roles_id         integer
        references roles,
    created_users_id integer,
    created_date     timestamp default CURRENT_TIMESTAMP
);

drop table if exists role_accesses;

create table role_accesses
(
    id                integer not null
        primary key,
    roles_id          integer
        references roles,
    accessed_roles_id integer
        references roles,
    created_date      timestamp default CURRENT_TIMESTAMP,
    created_users_id  integer
);

drop table if exists permissions;

create table permissions
(
    id               integer not null
        primary key,
    shortname        varchar,
    name             varchar,
    enabled          boolean   default false,
    order_id         integer   default 0,
    description      varchar,
    created_users_id integer,
    created_date     timestamp default CURRENT_TIMESTAMP,
    updated_users_id integer,
    updated_date     timestamp default CURRENT_TIMESTAMP
);

drop table if exists role_permissions;

create table role_permissions
(
    id               integer not null
        primary key,
    roles_id         integer
        references roles,
    permissions_id   integer
        constraint role_permissions_permissions__fk
            references permissions,
    created_users_id integer,
    created_date     timestamp default CURRENT_TIMESTAMP
);

drop sequence if exists users_seq;

create sequence users_seq
    maxvalue 2147483647;

drop sequence if exists user_roles_seq;

create sequence user_roles_seq
    maxvalue 2147483647;

drop sequence if exists roles_seq;

create sequence roles_seq
    maxvalue 2147483647;

drop sequence if exists permissions_seq;

create sequence permissions_seq
    maxvalue 2147483647;

--insert user procedure
create procedure insert_user(IN p_name character,
                             IN p_username character,
                             IN p_password character,
                             IN p_password_expire_date timestamp,
                             IN p_enabled boolean,
                             INOUT p_result integer,
                             INOUT p_new_id integer,
                             INOUT p_res_msg character,
                             INOUT p_log_msg character)
    language plpgsql
as
$$
declare
begin
    p_result := -1;
    p_res_msg := 'System error';
    p_log_msg := 'System error on insert_user()';
    if p_username is null
    then
        p_result := -1;
        p_res_msg := 'Username is not provided.';
        p_log_msg := p_res_msg || '- ERR 0.1 p_username is null';
    end if;
    if p_password is null
    then
        p_result := -1;
        p_res_msg := 'Password is not provided.';
        p_log_msg := p_res_msg || '- ERR 0.1 p_password is null';
    end if;
    if p_password_expire_date is null
    then
        p_password_expire_date := current_timestamp + interval '9 months';
    end if;
    if p_enabled is null
    then
        p_enabled := false;
    end if;

    p_new_id := nextval('users_seq');

    insert into users (id,
                       name,
                       username,
                       password,
                       password_expire_date,
                       enabled,
                       created_date)
    values (p_new_id,
            p_name,
            p_username,
            p_password,
            p_password_expire_date,
            p_enabled,
            current_timestamp);
    p_result := 0;
    p_res_msg := 'Success';
    p_log_msg := 'Success on insert_user()';
end;
$$;

--update user procedure
create procedure update_user(IN p_id integer,
                             IN p_name character,
                             IN p_username character,
                             IN p_password character,
                             IN p_password_expire_date timestamp,
                             IN p_enabled boolean,
                             INOUT p_result integer,
                             INOUT p_res_msg character,
                             INOUT p_log_msg character)
    language plpgsql
as
$$
declare
begin
    p_result := -1;
    p_res_msg := 'System error';
    p_log_msg := 'System error on update_user()';
    if p_id is null
    then
        p_result := -1;
        p_res_msg := 'ID not provided.';
        p_log_msg := p_res_msg || '- ERR 0.1 p_id is null';
    end if;
    if p_username is null
    then
        p_result := -1;
        p_res_msg := 'Username is not provided.';
        p_log_msg := p_res_msg || '- ERR 0.2 p_username is null';
    end if;
    if p_password is null
    then
        p_result := -1;
        p_res_msg := 'Password is not provided.';
        p_log_msg := p_res_msg || '- ERR 0.3 p_password is null';
    end if;
    if p_password_expire_date is null
    then
        p_password_expire_date := current_timestamp + interval '9 months';
    end if;
    if p_enabled is null
    then
        p_enabled := false;
    end if;
    update users
    set username             = p_username,
        name                 = p_name,
        password             = p_password,
        password_expire_date = p_password_expire_date,
        enabled              = p_enabled
    where id = p_id;
    p_result := 0;
    p_res_msg := 'Success';
    p_log_msg := 'Success on update_user()';
exception
    when others then
        p_result := -1;
        p_res_msg := 'System error';
        p_log_msg := 'System error on update_user()';
        rollback;
end;
$$;
