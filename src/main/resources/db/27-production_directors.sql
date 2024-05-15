create table if not exists production_directors
(
    id          serial
        constraint production_directors_pk
            primary key,
    director_id integer not null
        constraint production_directors_directors_id_fk
            references directors
);

comment on table production_directors is 'Режиссёры-постановщики.';

comment on column production_directors.id is 'Идентификатор режиссёра-постановщика.';

comment on column production_directors.director_id is 'Идентификатор режиссёра-постановщика как директора.';

alter table production_directors
    owner to postgres;

