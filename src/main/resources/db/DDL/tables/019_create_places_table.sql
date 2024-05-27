CREATE TABLE IF NOT EXISTS places
(
    id                SERIAL
        CONSTRAINT places_pk
            PRIMARY KEY,
    hall_id           INTEGER NOT NULL
        CONSTRAINT places___fk
            REFERENCES halls,
    price_coefficient NUMERIC NOT NULL
        CONSTRAINT price_coefficient_check
            CHECK (price_coefficient > (0)::NUMERIC)
);

COMMENT ON TABLE places IS 'Места в зале.';

COMMENT ON COLUMN places.id IS 'Идентификатор места в зале.';

COMMENT ON COLUMN places.hall_id IS 'Идентификатор зала.';

COMMENT ON COLUMN places.price_coefficient IS 'Коэффициент цены за место.';