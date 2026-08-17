# 🎌 AniVault

AniVault is a full-stack anime watchlist application built with Spring Boot and Next.js.

It allows users to securely manage their personal anime watchlists, track viewing progress, search and filter anime, and manage their accounts using JWT-based authentication and role-based authorization.

The application follows a layered backend architecture and is deployed with a production MySQL database.

---

## 🚀 Features

### 🔐 Authentication & Security

- User registration
- User login
- JWT-based authentication
- BCrypt password hashing
- Role-based authorization
- Protected API endpoints
- User-specific anime watchlists
- Server-side anime ownership validation
- Stateless authentication using Spring Security
- CORS configuration for frontend/backend communication

### 🎬 Anime Management

- Create anime
- View anime
- View anime by ID
- Update anime
- Delete anime
- Track watched episodes
- Track anime status
- Track watch status
- Anime cover images

### 🔎 Search & Filtering

- Search by anime title
- Filter by studio
- Filter by genre
- Filter by watch status
- Filter by anime status
- Combine multiple filters
- Pagination
- Sorting
- Dynamic filtering using Spring Data JPA Specifications

### 📊 Dashboard

- Anime collection statistics
- Watch progress information
- Watchlist overview
- Recently managed anime

### 🎨 Frontend

- Responsive desktop and mobile interface
- Anime cards
- Dashboard
- Protected application pages
- Login and registration pages
- Loading states
- Empty states
- Error handling
- Responsive layouts for different screen sizes

### 🛠️ Backend

- RESTful API architecture
- DTO pattern
- Entity ↔ DTO mapping using MapStruct
- Input validation
- Global exception handling
- Logging
- Unit testing
- Swagger / OpenAPI documentation

### 🚀 Deployment

- Docker
- Docker Compose
- Railway deployment
- Production MySQL database
- Separate frontend and backend deployment

---

# 🛠️ Tech Stack

## Backend

- Java 25
- Spring Boot 4.1
- Spring Security
- JWT (JJWT)
- Spring Data JPA
- Hibernate
- MySQL 8
- MapStruct
- Lombok
- Maven
- Swagger / OpenAPI
- JUnit 5
- Mockito

## Frontend

- Next.js 16
- React
- TypeScript
- Tailwind CSS
- Axios
- TanStack React Query
- Zustand

## DevOps & Deployment

- Docker
- Docker Compose
- Railway
- Git
- GitHub

---

# 🏗️ Architecture

AniVault follows a layered backend architecture:

```text
                    Next.js Frontend
                           │
                           │ REST API
                           ▼
                    Spring Boot Backend
                           │
             ┌─────────────┼─────────────┐
             │             │             │
             ▼             ▼             ▼
        Controller      Security       Exception
             │          + JWT          Handling
             ▼
          Service
             │
      ┌──────┴──────┐
      │             │
      ▼             ▼
   Mapper      Specification
  MapStruct         │
      │             │
      └──────┬──────┘
             ▼
        Repository
             │
             ▼
        MySQL Database
```

---

# 👤 User-Specific Watchlists

Each user has their own anime watchlist.

```text
User A
 ├── One Piece
 ├── Naruto
 └── Death Note

User B
 ├── Bleach
 ├── Demon Slayer
 └── Solo Leveling
```

Anime ownership is enforced by the backend using the authenticated user from the JWT.

The frontend does not provide a user ID to determine ownership.

The backend identifies the authenticated user and associates anime records with that user.

This prevents users from accessing, modifying, or deleting anime belonging to another user.

---

# 🔐 Authentication Flow

```text
User
 │
 ▼
Login
 │
 ▼
Spring Security
 │
 ▼
AuthenticationManager
 │
 ▼
JWT Generation
 │
 ▼
Frontend stores JWT
 │
 ▼
Axios sends JWT with requests
 │
 ▼
JwtAuthenticationFilter
 │
 ▼
Authenticated User
 │
 ▼
Protected API
```

---

# 📂 Project Structure

```text
AniVault/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── suhas/
│   │   │           └── anivault/
│   │   │               ├── config/
│   │   │               ├── controller/
│   │   │               ├── dto/
│   │   │               ├── entity/
│   │   │               ├── enums/
│   │   │               ├── exception/
│   │   │               ├── mapper/
│   │   │               ├── repository/
│   │   │               ├── security/
│   │   │               │   ├── config/
│   │   │               │   ├── jwt/
│   │   │               │   └── service/
│   │   │               ├── service/
│   │   │               ├── specification/
│   │   │               └── AnivaultApplication.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── suhas/
│                   └── anivault/
│
├── frontend/
│   ├── app/
│   ├── lib/
│   ├── services/
│   ├── store/
│   ├── types/
│   ├── package.json
│   └── next.config.ts
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── anime_db.sql
├── README.md
└── LICENSE
```

---

# 📌 API Endpoints

## 🔐 Authentication

### Register

```http
POST /auth/register
```

### Login

```http
POST /auth/login
```

The login endpoint returns a JWT token that is used to access protected endpoints.

---

## 🎬 Anime

### Get Anime

```http
GET /anime
```

### Get Anime by ID

```http
GET /anime/{id}
```

### Create Anime

```http
POST /anime
```

### Update Anime

```http
PUT /anime/{id}
```

### Delete Anime

```http
DELETE /anime/{id}
```

---

# 🔎 Filtering

### Search by title

```http
GET /anime?title=One Piece
```

### Filter by studio

```http
GET /anime?studio=MAPPA
```

### Filter by genre

```http
GET /anime?genre=Action
```

### Filter by watch status

```http
GET /anime?watchStatus=WATCHING
```

### Filter by anime status

```http
GET /anime?animeStatus=ONGOING
```

### Pagination

```http
GET /anime?page=0&size=5
```

### Sorting

```http
GET /anime?sort=title,asc
```

### Combined query

```http
GET /anime?studio=MAPPA&genre=Action&page=0&size=5&sort=title,asc
```

---

# 📖 API Documentation

Swagger / OpenAPI documentation is available when the backend is running.

Local:

```text
http://localhost:8080/swagger-ui/index.html
```

Production:

```text
https://anivault-production-74a7.up.railway.app/swagger-ui/index.html
```

---

# 🌐 Live Application

Frontend:

```text
https://vivacious-mercy-production-316c.up.railway.app
```

Backend:

```text
https://anivault-production-74a7.up.railway.app
```

---

# ▶️ Running Locally

## Prerequisites

Install the following:

* Java 25
* MySQL 8
* Node.js
* npm
* Docker (optional)

---

## 1. Clone the Repository

```bash
git clone https://github.com/Suhasud/AniVault.git
```

Navigate into the project:

```bash
cd AniVault
```

---

# 🖥️ 2. Run the Backend

Configure the MySQL connection in:

```text
src/main/resources/application.properties
```

Then start the Spring Boot application:

```bash
./mvnw spring-boot:run
```

Backend:

```text
http://localhost:8080
```

Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 🌐 3. Run the Frontend

Open another terminal:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```

Create:

```text
.env.local
```

Add:

```env
NEXT_PUBLIC_API_BASE_URL=http://localhost:8080
```

Start the frontend:

```bash
npm run dev
```

Frontend:

```text
http://localhost:3000
```

---

# 🐳 Running with Docker

From the project root:

```bash
docker compose up --build
```

The backend will be available at:

```text
http://localhost:8080
```

---

# 🗄️ Database

AniVault uses MySQL with Spring Data JPA and Hibernate.

The main relationship between users and anime is:

```text
User
 │
 │ 1
 │
 │ *
 ▼
Anime
```

Each anime record is associated with an authenticated user.

---

# 🧪 Testing

The backend uses:

* JUnit 5
* Mockito

The application has been tested for:

* User registration
* User login
* JWT authentication
* Role-based authorization
* User-specific anime access
* Anime creation
* Anime retrieval
* Anime updates
* Anime deletion
* Search and filtering
* Pagination
* Desktop frontend
* Mobile frontend
* Production API communication

---

# 🚀 Deployment

AniVault is deployed using Railway.

Production architecture:

```text
                    User
                     │
                     ▼
             Railway Frontend
                Next.js
                     │
                     │ REST API
                     ▼
             Railway Backend
               Spring Boot
                     │
                     ▼
              Railway MySQL
```

Docker is used for containerization and deployment configuration.

---

# 🖼️ Anime Images

AniVault stores an image URL for anime cover artwork.

The current application uses externally hosted AniList CDN image URLs for anime artwork.

AniList is used as an image source only; AniVault's core functionality does not depend on the AniList API.

---

# 🔒 Security

AniVault implements:

* Spring Security
* JWT authentication
* BCrypt password hashing
* Stateless sessions
* Role-based authorization
* User-specific data ownership
* Server-side ownership validation
* Input validation
* CORS configuration
* Protected REST endpoints

Ownership checks are performed on the backend rather than relying on data supplied by the frontend.

---

# 📄 License

This project is licensed under the MIT License.

See the [LICENSE](LICENSE) file for details.

---

# 👨‍💻 Author

**Suhas U D**

GitHub:

[https://github.com/Suhasud](https://github.com/Suhasud)