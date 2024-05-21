CREATE TABLE IF NOT EXISTS production_directors
(
    id          SERIAL
        CONSTRAINT production_directors_pk
            PRIMARY KEY,
    director_id INTEGER NOT NULL
        CONSTRAINT production_directors_directors_id_fk
            REFERENCES directors
);

COMMENT ON TABLE production_directors IS 'Режиссёры-постановщики.';

COMMENT ON COLUMN production_directors.id IS 'Идентификатор режиссёра-постановщика.';

COMMENT ON COLUMN production_directors.director_id IS 'Идентификатор режиссёра-постановщика как директора.';