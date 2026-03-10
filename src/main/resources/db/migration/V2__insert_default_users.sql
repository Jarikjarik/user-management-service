SET search_path TO app;

INSERT INTO roles (id, name)
VALUES (1, 'ROLE_USER')
ON CONFLICT (id) DO UPDATE SET name = EXCLUDED.name;

INSERT INTO roles (id, name)
VALUES (2, 'ROLE_ADMIN')
ON CONFLICT (id) DO UPDATE SET name = EXCLUDED.name;

INSERT INTO users (id, username, password, name, last_name, email, age)
VALUES (
           1,
           'user',
           '$2a$10$jvDgY4cJ0iqw8gXduysnoOja7MKM6YliA5oQYnxNJgEIg0Vw4Siee',
           'UserName',
           'UserLast',
           'user@mail.com',
           35
       )
ON CONFLICT (id) DO UPDATE SET
                               username = EXCLUDED.username,
                               password = EXCLUDED.password,
                               name = EXCLUDED.name,
                               last_name = EXCLUDED.last_name,
                               email = EXCLUDED.email,
                               age = EXCLUDED.age;

INSERT INTO users (id, username, password, name, last_name, email, age)
VALUES (
           2,
           'admin',
           '$2a$10$gpHnRyU9.RYlkDCBKQIocOklviPrtdSHFjtEaDx8oX1cM8K6QLZ7O',
           'AdminName',
           'AdminLast',
           'admin@mail.com',
           30
       )
ON CONFLICT (id) DO UPDATE SET
                               username = EXCLUDED.username,
                               password = EXCLUDED.password,
                               name = EXCLUDED.name,
                               last_name = EXCLUDED.last_name,
                               email = EXCLUDED.email,
                               age = EXCLUDED.age;

INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1)
ON CONFLICT DO NOTHING;

INSERT INTO user_roles (user_id, role_id)
VALUES (2, 1)
ON CONFLICT DO NOTHING;

INSERT INTO user_roles (user_id, role_id)
VALUES (2, 2)
ON CONFLICT DO NOTHING;

SELECT setval(
    pg_get_serial_sequence('app.roles', 'id'),
    COALESCE((SELECT MAX(id) FROM roles), 1),
    true
);

SELECT setval(
    pg_get_serial_sequence('app.users', 'id'),
    COALESCE((SELECT MAX(id) FROM users), 1),
    true
);
