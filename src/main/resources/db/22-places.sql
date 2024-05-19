create table if not exists places
(
    id                serial
        constraint places_pk
            primary key,
    hall_id           integer not null
        constraint places___fk
            references halls,
    price_coefficient numeric not null
        constraint price_coefficient_check
            check (price_coefficient > (0)::numeric)
);

comment on table places is 'Места в зале.';

comment on column places.id is 'Идентификатор места в зале.';

comment on column places.hall_id is 'Идентификатор зала.';

comment on column places.price_coefficient is 'Коэффициент цены за место.';


CREATE OR REPLACE FUNCTION check_hall_exists() RETURNS TRIGGER AS $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM halls WHERE id = NEW.hall_id) THEN
        RAISE EXCEPTION 'The hall does not exist';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_check_hall_exists
    BEFORE INSERT ON places
    FOR EACH ROW EXECUTE FUNCTION check_hall_exists();


