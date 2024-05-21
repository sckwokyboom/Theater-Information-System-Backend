CREATE TABLE IF NOT EXISTS repertoires
(
    id              SERIAL
        CONSTRAINT repertoires_pk
            PRIMARY KEY,
    start_of_season DATE NOT NULL,
    end_of_season   DATE NOT NULL,
    CONSTRAINT season_date_check
        CHECK (start_of_season < end_of_season)
);

COMMENT ON TABLE repertoires IS 'Репертуары театра.';

COMMENT ON COLUMN repertoires.id IS 'Идентификатор репертуара.';

COMMENT ON COLUMN repertoires.start_of_season IS 'Дата начала сезона (включительно).';

COMMENT ON COLUMN repertoires.end_of_season IS 'Дата конца сезона (включительно).';