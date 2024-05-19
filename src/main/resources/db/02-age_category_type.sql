DO
$$
    BEGIN
        BEGIN
            CREATE TYPE public.age_category_type AS ENUM ('0', '6', '12', '16', '18');
        EXCEPTION
            WHEN duplicate_object THEN
                RAISE NOTICE 'Type already exists, skipping creation.';
        END;
    END
$$;

alter type public.age_category_type owner to postgres;

