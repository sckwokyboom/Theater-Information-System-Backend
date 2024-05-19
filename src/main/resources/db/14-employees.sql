create sequence if not exists employee_id_seq
    as integer;

alter sequence employee_id_seq owner to postgres;

create sequence if not exists employee_theater_id_seq
    as integer;

alter sequence employee_theater_id_seq owner to postgres;

create table if not exists employees
(
    id                 integer default nextval('employee_id_seq'::regclass)         not null
        constraint employee_pk
            primary key,
    theater_id         integer default nextval('employee_theater_id_seq'::regclass) not null
        constraint employee_theater_id_fk
            references theaters,
    first_name         varchar(100)                                                 not null,
    second_name        varchar(100)                                                 not null,
    patronymic        varchar(100)                                                 not null,
    date_of_birth      date                                                         not null,
    gender             gender                                                       not null,
    salary             integer                                                      not null
        constraint check_salary
            check (salary >= 0),
    amount_of_children integer                                                      not null
        constraint check_amount_of_children
            check (amount_of_children >= 0),
    date_of_employment date                                                         not null,
    constraint check_date_of_employment_after_date_of_birth
        check (date_of_birth < date_of_employment)
);



comment on table employees is 'Работники.';

comment on column employees.id is 'Идентификатор работника.';

comment on column employees.theater_id is 'Идентификатор театра, в котором трудоустроен работник.';

comment on column employees.first_name is 'Имя.';

comment on column employees.second_name is 'Фамилия.';

comment on column employees.patronymic is 'Отчество.';

comment on column employees.date_of_birth is 'Дата рождения.';

comment on column employees.gender is 'Пол.';

comment on column employees.salary is 'Ежемесячная зарплата.';

comment on column employees.amount_of_children is 'Количество детей.';

comment on column employees.date_of_employment is 'Дата трудоустройства в театр.';

comment on constraint check_date_of_employment_after_date_of_birth on employees is 'Проверить, что дата трудоустройства произошла после даты рождения.';

alter table employees
    owner to postgres;

alter sequence employee_theater_id_seq owned by employees.theater_id;

alter sequence employee_id_seq owned by employees.id;

