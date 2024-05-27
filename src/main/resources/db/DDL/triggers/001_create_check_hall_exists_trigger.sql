CREATE OR REPLACE TRIGGER check_hall_exists_trigger
    BEFORE INSERT
    ON places
    FOR EACH ROW
EXECUTE PROCEDURE check_hall_exists();


