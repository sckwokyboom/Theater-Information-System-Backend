CREATE OR REPLACE TRIGGER check_performance_date_trigger
    BEFORE INSERT
    ON performances
    FOR EACH ROW
EXECUTE PROCEDURE check_performance_date();


