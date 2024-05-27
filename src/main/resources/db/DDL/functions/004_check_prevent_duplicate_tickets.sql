CREATE OR REPLACE FUNCTION check_prevent_duplicate_tickets() RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
DECLARE
    overlapping_count INTEGER;
BEGIN
    SELECT COUNT(*)
    INTO overlapping_count
    FROM tickets
    WHERE performance_id = new.performance_id
      AND place_id = new.place_id
      AND id != new.id;
    IF overlapping_count > 0 THEN
        RAISE EXCEPTION 'Нельзя купить несколько билетов на одно и то же место одного и того же спектакля.';
    END IF;
    RETURN new;
END;
$$;