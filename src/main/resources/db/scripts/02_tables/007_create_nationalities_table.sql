CREATE TABLE IF NOT EXISTS nationalities
(
    id   SERIAL
        CONSTRAINT nationalities_pk
            PRIMARY KEY,
    name VARCHAR(200) NOT NULL
);

COMMENT ON TABLE nationalities IS 'Национальности.';

COMMENT ON COLUMN nationalities.id IS 'Идентификатор.';

COMMENT ON COLUMN nationalities.name IS 'Название.';
