create table if not exists production_designers
(
    id          serial
        constraint production_designers_pk
            primary key,
    director_id integer not null
        constraint production_designers_directors_id_fk
            references directors
);

comment on table production_designers is 'Художники-постановщики.';

comment on column production_designers.id is 'Идентификатор художника-постановщика.';

comment on column production_designers.director_id is 'Идентификатор художника-постановщика как директора.';

alter table production_designers
    owner to postgres;

