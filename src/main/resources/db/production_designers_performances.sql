create table if not exists production_designers_performances
(
    production_designer_id integer not null
        constraint production_designers_performances_production_designers_id_fk
            references production_designers,
    performance_id         integer not null
        constraint production_designers_performances_perfomances_id_fk
            references performances,
    constraint production_designers_performances_pk
        primary key (performance_id, production_designer_id)
);

comment on table production_designers_performances is 'Художники-постановщики и спектакли.';

comment on column production_designers_performances.production_designer_id is 'Идентификатор художника-постановщика.';

comment on column production_designers_performances.performance_id is 'Идентификатор спектакля.';

alter table production_designers_performances
    owner to postgres;

