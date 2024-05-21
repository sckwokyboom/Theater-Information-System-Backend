CREATE TABLE IF NOT EXISTS stage_conductors_performances
(
    stage_conductor_id INTEGER NOT NULL
        CONSTRAINT stage_conductors_performances_stage_conductors_id_fk
            REFERENCES stage_conductors,
    performance_id     INTEGER NOT NULL
        CONSTRAINT stage_conductors_performances_perfomances_id_fk
            REFERENCES performances,
    CONSTRAINT stage_conductors_performances_pk
        PRIMARY KEY (stage_conductor_id, performance_id)
);

COMMENT ON TABLE stage_conductors_performances IS 'Дирижёры и спектакли.';

COMMENT ON COLUMN stage_conductors_performances.stage_conductor_id IS 'Идентификатор дирижёра.';

COMMENT ON COLUMN stage_conductors_performances.performance_id IS 'Идентификатор спектакля.';