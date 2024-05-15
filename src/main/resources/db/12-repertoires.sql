create table if not exists repertoires
(
    id              serial
        constraint repertoires_pk
            primary key,
    start_of_season date not null,
    end_of_season   date not null,
    constraint season_date_check
        check (start_of_season < end_of_season)
);

comment on table repertoires is 'Репертуары театра.';

comment on column repertoires.id is 'Идентификатор репертуара.';

comment on column repertoires.start_of_season is 'Дата начала сезона (включительно).';

comment on column repertoires.end_of_season is 'Дата конца сезона (включительно).';

alter table repertoires
    owner to postgres;

