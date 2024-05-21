CREATE TABLE IF NOT EXISTS countries
(
    id   SERIAL
        CONSTRAINT countries_pk
            PRIMARY KEY,
    name VARCHAR(200) NOT NULL
);

COMMENT ON COLUMN countries.id IS 'Идентификатор.';

COMMENT ON COLUMN countries.name IS 'Название страны.';