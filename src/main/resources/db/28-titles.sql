create table if not exists titles
(
    id             serial
        constraint titles_pk
            primary key,
    competition_id integer      not null
        constraint titles_competitions_id_fk
            references competitions,
    name           varchar(200) not null
);

comment on table titles is 'Звания актёров.';

comment on column titles.id is 'Идентификатор звания.';

comment on column titles.competition_id is 'Идентификатор соревнования, за которое было получено звание.';

comment on column titles.name is 'Название звания.';

alter table titles
    owner to postgres;

