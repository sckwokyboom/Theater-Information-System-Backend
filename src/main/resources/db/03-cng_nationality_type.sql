DO
$$
    BEGIN
        BEGIN
            CREATE TYPE public.cng_nationality_type as enum ('РУССКИЙ', 'УКРАИНЕЦ', 'БЕЛОРУСС', 'КАЗАХ', 'УЗБЕК', 'ТАДЖИК', 'КЫРГЫЗ', 'ТУРКМЕН', 'АРМЯНИН', 'АЗЕРБАЙДЖАНЕЦ', 'МОЛДАВАНИН', 'ГРУЗИН', 'ТУВИНЕЦ', 'ЧУВАШ', 'БАШКИР', 'ТАТАРИН', 'КАЛМЫК', 'КОМИ', 'МАРИЙЦЫ', 'БУРЯТ', 'АБХАЗ', 'ОСЕТИН', 'АРЦАХЦЫ', 'ПРИДНЕСТРОВЕЦ', 'КАРАЧАЕВЕЦ', 'ХАКАС');
        EXCEPTION
            WHEN duplicate_object THEN
                RAISE NOTICE 'Type already exists, skipping creation.';
        END;
    END
$$;

alter type public.cng_nationality_type owner to postgres;

