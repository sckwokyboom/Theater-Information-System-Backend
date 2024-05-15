DO
$$
    BEGIN
        BEGIN
            create type public.skin_color_type as enum ('ОЧЕНЬ СВЕТЛЫЙ', 'СВЕТЛЫЙ', 'СРЕДНИЙ', 'ТЕМНЫЙ', 'ОЧЕНЬ ТЕМНЫЙ', 'ЧЕРНЫЙ');
        EXCEPTION
            WHEN duplicate_object THEN
                RAISE NOTICE 'Type already exists, skipping creation.';
        END;
    END
$$;

alter type public.skin_color_type owner to postgres;

