CREATE TABLE IF NOT EXISTS musicians
(
    id        SERIAL
        CONSTRAINT musicians_pk
            PRIMARY KEY,
    artist_id INTEGER NOT NULL
        CONSTRAINT musicians_artist_id_fk
            REFERENCES artists
);

COMMENT ON TABLE musicians IS 'Музыканты.';

COMMENT ON COLUMN musicians.id IS 'Идентификатор музыканта.';

COMMENT ON COLUMN musicians.artist_id IS 'Идентификатор музыканта как артиста.';