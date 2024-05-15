create table if not exists plays
(
    id       serial
        constraint plays_pk
            primary key,
    title    varchar(200) not null,
    genre_id integer      not null
        constraint play_genre_id_fk
            references genres,
    century  integer      not null
        constraint check_century_is_not_zero
            check (century <> 0)
);

comment on table plays is 'Пьесы.';

comment on column plays.id is 'Идентификатор пьесы.';

comment on column plays.title is 'Название пьесы.';

comment on column plays.genre_id is 'Жанр.';

comment on column plays.century is 'Век, в котором пьеса была написана.';

comment on constraint check_century_is_not_zero on plays is 'Проверить, что век не является нулевым.';

alter table plays
    owner to postgres;

