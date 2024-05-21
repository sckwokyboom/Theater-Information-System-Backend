CREATE TABLE IF NOT EXISTS plays
(
    id       SERIAL
        CONSTRAINT plays_pk
            PRIMARY KEY,
    title    VARCHAR(200) NOT NULL,
    genre_id INTEGER      NOT NULL
        CONSTRAINT play_genre_id_fk
            REFERENCES genres,
    century  INTEGER      NOT NULL
        CONSTRAINT check_century_is_not_zero
            CHECK (century <> 0)
);

COMMENT ON TABLE plays IS 'Пьесы.';

COMMENT ON COLUMN plays.id IS 'Идентификатор пьесы.';

COMMENT ON COLUMN plays.title IS 'Название пьесы.';

COMMENT ON COLUMN plays.genre_id IS 'Жанр.';

COMMENT ON COLUMN plays.century IS 'Век, в котором пьеса была написана.';

COMMENT ON CONSTRAINT check_century_is_not_zero ON plays IS 'Проверить, что век не является нулевым.';
