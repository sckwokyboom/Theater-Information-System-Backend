CREATE TABLE IF NOT EXISTS authors
(
    id                   SERIAL
        CONSTRAINT authors_pk
            PRIMARY KEY,
    first_name           VARCHAR(100) NOT NULL,
    second_name          VARCHAR(100),
    date_of_birth        DATE,
    date_of_death        DATE,
    country_of_origin_id INTEGER
        CONSTRAINT authors_countries_id_fk
            REFERENCES countries,
    patronymic           VARCHAR(200),
    CONSTRAINT check_birth_before_death
        CHECK (((date_of_birth IS NULL) AND (date_of_death IS NULL)) OR
               ((date_of_birth IS NOT NULL) AND (date_of_death IS NULL)) OR
               ((date_of_birth IS NOT NULL) AND (date_of_death IS NOT NULL) AND (date_of_birth < date_of_death)))
);

COMMENT ON TABLE authors IS 'Авторы.';

COMMENT ON COLUMN authors.id IS 'Идентификатор.';

COMMENT ON COLUMN authors.first_name IS 'Имя.';

COMMENT ON COLUMN authors.second_name IS 'Фамилия.';

COMMENT ON COLUMN authors.date_of_birth IS 'Дата рождения.';

COMMENT ON COLUMN authors.date_of_death IS 'Дата смерти (если автор умер).';

COMMENT ON COLUMN authors.country_of_origin_id IS 'Идентификатор страны происхождения.';

COMMENT ON COLUMN authors.patronymic IS 'Отчество.';

COMMENT ON CONSTRAINT check_birth_before_death ON authors IS 'Проверка, что дата рождения предшествует дате смерти (в случае, если автор умер).';