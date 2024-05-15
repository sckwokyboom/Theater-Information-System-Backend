create table if not exists production_directors_performances
(
    production_director_id integer not null
        constraint production_directors_performances_production_directors_id_fk
            references production_directors,
    performance_id         integer not null
        constraint production_directors_performances_perfomances_id_fk
            references performances,
    constraint production_directors_performances_pk
        primary key (performance_id, production_director_id)
);

comment on table production_directors_performances is 'Режиссёры-постановщики и представления.';

comment on column production_directors_performances.production_director_id is 'Идентификатор режиссёра-постановщика.';

comment on column production_directors_performances.performance_id is 'Идентификатор представления.';

alter table production_directors_performances
    owner to postgres;

