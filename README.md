# User Management Service

Backend service for user management with role-based access control, implemented with **Spring Boot**, **Spring Security**, **Spring MVC**, and **REST API**.

## Overview

This project demonstrates a classic layered architecture:

`Controller -> Service -> Repository -> Database`

The application includes:
- MVC pages for admin/user workflows
- REST API for user and role management
- Authentication and authorization with role checks

## Tech Stack

- Java
- Spring Boot
- Spring Security
- Spring MVC + Thymeleaf
- Spring Data JPA (Hibernate)
- MySQL
- Maven

## Features

- Form-based authentication (`/login`)
- Role-based access control (`ADMIN`, `USER`)
- User CRUD operations
- DTO-based API contracts
- MVC + REST in one application

## Access Model

- `ADMIN`:
  - `/admin/**`
  - `/user`
  - all authenticated API endpoints
- `USER`:
  - `/user`
  - `/api/user/me`

Public routes:
- `/`
- `/login`

## API Endpoints

### Admin API

Base path: `/api`

- `GET /api/roles` - list available roles
- `GET /api/users` - list all users
- `GET /api/users/{id}` - get user by id
- `POST /api/users` - create user
- `PUT /api/users/{id}` - update user
- `DELETE /api/users/{id}` - delete user

### User API

Base path: `/api/user`

- `GET /api/user/me` - get current authenticated user

## MVC Endpoints

- `GET /login` - login page
- `GET /user` - user profile page (`USER` or `ADMIN`)
- `GET /admin` - admin panel (`ADMIN`)

## Project Structure

```text
user-management-service/
├── src/
│   ├── main/
│   │   ├── java/com/jarik/usermanagement/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   │   └── api/
│   │   │   ├── dto/
│   │   │   │   └── api/
│   │   │   ├── mapper/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   └── service/
│   │   └── resources/
│   │       ├── static/js/
│   │       ├── templates/
│   │       ├── application.properties
│   │       └── data.sql
│   └── test/
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## Quick Start

### 1. Clone and enter the project

```bash
git clone https://github.com/Jarikjarik/user-management-service.git
cd user-management-service
```

### 2. Configure database

Default datasource in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/user_crud_2
spring.datasource.username=root
spring.datasource.password=dbrootdb
```

Create the database before starting the app:

```sql
CREATE DATABASE user_crud_2;
```

### 3. Run the app

Windows:

```bash
mvnw.cmd spring-boot:run
```

Linux/macOS:

```bash
./mvnw spring-boot:run
```

Application URL:
- [http://localhost:8080](http://localhost:8080)

### 4. Run tests

Windows:

```bash
mvnw.cmd test
```

Linux/macOS:

```bash
./mvnw test
```

## Seed Data

Initial users are loaded from `data.sql` on startup.

Default accounts:
- `admin` (roles: `ADMIN`, `USER`)
- `user` (role: `USER`)

> Passwords are stored in BCrypt hash form in `data.sql`.

## Author

Yaroslav Chekashkin  
GitHub: [@Jarikjarik](https://github.com/Jarikjarik)
