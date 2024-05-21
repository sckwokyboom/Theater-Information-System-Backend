CREATE SEQUENCE IF NOT EXISTS manager_id_seq
    AS INTEGER;

ALTER SEQUENCE manager_id_seq OWNER TO postgres;

CREATE TABLE IF NOT EXISTS managers
(
    id          INTEGER DEFAULT NEXTVAL('manager_id_seq'::REGCLASS) NOT NULL
        CONSTRAINT manager_pk
            PRIMARY KEY,
    employee_id INTEGER                                             NOT NULL
        CONSTRAINT manager_employee_id_fk
            REFERENCES employees
);

COMMENT ON TABLE managers IS 'Менеджеры.';

COMMENT ON COLUMN managers.id IS 'Идентификатор менеджера.';

COMMENT ON COLUMN managers.employee_id IS 'Идентификатор менеджера как работника театра.';

ALTER SEQUENCE manager_id_seq OWNED BY managers.id;

