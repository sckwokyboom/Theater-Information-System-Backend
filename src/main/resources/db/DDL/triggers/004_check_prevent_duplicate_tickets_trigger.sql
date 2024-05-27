CREATE OR REPLACE TRIGGER check_duplicate_tickets_trigger
    BEFORE INSERT OR UPDATE
    ON tickets
    FOR EACH ROW
EXECUTE PROCEDURE check_prevent_duplicate_tickets();

