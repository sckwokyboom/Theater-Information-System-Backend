CREATE SEQUENCE IF NOT EXISTS employee_id_seq
    AS INTEGER;

ALTER SEQUENCE employee_id_seq OWNER TO postgres;

CREATE SEQUENCE IF NOT EXISTS employee_theater_id_seq
    AS INTEGER;

ALTER SEQUENCE employee_theater_id_seq OWNER TO postgres;

CREATE TABLE IF NOT EXISTS employees
(
    id                 INTEGER DEFAULT NEXTVAL('employee_id_seq'::REGCLASS)         NOT NULL
        CONSTRAINT employee_pk
            PRIMARY KEY,
    theater_id         INTEGER DEFAULT NEXTVAL('employee_theater_id_seq'::REGCLASS) NOT NULL
        CONSTRAINT employee_theater_id_fk
            REFERENCES theaters,
    first_name         VARCHAR(100)                                                 NOT NULL,
    second_name        VARCHAR(100)                                                 NOT NULL,
    patronymic         VARCHAR(100)                                                 NOT NULL,
    date_of_birth      DATE                                                         NOT NULL,
    gender             GENDER                                                       NOT NULL,
    salary             INTEGER                                                      NOT NULL
        CONSTRAINT check_salary
            CHECK (salary >= 0),
    amount_of_children INTEGER                                                      NOT NULL
        CONSTRAINT check_amount_of_children
            CHECK (amount_of_children >= 0),
    date_of_employment DATE                                                         NOT NULL,
    CONSTRAINT check_date_of_employment_after_date_of_birth
        CHECK (date_of_birth < date_of_employment)
);



COMMENT ON TABLE employees IS 'Работники.';

COMMENT ON COLUMN employees.id IS 'Идентификатор работника.';

COMMENT ON COLUMN employees.theater_id IS 'Идентификатор театра, в котором трудоустроен работник.';

COMMENT ON COLUMN employees.first_name IS 'Имя.';

COMMENT ON COLUMN employees.second_name IS 'Фамилия.';

COMMENT ON COLUMN employees.patronymic IS 'Отчество.';

COMMENT ON COLUMN employees.date_of_birth IS 'Дата рождения.';

COMMENT ON COLUMN employees.gender IS 'Пол.';

COMMENT ON COLUMN employees.salary IS 'Ежемесячная зарплата.';

COMMENT ON COLUMN employees.amount_of_children IS 'Количество детей.';

COMMENT ON COLUMN employees.date_of_employment IS 'Дата трудоустройства в театр.';

COMMENT ON CONSTRAINT check_date_of_employment_after_date_of_birth ON employees IS 'Проверить, что дата трудоустройства произошла после даты рождения.';

ALTER SEQUENCE employee_theater_id_seq OWNED BY employees.theater_id;

ALTER SEQUENCE employee_id_seq OWNED BY employees.id;

