CREATE TABLE IF NOT EXISTS actors
(
    id             SERIAL
        CONSTRAINT actors_pk
            PRIMARY KEY,
    artist_id      INTEGER         NOT NULL
        CONSTRAINT actors_artist_id_fk
            REFERENCES artists,
    voice          VOICE_TYPE      NOT NULL,
    weight         INTEGER         NOT NULL
        CONSTRAINT weight_check
            CHECK (weight > 0),
    height         INTEGER         NOT NULL
        CONSTRAINT height_check
            CHECK (height > 0),
    hair_color     VARCHAR         NOT NULL,
    eye_color      VARCHAR         NOT NULL,
    skin_color     SKIN_COLOR_TYPE NOT NULL,
    nationality_id INTEGER         NOT NULL
        CONSTRAINT actors_nationality_id_fk
            REFERENCES nationalities
);

COMMENT ON TABLE actors IS 'Актёры.';

COMMENT ON COLUMN actors.id IS 'Идентификатор.';

COMMENT ON COLUMN actors.artist_id IS 'Идентификатор актёра как артиста.';

COMMENT ON COLUMN actors.voice IS 'Тип голоса.';

COMMENT ON COLUMN actors.weight IS 'Вес в кг.';

COMMENT ON COLUMN actors.height IS 'Рост в см.';

COMMENT ON COLUMN actors.hair_color IS 'Цвет волос.';

COMMENT ON COLUMN actors.eye_color IS 'Цвет глаз.';

COMMENT ON COLUMN actors.skin_color IS 'Цвет кожи.';

COMMENT ON COLUMN actors.nationality_id IS 'Идентификатор национальности.';
