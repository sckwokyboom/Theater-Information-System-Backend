DO $$
DECLARE
    hall_record RECORD;
BEGIN
    -- Проходим по каждой записи из таблицы залов
    FOR hall_record IN SELECT id, number_of_seats FROM halls LOOP
        -- Для каждого зала генерируем указанное количество мест и добавляем их в таблицу places
        FOR _ IN 1..hall_record.number_of_seats LOOP
            INSERT INTO places (hall_id, price_coefficient)
            VALUES (hall_record.id, 1.0);
        END LOOP;
    END LOOP;
END $$;