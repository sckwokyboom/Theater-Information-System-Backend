CREATE TABLE IF NOT EXISTS subscriptions
(
    id    SERIAL
        CONSTRAINT subscriptions_pk
            PRIMARY KEY,
    price NUMERIC NOT NULL
        CONSTRAINT price_check
            CHECK (price >= (0)::NUMERIC)
);

COMMENT ON TABLE subscriptions IS 'Абонемент с билетами.';

COMMENT ON COLUMN subscriptions.id IS 'Идентификатор подписки.';

COMMENT ON COLUMN subscriptions.price IS 'Цена подписки.';