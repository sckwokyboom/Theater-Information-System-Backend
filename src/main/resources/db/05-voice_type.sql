DO
$$
    BEGIN
        BEGIN
            create type public.voice_type as enum ('Тенор', 'Баритон', 'Бас', 'Контральто', 'Сопрано', 'Альт', 'Меццо-сопрано', 'Контртенор', 'Драматический тенор', 'Лирический тенор', 'Драматический баритон', 'Лирический баритон', 'Драматический бас', 'Легкий баритон', 'Лирический бас', 'Фальцет', 'Драматический контртенор', 'Лирический контртенор');
        EXCEPTION
            WHEN duplicate_object THEN
                RAISE NOTICE 'Type already exists, skipping creation.';
        END;
    END
$$;

alter type public.voice_type owner to postgres;

