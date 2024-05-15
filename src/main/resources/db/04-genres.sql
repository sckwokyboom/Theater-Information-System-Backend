CREATE TABLE IF NOT EXISTS genres
(
    id
        serial
        constraint
            genres_pk
            primary
                key,
    title
        varchar(100) not null
);

comment on table genres is 'Жанры представлений.';

comment on column genres.id is 'Идентификатор.';

comment on column genres.title is 'Название.';

alter table genres
    owner to postgres;

