# Library Management System API

This is a RESTful API for a Library Management System built using **Spring Boot** with **Spring Security** for Basic Authentication.

## Features

- **CRUD Operations** for managing books
    - `GET` /api/books
    - `POST` /api/books
    - `PUT` /api/books/{id}
    - `DELETE` /api/books/{id}
- **Basic Authentication** to secure API endpoints
- **Swagger UI** for API documentation
- **H2 Database** for data persistence

## Technologies Used

- Spring Boot
- Spring Security
- H2 Database (in-memory)
- Swagger (for API documentation)
- Bean Validation (JSR-380)

## Prerequisites

Make sure you have the following tools installed:

- JDK 17
- Maven (for building the project)

## Build the project
mvn clean install

## Run the Application
mvn spring-boot:run

The application should start at http://localhost:8080.

## Basic Authentication
Username: test@123
Password: password@123

## Endpoints

- GET /api/books
- Retrieve all books in the system.

- POST /api/books
- Create a new book entry.
Request Body:
{
"title": "Book Title",
"author": "Author Name",
"publicationYear": 2021
}
- PUT /api/books/{id}
- Update a book entry by ID.

- DELETE /api/books/{id}
- Delete a book entry by ID.

## Security
- The API is secured using Basic Authentication. All endpoints require a valid username and password.

- Basic Auth Credentials:
- Username: test@123
- Password: password@123
