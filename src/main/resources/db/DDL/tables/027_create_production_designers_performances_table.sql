CREATE TABLE IF NOT EXISTS production_designers_performances
(
    production_designer_id INTEGER NOT NULL
        CONSTRAINT production_designers_performances_production_designers_id_fk
            REFERENCES production_designers,
    performance_id         INTEGER NOT NULL
        CONSTRAINT production_designers_performances_perfomances_id_fk
            REFERENCES performances,
    CONSTRAINT production_designers_performances_pk
        PRIMARY KEY (performance_id, production_designer_id)
);

COMMENT ON TABLE production_designers_performances IS 'Художники-постановщики и спектакли.';

COMMENT ON COLUMN production_designers_performances.production_designer_id IS 'Идентификатор художника-постановщика.';

COMMENT ON COLUMN production_designers_performances.performance_id IS 'Идентификатор спектакля.';