CREATE TABLE IF NOT EXISTS authors_plays
(
    author_id INTEGER NOT NULL
        CONSTRAINT authors_plays_authors_id_fk
            REFERENCES authors,
    play_id   INTEGER NOT NULL
        CONSTRAINT authors_plays_plays_id_fk
            REFERENCES plays,
    CONSTRAINT authors_plays_pk
        PRIMARY KEY (play_id, author_id)
);

COMMENT ON TABLE authors_plays IS 'Авторы и пьесы.';

COMMENT ON COLUMN authors_plays.author_id IS 'Идентификатор автора.';

COMMENT ON COLUMN authors_plays.play_id IS 'Идентификатор пьесы.';