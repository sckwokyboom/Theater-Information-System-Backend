create table if not exists subscriptions
(
    id    serial
        constraint subscriptions_pk
            primary key,
    price numeric not null
        constraint price_check
            check (price >= (0)::numeric)
);

comment on table subscriptions is 'Подписки на билеты.';

comment on column subscriptions.id is 'Идентификатор подписки.';

comment on column subscriptions.price is 'Цена подписки.';

alter table subscriptions
    owner to postgres;

