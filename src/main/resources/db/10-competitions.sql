create table if not exists competitions
(
    id            serial
        constraint competitions_pk
            primary key,
    name          varchar(200) not null,
    date_of_start date         not null,
    date_of_end   date         not null,
    constraint check_date_of_start_before_date_of_end
        check (date_of_start < date_of_end)
);

comment on table competitions is 'Соревнования.';

comment on column competitions.id is 'Идентификатор.';

comment on column competitions.name is 'Название.';

comment on column competitions.date_of_start is 'Дата начала (включительно).';

comment on column competitions.date_of_end is 'Дата завершения (включительно).';

comment on constraint check_date_of_start_before_date_of_end on competitions is 'Проверить, что дата начала соревнования раньше, чем его завершение.';

alter table competitions
    owner to postgres;

