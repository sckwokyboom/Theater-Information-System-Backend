CREATE OR REPLACE FUNCTION check_hall_exists() RETURNS TRIGGER AS
$$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM halls WHERE id = new.hall_id) THEN
        RAISE EXCEPTION 'The hall does not exist';
    END IF;
    RETURN new;
END;
$$ LANGUAGE plpgsql;