CREATE TABLE IF NOT EXISTS titles
(
    id             SERIAL
        CONSTRAINT titles_pk
            PRIMARY KEY,
    competition_id INTEGER      NOT NULL
        CONSTRAINT titles_competitions_id_fk
            REFERENCES competitions,
    name           VARCHAR(200) NOT NULL
);

COMMENT ON TABLE titles IS 'Звания актёров.';

COMMENT ON COLUMN titles.id IS 'Идентификатор звания.';

COMMENT ON COLUMN titles.competition_id IS 'Идентификатор соревнования, за которое было получено звание.';

COMMENT ON COLUMN titles.name IS 'Название звания.';