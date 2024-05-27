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