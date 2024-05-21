CREATE TABLE IF NOT EXISTS production_designers
(
    id          SERIAL
        CONSTRAINT production_designers_pk
            PRIMARY KEY,
    director_id INTEGER NOT NULL
        CONSTRAINT production_designers_directors_id_fk
            REFERENCES directors
);

COMMENT ON TABLE production_designers IS 'Художники-постановщики.';

COMMENT ON COLUMN production_designers.id IS 'Идентификатор художника-постановщика.';

COMMENT ON COLUMN production_designers.director_id IS 'Идентификатор художника-постановщика как директора.';
