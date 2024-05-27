CREATE OR REPLACE FUNCTION check_performance_date() RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
DECLARE
    performance_count INTEGER;
BEGIN
    -- Проверяем количество представлений с такой же датой и залом
    SELECT COUNT(*)
    INTO performance_count
    FROM performances
    WHERE ((new.start_time, new.end_time) OVERLAPS (start_time, end_time))
      AND hall_id = new.hall_id;

    -- Если найдено хотя бы одно представление, кидаем исключение
    IF performance_count > 0 THEN
        RAISE EXCEPTION 'Невозможно добавить представление. Дата представления уже занята для данного зала.';
    END IF;

    -- Если проверка пройдена успешно, возвращаем NEW
    RETURN new;
END;
$$;