CREATE TABLE IF NOT EXISTS actors_titles
(
    actor_id INTEGER NOT NULL
        CONSTRAINT actors_titles_actors_id_fk
            REFERENCES actors,
    title_id INTEGER NOT NULL
        CONSTRAINT actors_titles_titles_id_fk
            REFERENCES titles,
    CONSTRAINT actors_titles_pk
        PRIMARY KEY (actor_id, title_id)
);

COMMENT ON TABLE actors_titles IS 'Актёры и звания.';

COMMENT ON COLUMN actors_titles.actor_id IS 'Идентификатор актёра.';

COMMENT ON COLUMN actors_titles.title_id IS 'Идентификатор звания.';