create table if not exists castings
(
    actor_id       integer not null
        constraint castings_pk_2
            unique
        constraint castings_actors_id_fk_2
            references actors,
    performance_id integer not null
        constraint actors_performances_perfomances_id_fk
            references performances,
    double_id      integer
        constraint castings_pk_3
            unique
        constraint castings_actors_id_fk
            references actors,
    role_id        integer not null
        constraint castings_roles_id_fk
            references roles,
    constraint castings_pk
        primary key (performance_id, role_id),
    constraint actor_and_double_check
        check (actor_id <> double_id)
);

comment on table castings is 'Актёры и представления.';

comment on column castings.actor_id is 'Идентификатор актёра.';

comment on column castings.performance_id is 'Идентификатор спектакля, в котором участвует актёр.';

comment on column castings.double_id is 'Идентификатор дублирующего актёра.';

comment on column castings.role_id is 'Роль.';

alter table castings
    owner to postgres;

