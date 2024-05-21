CREATE TABLE IF NOT EXISTS artists_tours
(
    artist_id INTEGER NOT NULL
        CONSTRAINT artists_tours_artist_id_fk
            REFERENCES artists,
    tour_id   INTEGER NOT NULL
        CONSTRAINT artists_tours_tours_id_fk
            REFERENCES tours,
    CONSTRAINT artists_tours_pk
        PRIMARY KEY (tour_id, artist_id)
);

COMMENT ON TABLE artists_tours IS 'Артисты и гастрольные туры.';

COMMENT ON COLUMN artists_tours.artist_id IS 'Идентификатор артиста.';

COMMENT ON COLUMN artists_tours.tour_id IS 'Идентификатор гастрольного тура.';