create table if not exists nationalities
(
    id   serial
        constraint nationalities_pk
            primary key,
    name varchar(200) not null
);

comment on table nationalities is 'Национальности.';

comment on column nationalities.id is 'Идентификатор.';

comment on column nationalities.name is 'Название.';
