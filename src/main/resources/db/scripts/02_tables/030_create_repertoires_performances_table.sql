CREATE TABLE IF NOT EXISTS repertoires_performances
(
    performance_id INTEGER NOT NULL
        CONSTRAINT repertoires_performances_perfomances_id_fk
            REFERENCES performances,
    repertoire_id  INTEGER NOT NULL
        CONSTRAINT repertoires_performances_repertoires_id_fk
            REFERENCES repertoires,
    CONSTRAINT repertoires_performances_pk
        PRIMARY KEY (performance_id, repertoire_id)
);

COMMENT ON TABLE repertoires_performances IS 'Репертуары и представления.';

COMMENT ON COLUMN repertoires_performances.performance_id IS 'Идентификатор представления.';

COMMENT ON COLUMN repertoires_performances.repertoire_id IS 'Идентификатор репертуара.';
