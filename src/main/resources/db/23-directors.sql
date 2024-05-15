create table if not exists directors
(
    id        serial
        constraint directors_pk
            primary key,
    artist_id integer not null
        constraint directors_artist_id_fk
            references artists
);

comment on table directors is 'Директоры.';

comment on column directors.id is 'Идентификатор.';

comment on column directors.artist_id is 'Идентификатор директора как артиста.';

alter table directors
    owner to postgres;

