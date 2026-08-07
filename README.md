# 🎌 AniVault

AniVault is a production-style Spring Boot REST API for managing an anime watchlist. It provides secure CRUD operations, advanced search capabilities, JWT-based authentication, role-based authorization, and comprehensive API documentation. The project follows industry-standard backend architecture and best practices.

---

# 🚀 Features

- ✅ CRUD Operations
- ✅ DTO Pattern
- ✅ Entity ↔ DTO Mapping using MapStruct
- ✅ Input Validation
- ✅ Global Exception Handling
- ✅ Pagination
- ✅ Sorting
- ✅ Dynamic Filtering using Spring Data JPA Specifications
- ✅ Logging
- ✅ Unit Testing
- ✅ Spring Security
- ✅ JWT Authentication
- ✅ Role-Based Authorization (Admin/User)
- ✅ Swagger / OpenAPI Documentation
- ✅ Docker
- ✅ Docker Compose

---

# 🛠️ Tech Stack

- Java 25
- Spring Boot 4.1
- Spring Security
- JWT (jjwt)
- Spring Data JPA
- Hibernate
- MySQL 8
- MapStruct
- Lombok
- Maven
- Swagger / OpenAPI
- Docker
- Docker Compose
- JUnit 5
- Mockito

---

# 🏗️ Architecture

AniVault follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
MySQL Database
```

Supporting layers:

- DTO
- Mapper (MapStruct)
- Specification
- Security
- Exception Handling
- Logging
- Configuration

---

# 📂 Project Structure

```text
src
├── main
│   ├── java
│   │   └── com
│   │       └── suhas
│   │           └── anivault
│   │               ├── config
│   │               ├── controller
│   │               ├── dto
│   │               ├── entity
│   │               ├── enums
│   │               ├── exception
│   │               ├── mapper
│   │               ├── repository
│   │               ├── security
│   │               │   ├── config
│   │               │   ├── jwt
│   │               │   └── service
│   │               ├── service
│   │               ├── specification
│   │               └── AnivaultApplication
│   └── resources
│       ├── application.properties
│       ├── static
│       └── templates
│
└── test
    └── java
        └── com
            └── suhas
                └── anivault
                    ├── controller
                    ├── service
                    └── AnivaultApplicationTests
```

---

# 📌 API Features

## 🔐 Authentication

- Register User
- Login
- JWT Token Generation
- Protected APIs using JWT
- Role-Based Authorization

## 🎬 Anime Management

- Create Anime
- Get Anime by ID
- Update Anime
- Delete Anime

## 🔍 Dynamic Filtering

Examples:

```http
GET /anime?title=One Piece
```

```http
GET /anime?studio=MAPPA
```

```http
GET /anime?genre=Action
```

```http
GET /anime?watchStatus=WATCHING
```

```http
GET /anime?animeStatus=ONGOING
```

## 📄 Pagination

```http
GET /anime?page=0&size=5
```

## ↕️ Sorting

```http
GET /anime?sort=title,asc
```

## 🔎 Combined Query

```http
GET /anime?studio=MAPPA&genre=Action&page=0&size=5&sort=title,asc
```

---

# 📖 API Documentation

Swagger UI is available after starting the application:

```text
http://localhost:8080/swagger-ui.html
```

---

# ▶️ Running the Project

## Clone the repository

```bash
git clone https://github.com/Suhasud/AniVault.git
```

## Navigate to the project

```bash
cd AniVault
```

---

## Option 1: Run with Docker (Recommended)

```bash
docker compose up --build
```

Application:

```text
http://localhost:8080
```

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

---

## Option 2: Run Locally

### Configure MySQL

Update the database configuration in:

```text
src/main/resources/application.properties
```

### Start the application

```bash
./mvnw spring-boot:run
```

---

# ✅ Completed Features

- CRUD APIs
- DTO Pattern
- Validation
- Global Exception Handling
- Pagination
- Sorting
- Dynamic Filtering (JPA Specifications)
- Logging
- Unit Testing
- Spring Security
- JWT Authentication
- Role-Based Authorization
- Swagger / OpenAPI
- Docker
- Docker Compose

---

# 👨‍💻 Author

**Suhas U D**

GitHub: https://github.com/Suhasud

---

# 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
