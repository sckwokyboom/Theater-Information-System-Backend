DO
$$
    BEGIN
        BEGIN
            CREATE TYPE public.SKIN_COLOR AS ENUM ('ОЧЕНЬ СВЕТЛЫЙ', 'СВЕТЛЫЙ', 'СРЕДНИЙ', 'ТЕМНЫЙ', 'ОЧЕНЬ ТЕМНЫЙ', 'ЧЕРНЫЙ');
        EXCEPTION
            WHEN duplicate_object THEN
                RAISE NOTICE 'Type already exists, skipping creation.';
        END;
    END
$$;