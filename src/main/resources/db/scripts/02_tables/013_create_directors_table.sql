CREATE TABLE IF NOT EXISTS directors
(
    id        SERIAL
        CONSTRAINT directors_pk
            PRIMARY KEY,
    artist_id INTEGER NOT NULL
        CONSTRAINT directors_artist_id_fk
            REFERENCES artists
);

COMMENT ON TABLE directors IS 'Директоры.';

COMMENT ON COLUMN directors.id IS 'Идентификатор.';

COMMENT ON COLUMN directors.artist_id IS 'Идентификатор директора как артиста.';