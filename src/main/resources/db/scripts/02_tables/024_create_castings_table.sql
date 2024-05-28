CREATE TABLE IF NOT EXISTS castings
(
    actor_id       INTEGER NOT NULL
        CONSTRAINT castings_pk_2
            UNIQUE
        CONSTRAINT castings_actors_id_fk_2
            REFERENCES actors,
    performance_id INTEGER NOT NULL
        CONSTRAINT actors_performances_perfomances_id_fk
            REFERENCES performances,
    double_id      INTEGER
        CONSTRAINT castings_pk_3
            UNIQUE
        CONSTRAINT castings_actors_id_fk
            REFERENCES actors,
    role_id        INTEGER NOT NULL
        CONSTRAINT castings_roles_id_fk
            REFERENCES roles,
    CONSTRAINT castings_pk
        PRIMARY KEY (performance_id, role_id),
    CONSTRAINT actor_and_double_check
        CHECK (actor_id <> double_id)
);

COMMENT ON TABLE castings IS 'Актёры и представления.';

COMMENT ON COLUMN castings.actor_id IS 'Идентификатор актёра.';

COMMENT ON COLUMN castings.performance_id IS 'Идентификатор спектакля, в котором участвует актёр.';

COMMENT ON COLUMN castings.double_id IS 'Идентификатор дублирующего актёра.';

COMMENT ON COLUMN castings.role_id IS 'Роль.';