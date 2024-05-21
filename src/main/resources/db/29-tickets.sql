CREATE TABLE IF NOT EXISTS tickets
(
    id              SERIAL
        CONSTRAINT tickets_pk
            PRIMARY KEY,
    performance_id  INTEGER NOT NULL
        CONSTRAINT tickets_perfomances_id_fk
            REFERENCES performances,
    price           NUMERIC NOT NULL
        CONSTRAINT price_check
            CHECK (price >= (0)::NUMERIC),
    place_id        INTEGER NOT NULL
        CONSTRAINT tickets_places_id_fk
            REFERENCES places,
    subscription_id INTEGER
        CONSTRAINT tickets_subscriptions_id_fk
            REFERENCES subscriptions,
    sale_date       DATE    NOT NULL
);

COMMENT ON TABLE tickets IS 'Билеты на спектакли';

COMMENT ON COLUMN tickets.id IS 'Идентификатор билета.';

COMMENT ON COLUMN tickets.performance_id IS 'Идентификатор спектакля.';

COMMENT ON COLUMN tickets.price IS 'Цена билета в рублях.';

COMMENT ON COLUMN tickets.place_id IS 'Идентификатор места в зале.';

COMMENT ON COLUMN tickets.subscription_id IS 'Идентификатор подписки (если билет входит в подписку).';

CREATE OR REPLACE FUNCTION public.check_prevent_duplicate_tickets() RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
DECLARE
    overlapping_count INTEGER;
BEGIN
    SELECT COUNT(*)
    INTO overlapping_count
    FROM tickets
    WHERE performance_id = new.performance_id
      AND place_id = new.place_id
      AND id != new.id;
    IF overlapping_count > 0 THEN
        RAISE EXCEPTION 'Нельзя купить несколько билетов на одно и то же место одного и того же спектакля.';
    END IF;
    RETURN new;
END;
$$;

CREATE OR REPLACE FUNCTION check_place_performance_hall()
    RETURNS TRIGGER AS
$$
DECLARE
    performance_hall_id INTEGER;
    place_hall_id       INTEGER;
BEGIN
    -- Получаем hall_id для performance_id
    SELECT hall_id
    INTO performance_hall_id
    FROM performances
    WHERE id = new.performance_id;

    -- Получаем hall_id для place_id
    SELECT hall_id
    INTO place_hall_id
    FROM places
    WHERE id = new.place_id;

    -- Проверяем, совпадают ли hall_id
    IF performance_hall_id IS DISTINCT FROM place_hall_id THEN
        RAISE EXCEPTION 'Place_id % does not belong to the same hall as performance_id %', new.place_id, new.performance_id;
    END IF;

    RETURN new;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trigger_check_place_performance_hall
    BEFORE INSERT
    ON tickets
    FOR EACH ROW
EXECUTE FUNCTION check_place_performance_hall();


CREATE TRIGGER check_duplicate_tickets
    BEFORE INSERT OR UPDATE
    ON tickets
    FOR EACH ROW
EXECUTE PROCEDURE public.check_prevent_duplicate_tickets();

