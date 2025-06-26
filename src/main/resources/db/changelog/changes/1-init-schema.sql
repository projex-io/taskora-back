CREATE TABLE IF NOT EXISTS tickets(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR (255) NOT NULL,
    description TEXT,
    status VARCHAR (50) NOT NULL
);