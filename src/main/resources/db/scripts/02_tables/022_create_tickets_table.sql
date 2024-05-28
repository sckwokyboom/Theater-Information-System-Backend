CREATE TABLE IF NOT EXISTS tickets
(
    id              SERIAL
        CONSTRAINT tickets_pk
            PRIMARY KEY,
    performance_id  INTEGER NOT NULL
        CONSTRAINT tickets_perfomances_id_fk
            REFERENCES performances,
    price           NUMERIC NOT NULL
        CONSTRAINT price_check
            CHECK (price >= (0)::NUMERIC),
    place_id        INTEGER NOT NULL
        CONSTRAINT tickets_places_id_fk
            REFERENCES places,
    subscription_id INTEGER
        CONSTRAINT tickets_subscriptions_id_fk
            REFERENCES subscriptions,
    sale_date       DATE    NOT NULL
);

COMMENT ON TABLE tickets IS 'Билеты на спектакли';

COMMENT ON COLUMN tickets.id IS 'Идентификатор билета.';

COMMENT ON COLUMN tickets.performance_id IS 'Идентификатор спектакля.';

COMMENT ON COLUMN tickets.price IS 'Цена билета в рублях.';

COMMENT ON COLUMN tickets.place_id IS 'Идентификатор места в зале.';

COMMENT ON COLUMN tickets.subscription_id IS 'Идентификатор подписки (если билет входит в подписку).';