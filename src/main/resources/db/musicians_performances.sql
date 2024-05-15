create table if not exists musicians_performances
(
    musician_id integer not null
        constraint musicians_performances___fk
            references musicians,
    performance integer not null
        constraint musicians_performances_perfomances_id_fk
            references performances,
    constraint musicians_performances_pk
        primary key (musician_id, performance)
);

comment on table musicians_performances is 'Музыканты и представления.';

comment on column musicians_performances.musician_id is 'Идентификатор музыканта.';

comment on column musicians_performances.performance is 'Идентификатор представления.';

alter table musicians_performances
    owner to postgres;

