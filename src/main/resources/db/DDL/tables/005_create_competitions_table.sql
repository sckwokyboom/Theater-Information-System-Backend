CREATE TABLE IF NOT EXISTS competitions
(
    id            SERIAL
        CONSTRAINT competitions_pk
            PRIMARY KEY,
    name          VARCHAR(200) NOT NULL,
    date_of_start DATE         NOT NULL,
    date_of_end   DATE         NOT NULL,
    CONSTRAINT check_date_of_start_before_date_of_end
        CHECK (date_of_start < date_of_end)
);

COMMENT ON TABLE competitions IS 'Соревнования.';

COMMENT ON COLUMN competitions.id IS 'Идентификатор.';

COMMENT ON COLUMN competitions.name IS 'Название.';

COMMENT ON COLUMN competitions.date_of_start IS 'Дата начала (включительно).';

COMMENT ON COLUMN competitions.date_of_end IS 'Дата завершения (включительно).';

COMMENT ON CONSTRAINT check_date_of_start_before_date_of_end ON competitions IS 'Проверить, что дата начала соревнования раньше, чем его завершение.';