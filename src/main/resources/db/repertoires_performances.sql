CREATE TABLE IF NOT EXISTS repertoires_performances
(
    performance_id INTEGER NOT NULL
        CONSTRAINT repertoires_performances_perfomances_id_fk
            REFERENCES performances,
    repertoire_id  INTEGER NOT NULL
        CONSTRAINT repertoires_performances_repertoires_id_fk
            REFERENCES repertoires,
    CONSTRAINT repertoires_performances_pk
        PRIMARY KEY (performance_id, repertoire_id)
);

COMMENT ON TABLE repertoires_performances IS 'Репертуары и представления.';

COMMENT ON COLUMN repertoires_performances.performance_id IS 'Идентификатор представления.';

COMMENT ON COLUMN repertoires_performances.repertoire_id IS 'Идентификатор репертура.';

CREATE OR REPLACE FUNCTION public.check_repertoire_dates() RETURNS TRIGGER
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
             JOIN performances p2 ON p1.date = p2.date;

    -- Если найдено хотя бы одно пересечение дат, кидаем исключение
    IF overlapping_count > 0 THEN
        RAISE EXCEPTION 'Невозможно добавить спектакль в репертуар. Дата спектакля пересекается с другими спектаклями.';
    END IF;

    -- Если проверка пройдена успешно, возвращаем NEW
    RETURN new;
END;
$$;

ALTER FUNCTION public.check_repertoire_dates() OWNER TO postgres;



CREATE TRIGGER check_repertoire_dates_trigger
    BEFORE INSERT
    ON repertoires_performances
    FOR EACH ROW
EXECUTE PROCEDURE public.check_repertoire_dates();

