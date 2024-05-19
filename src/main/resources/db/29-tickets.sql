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
            references subscriptions,
    sale_date       date    not null
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

CREATE OR REPLACE FUNCTION check_place_performance_hall()
    RETURNS TRIGGER AS $$
DECLARE
    performance_hall_id INTEGER;
    place_hall_id INTEGER;
BEGIN
    -- Получаем hall_id для performance_id
    SELECT hall_id INTO performance_hall_id
    FROM performances
    WHERE id = NEW.performance_id;

    -- Получаем hall_id для place_id
    SELECT hall_id INTO place_hall_id
    FROM places
    WHERE id = NEW.place_id;

    -- Проверяем, совпадают ли hall_id
    IF performance_hall_id IS DISTINCT FROM place_hall_id THEN
        RAISE EXCEPTION 'Place_id % does not belong to the same hall as performance_id %', NEW.place_id, NEW.performance_id;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trigger_check_place_performance_hall
    BEFORE INSERT ON tickets
    FOR EACH ROW
EXECUTE FUNCTION check_place_performance_hall();


create trigger check_duplicate_tickets
    before insert or update
    on tickets
    for each row
execute procedure public.check_prevent_duplicate_tickets();

