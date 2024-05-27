CREATE OR REPLACE TRIGGER check_premiere_trigger
    BEFORE INSERT OR UPDATE
    ON performances
    FOR EACH ROW
EXECUTE FUNCTION check_premiere();