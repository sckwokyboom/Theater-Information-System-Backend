create table if not exists countries
(
    id   serial
        constraint countries_pk
            primary key,
    name varchar(200) not null
);

comment on column countries.id is 'Идентификатор.';

comment on column countries.name is 'Название страны.';

alter table countries
    owner to postgres;

