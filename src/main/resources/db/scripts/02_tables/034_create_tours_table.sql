CREATE TABLE IF NOT EXISTS tours
(
    id                      SERIAL
        CONSTRAINT tours_pk
            PRIMARY KEY,
    play_id                 INTEGER NOT NULL
        CONSTRAINT tours_plays_id_fk
            REFERENCES plays,
    date_of_start           DATE    NOT NULL,
    date_of_end             DATE    NOT NULL,
    tour_theater_id         INTEGER NOT NULL
        CONSTRAINT tours_theater_id_fk
            REFERENCES theaters,
    organization_theater_id INTEGER NOT NULL
        CONSTRAINT tours_theater_id_fk_2
            REFERENCES theaters,
    CONSTRAINT check_theaters_not_equals
        CHECK (tour_theater_id <> organization_theater_id),
    CONSTRAINT check_date_of_start_before_date_of_end
        CHECK (date_of_start <= date_of_end)
);

COMMENT ON TABLE tours IS 'Гастрольные туры.';

COMMENT ON COLUMN tours.id IS 'Индекс гастрольного тура.';

COMMENT ON COLUMN tours.play_id IS 'Пьеса, с которой совершается гастрольный тур.';

COMMENT ON COLUMN tours.date_of_start IS 'Дата начала гастрольного тура (включительно).';

COMMENT ON COLUMN tours.date_of_end IS 'Дата конца гастрольного тура (включительно).';

COMMENT ON COLUMN tours.tour_theater_id IS 'Театр, в который совершается гастрольный тур.';

COMMENT ON COLUMN tours.organization_theater_id IS 'Театр, от которого производится гастрольный тур.';

COMMENT ON CONSTRAINT check_theaters_not_equals ON tours IS 'Проверить, что театр-организатор гастрольного тура не совпадает с театром, в который этот гастрольный тур совершается.';

COMMENT ON CONSTRAINT check_date_of_start_before_date_of_end ON tours IS 'Проверить, что дата начала гастрольного тура раньше конца гастрольного тура (или совпадает с ней).';
