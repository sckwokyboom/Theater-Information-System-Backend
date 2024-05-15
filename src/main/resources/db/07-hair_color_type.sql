DO
$$
    BEGIN
        BEGIN
            create type public.hair_color_type as enum ('ЧЕРНЫЙ', 'КОРИЧНЕВЫЙ', 'СВЕТЛО-КОРИЧНЕВЫЙ', 'РУСЫЙ', 'РЫЖИЙ', 'БЛОНДИН', 'СЕДОЙ', 'БЕЛЫЙ', 'СЕРЫЙ', 'ПЕПЕЛЬНЫЙ', 'ПЕСОЧНЫЙ', 'МЕДНЫЙ', 'ШАТЕН', 'КАШТАНОВЫЙ', 'МЕЛИРОВАННЫЙ', 'ОКРАШЕННЫЙ В ЧЁРНЫЙ', 'ОКРАШЕННЫЙ В КРАСНЫЙ', 'ОКРАШЕННЫЙ В СИНИЙ', 'ОКРАШЕННЫЙ В ЗЕЛЁНЫЙ', 'ОКРАШЕННЫЙ В ФИОЛЕТОВЫЙ', 'ОКРАШЕННЫЙ В РОЗОВЫЙ');
        EXCEPTION
            WHEN duplicate_object THEN
                RAISE NOTICE 'Type already exists, skipping creation.';
        END;
    END
$$;

alter type public.hair_color_type owner to postgres;

