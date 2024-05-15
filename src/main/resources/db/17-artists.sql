create sequence if not exists artist_id_seq
    as integer;

alter sequence artist_id_seq owner to postgres;

create table if not exists artists
(
    id          integer default nextval('artist_id_seq'::regclass) not null
        constraint artist_pk
            primary key,
    employee_id integer                                            not null
        constraint artist_employee_id_fk
            references employees
);

comment on table artists is 'Артисты.';

comment on column artists.id is 'Идентификатор.';

comment on column artists.employee_id is 'Идентификатор артиста как работника театра.';

alter table artists
    owner to postgres;


alter sequence artist_id_seq owned by artists.id;

