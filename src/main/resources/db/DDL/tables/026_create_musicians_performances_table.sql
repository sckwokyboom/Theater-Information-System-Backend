CREATE TABLE IF NOT EXISTS musicians_performances
(
    musician_id INTEGER NOT NULL
        CONSTRAINT musicians_performances___fk
            REFERENCES musicians,
    performance INTEGER NOT NULL
        CONSTRAINT musicians_performances_perfomances_id_fk
            REFERENCES performances,
    CONSTRAINT musicians_performances_pk
        PRIMARY KEY (musician_id, performance)
);

COMMENT ON TABLE musicians_performances IS 'Музыканты и представления.';

COMMENT ON COLUMN musicians_performances.musician_id IS 'Идентификатор музыканта.';

COMMENT ON COLUMN musicians_performances.performance IS 'Идентификатор представления.';