create sequence if not exists perfomances_id_seq
    as integer;

alter sequence perfomances_id_seq owner to postgres;

create table if not exists performances
(
    id           integer default nextval('perfomances_id_seq'::regclass) not null
        constraint perfomances_pk
            primary key,
    play_id      integer                                                 not null
        constraint perfomances_plays_id_fk
            references plays,
    date         date                                                    not null,
    hall_id      integer                                                 not null,
    age_category age_category_type,
    base_price   numeric(10, 2)                                          not null,
    is_premiere  boolean                                                 not null
);

comment on table performances is 'Спектакли.';

comment on column performances.id is 'Идентификатор спектакля.';

comment on column performances.play_id is 'Идентификатор пьесы, по которой поставлен спектакль.';

comment on column performances.date is 'Дата проведения спектакля.';

comment on column performances.hall_id is 'Индекс зала, в котором проводится спектакль. ';

comment on column performances.age_category is 'Возрастна категория спектакля.';

comment on column performances.base_price is 'Базовая цена за билет на спектакль в рублях.';

comment on column performances.is_premiere is 'Является ли данный спектакль премьерой.';

alter table performances
    owner to postgres;


create or replace function public.check_performance_date() returns trigger
    language plpgsql
as
$$
DECLARE
    performance_count INTEGER;
BEGIN
    -- Проверяем количество представлений с такой же датой и залом
    SELECT COUNT(*)
    INTO performance_count
    FROM performances
    WHERE date = NEW.date
      AND hall_id = NEW.hall_id;

    -- Если найдено хотя бы одно представление, кидаем исключение
    IF performance_count > 0 THEN
        RAISE EXCEPTION 'Невозможно добавить представление. Дата представления уже занята для данного зала.';
    END IF;

    -- Если проверка пройдена успешно, возвращаем NEW
    RETURN NEW;
END;
$$;

alter function public.check_performance_date() owner to postgres;


create trigger check_performance_date_trigger
    before insert
    on performances
    for each row
execute procedure public.check_performance_date();



alter sequence perfomances_id_seq owned by performances.id;

