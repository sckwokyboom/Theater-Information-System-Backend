create sequence if not exists theater_id_seq
    as integer;

alter sequence theater_id_seq owner to postgres;



create table if not exists theaters
(
    name varchar(200)                                        not null,
    id   integer default nextval('theater_id_seq'::regclass) not null
        constraint id
            primary key
);

comment on table theaters is 'Театры.';

comment on column theaters.name is 'Название театра.';

comment on column theaters.id is 'Идентификатор театра.';

alter table theaters
    owner to postgres;

alter sequence theater_id_seq owned by theaters.id;