# User Management Service

Spring Boot application for user management with role-based access control, MVC pages, and REST endpoints.

## Overview

The project combines three concerns in one service:
- authentication and authorization with Spring Security
- web UI for admin and user flows
- REST API for user and role management

Current database setup is based on PostgreSQL and Flyway. The schema is created by migrations, while Hibernate runs in validation mode.

## Tech Stack

- Java 17+
- Spring Boot 2.6
- Spring Security
- Spring MVC + Thymeleaf
- Spring Data JPA
- PostgreSQL
- Flyway
- OpenAPI / Swagger UI
- Maven

## Architecture

Layered flow:

`Controller -> Service -> Repository -> PostgreSQL`

Package layout:

```text
src/main/java/com/jarik/usermanagement
|-- config
|-- controller
|   `-- api
|-- dto
|   `-- api
|-- mapper
|-- model
|-- repository
`-- service
```

## Features

- form-based login at `/login`
- role-based access control for `ADMIN` and `USER`
- admin panel for user management
- REST API for CRUD operations on users
- endpoint for current authenticated user
- automatic PostgreSQL schema migration through Flyway
- Swagger UI via Springdoc

## Endpoints

MVC:
- `GET /login`
- `GET /user`
- `GET /admin`

REST:
- `GET /api/roles`
- `GET /api/users`
- `GET /api/users/{id}`
- `POST /api/users`
- `PUT /api/users/{id}`
- `DELETE /api/users/{id}`
- `GET /api/user/me`

## Project Structure

```text
user-management-service/
|-- src/
|   |-- main/
|   |   |-- java/com/jarik/usermanagement/
|   |   `-- resources/
|   |       |-- application.properties
|   |       |-- db/migration/
|   |       |-- static/js/
|   |       `-- templates/
|   `-- test/
|-- workflows/ci.yml
|-- docker-compose.yml
|-- pom.xml
|-- mvnw
|-- mvnw.cmd
`-- README.md
```

## Database and Migrations

Flyway migration files:
- `V1__init_schema.sql` creates schema and tables
- `V2__insert_default_users.sql` inserts default roles and users

Hibernate does not create tables automatically. It only validates the mapping against the migrated schema.

## Default Accounts

- `admin / admin`
- `user / user`

## Running with Local PostgreSQL

Application datasource is configured in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/user_management
spring.datasource.username=app_user
spring.datasource.password=12345
```

Create the database if needed:

```sql
CREATE DATABASE user_management;
```

Run the application:

```bash
./mvnw spring-boot:run
```

Windows:

```bash
mvnw.cmd spring-boot:run
```

Application URL:
- [http://localhost:8080](http://localhost:8080)

Swagger UI:
- [http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui)

## Running with Docker Compose

Start PostgreSQL:

```bash
docker compose up -d
```

If you already started PostgreSQL with old credentials, recreate the volume once:

```bash
docker compose down -v
docker compose up -d
```

The container uses the same credentials as the application:
- database: `user_management`
- username: `app_user`
- password: `12345`

## Tests

```bash
./mvnw test
```

Windows:

```bash
mvnw.cmd test
```

## Author

Yaroslav Chekashkin  
GitHub: [@Jarikjarik](https://github.com/Jarikjarik)
