DO
$$
    BEGIN
        BEGIN
            CREATE TYPE public.AGE_CATEGORY_TYPE AS ENUM ('0', '6', '12', '16', '18');
        EXCEPTION
            WHEN duplicate_object THEN
                RAISE NOTICE 'Type already exists, skipping creation.';
        END;
    END
$$;