CREATE OR REPLACE FUNCTION check_play_century() RETURNS TRIGGER AS
$$
DECLARE
    author_century INTEGER;
BEGIN
    -- Получаем век автора
    SELECT EXTRACT(CENTURY FROM authors.date_of_birth)
    INTO author_century
    FROM authors
    WHERE id = (SELECT author_id FROM authors_plays WHERE play_id = new.id LIMIT 1);

    -- Проверяем, что век пьесы совпадает с веком автора
    IF new.century != author_century THEN
        RAISE EXCEPTION 'Век пьесы (%) не совпадает с веком автора (%)', new.century, author_century;
    END IF;

    RETURN new;
END;
$$ LANGUAGE plpgsql;
