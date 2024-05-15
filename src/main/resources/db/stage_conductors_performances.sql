create table if not exists stage_conductors_performances
(
    stage_conductor_id integer not null
        constraint stage_conductors_performances_stage_conductors_id_fk
            references stage_conductors,
    performance_id     integer not null
        constraint stage_conductors_performances_perfomances_id_fk
            references performances,
    constraint stage_conductors_performances_pk
        primary key (stage_conductor_id, performance_id)
);

comment on table stage_conductors_performances is 'Дирижёры и спектакли.';

comment on column stage_conductors_performances.stage_conductor_id is 'Идентификатор дирижёра.';

comment on column stage_conductors_performances.performance_id is 'Идентификатор спектакля.';

alter table stage_conductors_performances
    owner to postgres;

