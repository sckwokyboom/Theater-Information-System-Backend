create table if not exists actors_competitions
(
    actor_id       integer not null
        constraint actors_competitions_actors_id_fk
            references actors,
    competition_id integer not null
        constraint actors_competitions_competitions_id_fk
            references competitions,
    constraint actors_competitions_pk
        primary key (competition_id, actor_id)
);

comment on table actors_competitions is 'Актёры и соревнования.';

comment on column actors_competitions.actor_id is 'Идентификатор актёра.';

comment on column actors_competitions.competition_id is 'Идентификатор соревнования, в котором участвует актёр.';

alter table actors_competitions
    owner to postgres;

