CREATE TABLE jmeniny
(
    id    IDENTITY PRIMARY KEY,
    den   SMALLINT     NOT NULL,
    mesic SMALLINT     NOT NULL,
    jmeno VARCHAR(100) NOT NULL
);

CREATE
    INDEX jmeniny_idx ON jmeniny (mesic, den);
