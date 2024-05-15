create table if not exists halls
(
    id              serial
        constraint halls_pk
            primary key,
    title           varchar(100) not null,
    number_of_seats integer      not null
        constraint number_of_seats_check
            check (number_of_seats > 0)
);

comment on table halls is 'Залы в театре.';

comment on column halls.id is 'Идентификатор зала.';

comment on column halls.number_of_seats is 'Количество мест в зале.';

alter table halls
    owner to postgres;

