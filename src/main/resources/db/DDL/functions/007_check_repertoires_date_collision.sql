CREATE OR REPLACE FUNCTION check_repertoire_dates() RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
DECLARE
    overlapping_count INTEGER;
BEGIN
    -- Проверяем количество спектаклей с пересекающимися датами
    SELECT COUNT(*)
    INTO overlapping_count
    FROM repertoires_performances rp
             JOIN performances p1 ON rp.performance_id = p1.id
             JOIN performances p2 ON p2.id = new.performance_id
    WHERE rp.repertoire_id = new.repertoire_id
      AND (p1.start_time, p1.end_time) OVERLAPS (p2.start_time, p2.end_time);

    -- Если найдено хотя бы одно пересечение дат, кидаем исключение
    IF overlapping_count > 0 THEN
        RAISE EXCEPTION 'Невозможно добавить спектакль в репертуар. Дата спектакля пересекается с другими спектаклями.';
    END IF;

    -- Если проверка пройдена успешно, возвращаем NEW
    RETURN new;
END;
$$;
