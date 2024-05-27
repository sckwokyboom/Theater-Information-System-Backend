CREATE OR REPLACE FUNCTION check_performance_and_role_play_id() RETURNS TRIGGER AS
$$
BEGIN
    -- Проверяем, что performance_id и role_id привязаны к одной и той же пьесе (play_id)
    IF NOT EXISTS (SELECT 1
                   FROM performances p
                            JOIN roles r ON p.play_id = r.play_id
                   WHERE p.id = new.performance_id
                     AND r.id = new.role_id) THEN
        RAISE EXCEPTION 'The performance and role are not linked to the same play';
    END IF;
    RETURN new;
END;
$$ LANGUAGE plpgsql;