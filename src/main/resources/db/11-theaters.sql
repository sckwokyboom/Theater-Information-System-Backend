CREATE SEQUENCE IF NOT EXISTS theater_id_seq
    AS INTEGER;

ALTER SEQUENCE theater_id_seq OWNER TO postgres;



CREATE TABLE IF NOT EXISTS theaters
(
    name VARCHAR(200)                                        NOT NULL,
    id   INTEGER DEFAULT NEXTVAL('theater_id_seq'::REGCLASS) NOT NULL
        CONSTRAINT id
            PRIMARY KEY
);

COMMENT ON TABLE theaters IS 'Театры.';

COMMENT ON COLUMN theaters.name IS 'Название театра.';

COMMENT ON COLUMN theaters.id IS 'Идентификатор театра.';

ALTER SEQUENCE theater_id_seq OWNED BY theaters.id;