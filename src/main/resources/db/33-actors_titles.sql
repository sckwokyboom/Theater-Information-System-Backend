create table if not exists actors_titles
(
    actor_id integer not null
        constraint actors_titles_actors_id_fk
            references actors,
    title_id integer not null
        constraint actors_titles_titles_id_fk
            references titles,
    constraint actors_titles_pk
        primary key (actor_id, title_id)
);

comment on table actors_titles is 'Актёры и звания.';

comment on column actors_titles.actor_id is 'Идентификатор актёра.';

comment on column actors_titles.title_id is 'Идентификатор звания.';

alter table actors_titles
    owner to postgres;

