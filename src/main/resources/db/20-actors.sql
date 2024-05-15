create table if not exists actors
(
    id          serial
        constraint actors_pk
            primary key,
    artist_id   integer              not null
        constraint actors_artist_id_fk
            references artists,
    voice       voice_type           not null,
    weight      integer              not null
        constraint weight_check
            check (weight > 0),
    height      integer              not null
        constraint height_check
            check (height > 0),
    hair_color  varchar              not null,
    eye_color   varchar              not null,
    skin_color  skin_color_type      not null,
    nationality cng_nationality_type not null
);

comment on table actors is 'Актёры.';

comment on column actors.id is 'Идентификатор.';

comment on column actors.artist_id is 'Идентификатор актёра как артиста.';

comment on column actors.voice is 'Тип голоса.';

comment on column actors.weight is 'Вес в кг.';

comment on column actors.height is 'Рост в см.';

comment on column actors.hair_color is 'Цвет волос.';

comment on column actors.eye_color is 'Цвет глаз.';

comment on column actors.skin_color is 'Цвет кожи.';

comment on column actors.nationality is 'Национальность.';

alter table actors
    owner to postgres;

