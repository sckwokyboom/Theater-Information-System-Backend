CREATE TABLE IF NOT EXISTS production_directors_performances
(
    production_director_id INTEGER NOT NULL
        CONSTRAINT production_directors_performances_production_directors_id_fk
            REFERENCES production_directors,
    performance_id         INTEGER NOT NULL
        CONSTRAINT production_directors_performances_perfomances_id_fk
            REFERENCES performances,
    CONSTRAINT production_directors_performances_pk
        PRIMARY KEY (performance_id, production_director_id)
);

COMMENT ON TABLE production_directors_performances IS 'Режиссёры-постановщики и представления.';

COMMENT ON COLUMN production_directors_performances.production_director_id IS 'Идентификатор режиссёра-постановщика.';

COMMENT ON COLUMN production_directors_performances.performance_id IS 'Идентификатор представления.';