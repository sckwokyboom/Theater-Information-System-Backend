CREATE OR REPLACE TRIGGER check_repertoire_dates_trigger
    BEFORE INSERT
    ON repertoires_performances
    FOR EACH ROW
EXECUTE PROCEDURE check_repertoire_dates();
