CREATE OR REPLACE TRIGGER trigger_check_place_performance_hall
    BEFORE INSERT
    ON tickets
    FOR EACH ROW
EXECUTE PROCEDURE check_place_performance_hall();