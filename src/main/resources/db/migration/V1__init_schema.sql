CREATE SCHEMA IF NOT EXISTS app;

SET search_path TO app;

CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    age INTEGER
);

CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f
        FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6
        FOREIGN KEY (role_id) REFERENCES roles (id)
);
