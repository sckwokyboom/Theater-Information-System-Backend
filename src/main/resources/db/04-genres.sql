CREATE TABLE IF NOT EXISTS genres
(
    id
        SERIAL
        CONSTRAINT
            genres_pk
            PRIMARY
                KEY,
    title
        VARCHAR(100) NOT NULL
);

COMMENT ON TABLE genres IS 'Жанры представлений.';

COMMENT ON COLUMN genres.id IS 'Идентификатор.';

COMMENT ON COLUMN genres.title IS 'Название.';