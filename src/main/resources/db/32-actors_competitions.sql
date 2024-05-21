CREATE TABLE IF NOT EXISTS actors_competitions
(
    actor_id       INTEGER NOT NULL
        CONSTRAINT actors_competitions_actors_id_fk
            REFERENCES actors,
    competition_id INTEGER NOT NULL
        CONSTRAINT actors_competitions_competitions_id_fk
            REFERENCES competitions,
    CONSTRAINT actors_competitions_pk
        PRIMARY KEY (competition_id, actor_id)
);

COMMENT ON TABLE actors_competitions IS 'Актёры и соревнования.';

COMMENT ON COLUMN actors_competitions.actor_id IS 'Идентификатор актёра.';

COMMENT ON COLUMN actors_competitions.competition_id IS 'Идентификатор соревнования, в котором участвует актёр.';