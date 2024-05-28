DO
$$
    BEGIN
        BEGIN
            CREATE TYPE public.GENDER AS ENUM ('Male', 'Female', 'Other');
        EXCEPTION
            WHEN duplicate_object THEN
                RAISE NOTICE 'Type already exists, skipping creation.';
        END;
    END
$$;