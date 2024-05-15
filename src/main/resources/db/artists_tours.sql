create table if not exists artists_tours
(
    artist_id integer not null
        constraint artists_tours_artist_id_fk
            references artists,
    tour_id   integer not null
        constraint artists_tours_tours_id_fk
            references tours,
    constraint artists_tours_pk
        primary key (tour_id, artist_id)
);

comment on table artists_tours is 'Артисты и гастрольные туры.';

comment on column artists_tours.artist_id is 'Идентификатор артиста.';

comment on column artists_tours.tour_id is 'Идентификатор гастрольного тура.';

alter table artists_tours
    owner to postgres;

