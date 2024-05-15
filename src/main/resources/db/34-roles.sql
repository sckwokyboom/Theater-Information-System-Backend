create table if not exists roles
(
    id             serial
        constraint roles_pk
            primary key,
    name           varchar(300) not null,
    weight         integer,
    height         integer,
    eye_color      varchar(100),
    skin_color     skin_color_type,
    hair_color     hair_color_type,
    voice_type     voice_type,
    gender         gender,
    age            integer
        constraint check_age_not_negative
            check (age >= 0),
    play_id        integer      not null
        constraint roles_plays_id_fk
            references plays,
    description    text,
    nationality_id integer
        constraint roles_nationality_id_fk
            references nationalities
);

comment on table roles is 'Роли в пьесе.';

comment on column roles.id is 'Идентификатор.';

comment on column roles.name is 'Имя персонажа.';

comment on column roles.weight is 'Вес персонажа.';

comment on column roles.height is 'Рост персонажа.';

comment on column roles.eye_color is 'Цвет глаз персонажа.';

comment on column roles.skin_color is 'Цвет кожи персонажа.';

comment on column roles.hair_color is 'Цвет волос персонажа.';

comment on column roles.voice_type is 'Тип голоса персонажа.';

comment on column roles.gender is 'Пол персонажа.';

comment on column roles.age is 'Возраст персонажа.';

comment on constraint check_age_not_negative on roles is 'Проверить, что возраст не меньше нуля.';

comment on column roles.play_id is 'Идентификатор пьесы.';

comment on column roles.nationality_id is 'Идентификатор национальности.';

alter table roles
    owner to postgres;

