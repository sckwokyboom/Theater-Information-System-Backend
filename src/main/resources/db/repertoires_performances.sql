create table if not exists repertoires_performances
(
    performance_id integer not null
        constraint repertoires_performances_perfomances_id_fk
            references performances,
    repertoire_id  integer not null
        constraint repertoires_performances_repertoires_id_fk
            references repertoires,
    constraint repertoires_performances_pk
        primary key (performance_id, repertoire_id)
);

comment on table repertoires_performances is 'Репертуары и представления.';

comment on column repertoires_performances.performance_id is 'Идентификатор представления.';

comment on column repertoires_performances.repertoire_id is 'Идентификатор репертура.';

alter table repertoires_performances
    owner to postgres;

create or replace function public.check_repertoire_dates() returns trigger
    language plpgsql
as
$$
DECLARE
    overlapping_count INTEGER;
BEGIN
    -- Проверяем количество спектаклей с пересекающимися датами
    SELECT COUNT(*)
    INTO overlapping_count
    FROM repertoires_performances rp
             JOIN performances p1 ON rp.performance_id = p1.id
             JOIN performances p2 ON p1.date = p2.date;

    -- Если найдено хотя бы одно пересечение дат, кидаем исключение
    IF overlapping_count > 0 THEN
        RAISE EXCEPTION 'Невозможно добавить спектакль в репертуар. Дата спектакля пересекается с другими спектаклями.';
    END IF;

    -- Если проверка пройдена успешно, возвращаем NEW
    RETURN NEW;
END;
$$;

alter function public.check_repertoire_dates() owner to postgres;



create trigger check_repertoire_dates_trigger
    before insert
    on repertoires_performances
    for each row
execute procedure public.check_repertoire_dates();

