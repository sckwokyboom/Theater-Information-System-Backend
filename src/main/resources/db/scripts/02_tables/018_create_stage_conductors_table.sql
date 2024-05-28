CREATE TABLE IF NOT EXISTS stage_conductors
(
    id          SERIAL
        CONSTRAINT stage_conductors_pk
            PRIMARY KEY,
    director_id INTEGER NOT NULL
        CONSTRAINT stage_conductors_directors_id_fk
            REFERENCES directors
);

COMMENT ON TABLE stage_conductors IS 'Дирижёры.';

COMMENT ON COLUMN stage_conductors.id IS 'Идентификатор дирижёра.';

COMMENT ON COLUMN stage_conductors.director_id IS 'Идентификатор дирижёра как директора.';