DO
$$
    BEGIN
        BEGIN
            create type public.gender as enum ('Male', 'Female', 'Other');
        EXCEPTION
            WHEN duplicate_object THEN
                RAISE NOTICE 'Type already exists, skipping creation.';
        END;
    END
$$;


alter type public.gender owner to postgres;

