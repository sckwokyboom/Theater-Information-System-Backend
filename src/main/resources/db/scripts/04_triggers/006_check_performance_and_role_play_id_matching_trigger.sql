CREATE OR REPLACE TRIGGER trg_check_performance_and_role_play_id
    BEFORE INSERT
    ON castings
    FOR EACH ROW
EXECUTE PROCEDURE check_performance_and_role_play_id();
