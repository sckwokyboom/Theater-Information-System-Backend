CREATE OR REPLACE FUNCTION check_premiere() RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
DECLARE
    earliest_start_time TIMESTAMP;
BEGIN
    -- Получаем самое раннее время начала спектакля для данного play_id
    SELECT MIN(start_time)
    INTO earliest_start_time
    FROM performances
    WHERE play_id = new.play_id;

    -- Если текущий спектакль не самый ранний, проверяем флаг is_premiere
    IF earliest_start_time IS NOT NULL AND new.start_time > earliest_start_time AND new.is_premiere THEN
        RAISE EXCEPTION 'Этот спектакль не может быть премьерой, так как есть более ранние спектакли с этим play_id.';
    END IF;

    -- Если это не самый ранний спектакль, но is_premiere установлено в TRUE, также выдаем ошибку
    IF earliest_start_time IS NOT NULL AND new.start_time > earliest_start_time AND new.is_premiere THEN
        RAISE EXCEPTION 'Этот спектакль не может быть премьерой, так как есть более ранние спектакли с этим play_id.';
    END IF;

    -- Если это самый ранний спектакль, но is_premiere не установлено в TRUE, выдаем предупреждение
    IF earliest_start_time IS NULL OR new.start_time <= earliest_start_time THEN
        new.is_premiere := TRUE;
    END IF;

    RETURN new;
END;
$$;
