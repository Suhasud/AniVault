# 🎌 AniVault

AniVault is a production-style Spring Boot REST API for managing an anime watchlist. It allows users to manage anime entries, track watch progress, and perform advanced searching with filtering, pagination, and sorting.

---

## 🚀 Features

### ✅ Core Features

- Create, Read, Update and Delete (CRUD) anime
- Input Validation
- Global Exception Handling
- DTO Pattern
- Entity ↔ DTO Mapping using MapStruct

### ✅ Advanced Features

- Pagination
- Sorting
- Dynamic Filtering using Spring Data JPA Specifications
- OpenAPI / Swagger Documentation

---

## 🛠️ Tech Stack

- Java 25
- Spring Boot 4.1
- Spring Data JPA
- Hibernate
- MySQL
- MapStruct
- Lombok
- Maven
- Swagger / OpenAPI
- Spring Security
- JWT (jjwt)

---

## 📂 Project Structure

```
src
├── controller
├── service
├── repository
├── entity
├── dto
├── mapper
├── specification
├── exception
├── enums
└── config
```

---

## 📌 API Features

### CRUD

- Create Anime
- Get Anime by ID
- Update Anime
- Delete Anime

### Dynamic Filtering

Example:

```
GET /anime?title=One piece
```

```
GET /anime?studio=MAPPA
```

```
GET /anime?genre=Action
```

```
GET /anime?watchStatus=WATCHING
```

```
GET /anime?animeStatus=ONGOING
```

### Pagination

```
GET /anime?page=0&size=5
```

### Sorting

```
GET /anime?sort=title,asc
```

### Combined Query

```
GET /anime?studio=MAPPA&genre=Action&page=0&size=5&sort=title,asc
```

---

## 📖 API Documentation

Swagger UI is available after starting the application:

```
http://localhost:8080/swagger-ui.html
```

---

## ▶️ Running the Project

### Clone the repository

```bash
git clone https://github.com/Suhasud/AniVault.git
```

### Navigate to the project

```bash
cd AniVault
```

### Configure MySQL

Update the database configuration in:

```
src/main/resources/application.properties
```

### Run

```bash
./mvnw spring-boot:run
```

---

## 📅 Roadmap

### ✅ Completed

- CRUD
- Validation
- Exception Handling
- DTO
- MapStruct
- Pagination
- Sorting
- Swagger / OpenAPI
- JPA Specifications

### 🚧 Upcoming

- Logging
- Unit Testing
- Spring Security
- JWT Authentication
- Role-Based Authorization
- Docker
- Deployment

---

## 👨‍💻 Author

**Suhas U D**

GitHub: https://github.com/Suhasud
