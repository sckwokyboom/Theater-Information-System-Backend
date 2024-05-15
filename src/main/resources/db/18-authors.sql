create table if not exists authors
(
    id                   serial
        constraint authors_pk
            primary key,
    first_name           varchar(100) not null,
    second_name          varchar(100),
    date_of_birth        date,
    date_of_death        date,
    country_of_origin_id integer
        constraint authors_countries_id_fk
            references countries,
    patronymic           varchar(200),
    constraint check_birth_before_death
        check (((date_of_birth IS NULL) AND (date_of_death IS NULL)) OR
               ((date_of_birth IS NOT NULL) AND (date_of_death IS NULL)) OR
               ((date_of_birth IS NOT NULL) AND (date_of_death IS NOT NULL) AND (date_of_birth < date_of_death)))
);

comment on table authors is 'Авторы.';

comment on column authors.id is 'Идентификатор.';

comment on column authors.first_name is 'Имя.';

comment on column authors.second_name is 'Фамилия.';

comment on column authors.date_of_birth is 'Дата рождения.';

comment on column authors.date_of_death is 'Дата смерти (если автор умер).';

comment on column authors.country_of_origin_id is 'Идентификатор страны происхождения.';

comment on column authors.patronymic is 'Отчество.';

comment on constraint check_birth_before_death on authors is 'Проверка, что дата рождения предшествует дате смерти (в случае, если автор умер).';

alter table authors
    owner to postgres;

