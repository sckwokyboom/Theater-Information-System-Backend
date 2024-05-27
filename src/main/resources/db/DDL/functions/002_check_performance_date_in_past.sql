CREATE OR REPLACE FUNCTION check_performance_date() RETURNS TRIGGER AS
$$
BEGIN
    IF new.start_time < CURRENT_TIMESTAMP THEN
        RAISE EXCEPTION 'The performance date cannot be in the past';
    END IF;
    RETURN new;
END;
$$ LANGUAGE plpgsql;