-- CREATE OR REPLACE FUNCTION create_subscription_with_tickets(
--     sub_price NUMERIC,
--     ticket_data JSONB
-- ) RETURNS VOID AS
-- $$
-- DECLARE
--     new_subscription_id INT;
-- BEGIN
--     -- Начинаем транзакцию
--     BEGIN
--         ;
--
--         -- Создаем новую подписку
--         INSERT INTO subscriptions (price)
--         VALUES (sub_price)
--         RETURNING id INTO new_subscription_id;
--
--         -- Обходим данные билетов и вставляем их
--         FOR ticket IN SELECT * FROM JSONB_ARRAY_ELEMENTS(ticket_data)
--             LOOP
--                 INSERT INTO tickets (performance_id, price, place_id, subscription_id, sale_date)
--                 VALUES ((ticket ->> 'performanceId')::INT,
--                         (ticket ->> 'price')::NUMERIC,
--                         (ticket ->> 'placeId')::INT,
--                         new_subscription_id,
--                         (ticket ->> 'saleDate')::DATE);
--             END LOOP;
--
--         -- Фиксируем транзакцию
--         COMMIT;
--     EXCEPTION
--         WHEN OTHERS THEN
--             -- Откатываем транзакцию в случае ошибки
--             ROLLBACK;
--             RAISE;
--     END;
-- $$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION create_subscription_with_tickets(
    _performance_ids INTEGER[],
    _place_ids INTEGER[]
) RETURNS VOID AS
$$
DECLARE
    _subscription_id INTEGER;
    i                INTEGER;
    total_price      NUMERIC := 0;
    _prices          NUMERIC[];
BEGIN
    -- Начало транзакции
    BEGIN
        -- Проверка на пересечение спектаклей
        FOR i IN 1 .. ARRAY_LENGTH(_performance_ids, 1)
            LOOP
                PERFORM 1
                FROM performances p1,
                     performances p2
                WHERE p1.id = _performance_ids[i]
                  AND p1.id != p2.id
                  AND p1.start_time <= p2.end_time
                  AND p1.end_time >= p2.start_time;
                IF found THEN
                    RAISE EXCEPTION 'Спектакли пересекаются по времени';
                END IF;
            END LOOP;

        _prices := ARRAY(SELECT p.base_price * pl.price_coefficient
                         FROM UNNEST(_performance_ids) WITH ORDINALITY AS perf(performance_id, idx)
                                  JOIN UNNEST(_place_ids) WITH ORDINALITY AS plc(place_id, idx2)
                                       ON idx = idx2
                                  JOIN performances p ON p.id = perf.performance_id
                                  JOIN places pl ON pl.id = plc.place_id);

        -- Рассчитать общую стоимость
        FOR i IN 1 .. ARRAY_LENGTH(_prices, 1)
            LOOP
                total_price := total_price + _prices[i];
            END LOOP;

        -- Создать запись в subscriptions
        INSERT INTO subscriptions (price) VALUES (total_price) RETURNING id INTO _subscription_id;


        -- Создать записи в tickets
        FOR i IN 1 .. ARRAY_LENGTH(_performance_ids, 1)
            LOOP
                INSERT INTO tickets (performance_id, price, place_id, subscription_id, sale_date)
                VALUES (_performance_ids[i], _prices[i], _place_ids[i], _subscription_id, CURRENT_DATE);
            END LOOP;

        -- Коммит транзакции
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            -- В случае ошибки, откатываем транзакцию
            ROLLBACK;
            RAISE;
    END;
END;
$$ LANGUAGE plpgsql;
