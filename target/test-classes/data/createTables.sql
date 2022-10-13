CREATE TABLE IF NOT EXISTS films (
    id       CHAR(36)       NOT NULL,
    name     VARCHAR(255)   NOT NULL,
    duration INT            NOT NULL,
    score    DECIMAL(3, 2)  NOT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS evaluations (
    id       CHAR(36)       NOT NULL,
    score    NUMERIC(2)  NOT NULL,
    film_id  CHAR(36)       NOT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;