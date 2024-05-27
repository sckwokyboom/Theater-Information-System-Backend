CREATE OR REPLACE TRIGGER check_play_century_trigger
    BEFORE INSERT OR UPDATE
    ON plays
    FOR EACH ROW
EXECUTE PROCEDURE check_play_century();