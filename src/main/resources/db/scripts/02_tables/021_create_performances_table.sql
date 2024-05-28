CREATE SEQUENCE IF NOT EXISTS perfomances_id_seq
    AS INTEGER;

CREATE TABLE IF NOT EXISTS performances
(
    id           INTEGER DEFAULT NEXTVAL('perfomances_id_seq'::REGCLASS) NOT NULL
        CONSTRAINT perfomances_pk
            PRIMARY KEY,
    play_id      INTEGER                                                 NOT NULL
        CONSTRAINT perfomances_plays_id_fk
            REFERENCES plays,
    start_time   TIMESTAMP                                               NOT NULL,
    end_time     TIMESTAMP                                               NOT NULL,
    hall_id      INTEGER                                                 NOT NULL,
    age_category AGE_CATEGORY,
    base_price   NUMERIC(10, 2)                                          NOT NULL,
    is_premiere  BOOLEAN                                                 NOT NULL
);

COMMENT ON TABLE performances IS 'Спектакли.';

COMMENT ON COLUMN performances.id IS 'Идентификатор спектакля.';

COMMENT ON COLUMN performances.play_id IS 'Идентификатор пьесы, по которой поставлен спектакль.';

COMMENT ON COLUMN performances.start_time IS 'Дата и время начала спектакля.';

COMMENT ON COLUMN performances.end_time IS 'Дата и время окончания спектакля.';

COMMENT ON COLUMN performances.hall_id IS 'Индекс зала, в котором проводится спектакль. ';

COMMENT ON COLUMN performances.age_category IS 'Возрастная категория спектакля.';

COMMENT ON COLUMN performances.base_price IS 'Базовая цена за билет на спектакль в рублях.';

COMMENT ON COLUMN performances.is_premiere IS 'Является ли данный спектакль премьерой.';

ALTER SEQUENCE perfomances_id_seq OWNED BY performances.id;