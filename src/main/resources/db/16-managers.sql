create sequence if not exists manager_id_seq
    as integer;

alter sequence manager_id_seq owner to postgres;

create table if not exists managers
(
    id          integer default nextval('manager_id_seq'::regclass) not null
        constraint manager_pk
            primary key,
    employee_id integer                                             not null
        constraint manager_employee_id_fk
            references employees
);

comment on table managers is 'Менеджеры.';

comment on column managers.id is 'Идентификатор менеджера.';

comment on column managers.employee_id is 'Идентификатор менеджера как работника театра.';

alter table managers
    owner to postgres;

alter sequence manager_id_seq owned by managers.id;

