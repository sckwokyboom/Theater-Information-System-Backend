CREATE TABLE IF NOT EXISTS halls
(
    id              SERIAL
        CONSTRAINT halls_pk
            PRIMARY KEY,
    title           VARCHAR(100) NOT NULL,
    number_of_seats INTEGER      NOT NULL
        CONSTRAINT number_of_seats_check
            CHECK (number_of_seats > 0)
);

COMMENT ON TABLE halls IS 'Залы в театре.';

COMMENT ON COLUMN halls.id IS 'Идентификатор зала.';

COMMENT ON COLUMN halls.number_of_seats IS 'Количество мест в зале.';