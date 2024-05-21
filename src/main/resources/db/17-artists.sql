CREATE SEQUENCE IF NOT EXISTS artist_id_seq
    AS INTEGER;

ALTER SEQUENCE artist_id_seq OWNER TO postgres;

CREATE TABLE IF NOT EXISTS artists
(
    id          INTEGER DEFAULT NEXTVAL('artist_id_seq'::REGCLASS) NOT NULL
        CONSTRAINT artist_pk
            PRIMARY KEY,
    employee_id INTEGER                                            NOT NULL
        CONSTRAINT artist_employee_id_fk
            REFERENCES employees
);

COMMENT ON TABLE artists IS 'Артисты.';

COMMENT ON COLUMN artists.id IS 'Идентификатор.';

COMMENT ON COLUMN artists.employee_id IS 'Идентификатор артиста как работника театра.';

ALTER SEQUENCE artist_id_seq OWNED BY artists.id;

