CREATE TABLE IF NOT EXISTS roles
(
    id             SERIAL
        CONSTRAINT roles_pk
            PRIMARY KEY,
    name           VARCHAR(300) NOT NULL,
    weight         INTEGER,
    height         INTEGER,
    eye_color      VARCHAR(100),
    skin_color     SKIN_COLOR_TYPE,
    hair_color     HAIR_COLOR_TYPE,
    voice_type     VOICE_TYPE,
    gender         GENDER,
    age            INTEGER
        CONSTRAINT check_age_not_negative
            CHECK (age >= 0),
    play_id        INTEGER      NOT NULL
        CONSTRAINT roles_plays_id_fk
            REFERENCES plays,
    description    TEXT,
    nationality_id INTEGER
        CONSTRAINT roles_nationality_id_fk
            REFERENCES nationalities
);

COMMENT ON TABLE roles IS 'Роли в пьесе.';

COMMENT ON COLUMN roles.id IS 'Идентификатор.';

COMMENT ON COLUMN roles.name IS 'Имя персонажа.';

COMMENT ON COLUMN roles.weight IS 'Вес персонажа.';

COMMENT ON COLUMN roles.height IS 'Рост персонажа.';

COMMENT ON COLUMN roles.eye_color IS 'Цвет глаз персонажа.';

COMMENT ON COLUMN roles.skin_color IS 'Цвет кожи персонажа.';

COMMENT ON COLUMN roles.hair_color IS 'Цвет волос персонажа.';

COMMENT ON COLUMN roles.voice_type IS 'Тип голоса персонажа.';

COMMENT ON COLUMN roles.gender IS 'Пол персонажа.';

COMMENT ON COLUMN roles.age IS 'Возраст персонажа.';

COMMENT ON CONSTRAINT check_age_not_negative ON roles IS 'Проверить, что возраст не меньше нуля.';

COMMENT ON COLUMN roles.play_id IS 'Идентификатор пьесы.';

COMMENT ON COLUMN roles.nationality_id IS 'Идентификатор национальности.';