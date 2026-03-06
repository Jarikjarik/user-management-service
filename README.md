# \# User Management Service

# 

# Backend application for managing users with role-based access control.

# 

# This project demonstrates a typical \*\*Spring Boot layered architecture\*\* with authentication and authorization using \*\*Spring Security\*\*.

# 

# The application provides both \*\*REST API endpoints\*\* and \*\*MVC web pages\*\* for managing users.

# 

# ---

# 

# \## Tech Stack

# 

# \- Java

# \- Spring Boot

# \- Spring Security

# \- Spring MVC

# \- JPA / Hibernate

# \- Maven

# 

# ---

# 

# \## Features

# 

# \- Authentication using Spring Security

# \- Role-based access control (`ADMIN`, `USER`)

# \- CRUD operations for users

# \- REST API endpoints

# \- MVC web interface

# \- DTO layer with entity mapping

# 

# ---

# 

# \## Architecture

# 

# The project follows a classic \*\*layered architecture\*\*:

# 

# 

# Controller → Service → Repository → Database

# 

# 

# \### Package structure

# 

# 

# src/main/java/com/jarik/usermanagement

# 

# config – application and security configuration

# controller – MVC and REST controllers

# dto – request and response objects

# mapper – DTO ↔ Entity mapping

# model – JPA entities

# repository – data access layer

# service – business logic

# 

# 

# ---

# 

# \## Project Structure

# 

# 

# user-management-service

# │

# ├── src

# │ ├── main

# │ │ ├── java/com/jarik/usermanagement

# │ │ │ ├── config

# │ │ │ ├── controller

# │ │ │ ├── dto

# │ │ │ ├── mapper

# │ │ │ ├── model

# │ │ │ ├── repository

# │ │ │ └── service

# │ │ │

# │ │ └── resources

# │ │ ├── application.properties

# │ │ ├── templates

# │ │ └── static

# │ │

# │ └── test

# │

# ├── pom.xml

# ├── mvnw

# ├── mvnw.cmd

# └── README.md

# 

# 

# ---

# 

# \## Running the Application

# 

# Clone the repository:

# 

# ```bash

# git clone https://github.com/Jarikjarik/user-management-service.git

# 

# Move to project folder:

# 

# cd user-management-service

# 

# Run the application:

# 

# Windows

# mvnw.cmd spring-boot:run

# Linux / macOS

# ./mvnw spring-boot:run

# 

# The application will start at:

# 

# http://localhost:8080

# Running Tests

# ./mvnw test

# 

# or on Windows:

# 

# mvnw.cmd test

# Author

# 

# Yaroslav Chekashkin

# 

# GitHub:

# https://github.com/Jarikjarik

