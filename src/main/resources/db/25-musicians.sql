create table if not exists musicians
(
    id        serial
        constraint musicians_pk
            primary key,
    artist_id integer not null
        constraint musicians_artist_id_fk
            references artists
);

comment on table musicians is 'Музыканты.';

comment on column musicians.id is 'Идентификатор музыканта.';

comment on column musicians.artist_id is 'Идентификатор музыканта как артиста.';

alter table musicians
    owner to postgres;

