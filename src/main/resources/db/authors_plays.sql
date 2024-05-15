create table if not exists authors_plays
(
    author_id integer not null
        constraint authors_plays_authors_id_fk
            references authors,
    play_id   integer not null
        constraint authors_plays_plays_id_fk
            references plays,
    constraint authors_plays_pk
        primary key (play_id, author_id)
);

comment on table authors_plays is 'Авторы и пьесы.';

comment on column authors_plays.author_id is 'Идентификатор автора.';

comment on column authors_plays.play_id is 'Идентификатор пьесы.';

alter table authors_plays
    owner to postgres;

