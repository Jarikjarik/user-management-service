-- Роли
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

-- Пользователи
INSERT INTO users (id, username, password, name, last_name, email, age)
VALUES (1, 'user',  '$2a$10$jvDgY4cJ0iqw8gXduysnoOja7MKM6YliA5oQYnxNJgEIg0Vw4Siee',
        'UserName', 'UserLast', 'user@mail.com', 35);

INSERT INTO users (id, username, password, name, last_name, email, age)
VALUES (2, 'admin', '$2a$10$gpHnRyU9.RYlkDCBKQIocOklviPrtdSHFjtEaDx8oX1cM8K6QLZ7O',
        'AdminName', 'AdminLast', 'admin@mail.com', 30);

-- Связи user_roles
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);
