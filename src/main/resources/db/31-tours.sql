create table if not exists tours
(
    id                      serial
        constraint tours_pk
            primary key,
    play_id                 integer not null
        constraint tours_plays_id_fk
            references plays,
    date_of_start           date    not null,
    date_of_end             date    not null,
    tour_theater_id         integer not null
        constraint tours_theater_id_fk
            references theaters,
    organization_theater_id integer not null
        constraint tours_theater_id_fk_2
            references theaters,
    constraint check_theaters_not_equals
        check (tour_theater_id <> organization_theater_id),
    constraint check_date_of_start_before_date_of_end
        check (date_of_start <= date_of_end)
);

comment on table tours is 'Гастрольные туры.';

comment on column tours.id is 'Индекс гастрольного тура.';

comment on column tours.play_id is 'Пьеса, с которой совершается гастрольный тур.';

comment on column tours.date_of_start is 'Дата начала гастрольного тура (включительно).';

comment on column tours.date_of_end is 'Дата конца гастрольного тура (включительно).';

comment on column tours.tour_theater_id is 'Театр, в который совершается гастрольный тур.';

comment on column tours.organization_theater_id is 'Театр, от которого производится гастрольный тур.';

comment on constraint check_theaters_not_equals on tours is 'Проверить, что театр-организатор гастрольного тура не совпадает с театром, в который этот гастрольный тур совершается.';

comment on constraint check_date_of_start_before_date_of_end on tours is 'Проверить, что дата начала гастрольного тура раньше конца гастрольного тура (или совпадает с ней).';

alter table tours
    owner to postgres;

