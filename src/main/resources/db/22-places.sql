create table if not exists places
(
    id                serial
        constraint places_pk
            primary key,
    hall_id           integer not null
        constraint places___fk
            references halls,
    price_coefficient numeric not null
        constraint price_coefficient_check
            check (price_coefficient > (0)::numeric)
);

comment on table places is 'Места в зале.';

comment on column places.id is 'Идентификатор места в зале.';

comment on column places.hall_id is 'Идентификатор зала.';

comment on column places.price_coefficient is 'Коэффициент цены за место.';

alter table places
    owner to postgres;

