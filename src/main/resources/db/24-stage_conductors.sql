create table if not exists stage_conductors
(
    id          serial
        constraint stage_conductors_pk
            primary key,
    director_id integer not null
        constraint stage_conductors_directors_id_fk
            references directors
);

comment on table stage_conductors is 'Дирижёры.';

comment on column stage_conductors.id is 'Идентификатор дирижёра.';

comment on column stage_conductors.director_id is 'Идентификатор дирижёра как директора.';

alter table stage_conductors
    owner to postgres;

