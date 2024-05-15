create table if not exists tickets
(
    id              serial
        constraint tickets_pk
            primary key,
    performance_id  integer not null
        constraint tickets_perfomances_id_fk
            references performances,
    price           numeric not null
        constraint price_check
            check (price >= (0)::numeric),
    place_id        integer not null
        constraint tickets_places_id_fk
            references places,
    subscription_id integer
        constraint tickets_subscriptions_id_fk
            references subscriptions
);

comment on table tickets is 'Билеты на спектакли';

comment on column tickets.id is 'Идентификатор билета.';

comment on column tickets.performance_id is 'Идентификатор спектакля.';

comment on column tickets.price is 'Цена билета в рублях.';

comment on column tickets.place_id is 'Идентификатор места в зале.';

comment on column tickets.subscription_id is 'Идентификатор подписки (если билет входит в подписку).';

alter table tickets
    owner to postgres;



create or replace function public.check_prevent_duplicate_tickets() returns trigger
    language plpgsql
as
$$
DECLARE
    overlapping_count INTEGER;
BEGIN
    SELECT COUNT(*)
    INTO overlapping_count
    FROM tickets
    WHERE performance_id = NEW.performance_id
      AND place_id = NEW.place_id
      AND id != NEW.id;
    IF overlapping_count > 0 THEN
        RAISE EXCEPTION 'Нельзя купить несколько билетов на одно и то же место одного и того же спектакля.';
    END IF;
    RETURN NEW;
END;
$$;

alter function public.check_prevent_duplicate_tickets() owner to postgres;



create trigger check_duplicate_tickets
    before insert or update
    on tickets
    for each row
execute procedure public.check_prevent_duplicate_tickets();

