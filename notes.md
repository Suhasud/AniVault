@Entity
- Marks the class as an entity.
- Creates a table in the database.

@Table
- Optional.
- Used to specify table name.

@Id
- Primary key.

@GeneratedValue
- Auto-generates the id.

GenerationType.IDENTITY
- Uses MySQL AUTO_INCREMENT.

@ElementCollection
- Used for Set<String>.
- Creates another table because SQL cannot store a Set in one column.

@Enumerated(EnumType.STRING)
- Stores enum names instead of numbers.

@CollectionTable(name = "anime_genres") &
@Column(name = "anime") may require in future

# AniVault Learning Notes

# Version

Current Version : v0.1

---

# Project Philosophy

- Build from basic to professional.
- Learn one concept at a time.
- Refactor later instead of overengineering now.
- Build features only when they are needed.
- Use AI as a mentor, not as a code generator.

---

# Entity Design

Anime

Fields

- id
- title
- genres
- studio
- episodes
- watchedEpisodes
- animeStatus
- watchStatus

---

# Why Long instead of long?

Long can be null.

Before saving:

id = null

After saving:

id = 1

JPA generates the id automatically.

---

# @GeneratedValue(strategy = GenerationType.IDENTITY)

Uses MySQL AUTO_INCREMENT.

Database generates the id.

---

# Why separate AnimeStatus and WatchStatus?

AnimeStatus

- ONGOING
- COMPLETED
- UPCOMING

WatchStatus

- PLANNING
- WATCHING
- COMPLETED
- DROPPED

Anime status belongs to the anime.

Watch status belongs to the user.

---

# Why Set<String> instead of List<String>?

Genres should not contain duplicates.

Example

GOOD

Action
Drama

BAD

Action
Action
Drama

Set automatically removes duplicates.

---

# @ElementCollection

Used when an entity contains a collection of basic types.

Example

Set<String>
List<String>

JPA creates another table to store the collection.

---

# @Enumerated(EnumType.STRING)

Stores

ONGOING

instead of

0

Reason

Changing enum order later will not corrupt data.

---

# Constructors

No Argument Constructor

Required by JPA.

Parameterized Constructor

Useful while creating objects.

Do not include id because database generates it.

---

# Repository

Repository should be an interface.

Reason

Spring Data JPA generates the implementation automatically.

Example

extends JpaRepository<Anime, Long>

Second Generic

Long = Primary Key datatype.

---

# Service

For v0.1

Use class.

Later

Refactor into

AnimeService (Interface)

AnimeServiceImpl (Class)

Reason

Understand the need first before using interfaces.

---

# Project Structure

controller

service

repository

entity

enums

exception

---

# Current Roadmap

✅ Spring Boot Project

✅ Packages

✅ Enums

✅ Entity

✅ Repository

⬜ Service

⬜ Controller

⬜ MySQL Connection

⬜ CRUD

⬜ Postman Testing

---

# Personal Rules

Before using any annotation ask

Why?

When?

What problem does it solve?

Never memorize.

Understand first.

# Things I'll Forget

## Why Long instead of long?

Long can be null.

JPA uses null before persisting.

After save, database assigns id.

Repository generic must match id datatype.

---

## @ElementCollection

Use only for collection of primitive/basic types.

Examples

Set<String>

List<String>

NOT for another Entity.

JPA creates another table automatically.

If another object has its own identity,
use relationships instead.

---

## @Enumerated(EnumType.STRING)

Never use ORDINAL.

Reason

Changing enum order changes stored numbers.

STRING stores actual enum name.

Safe.

---

## Set vs List

List

- duplicates allowed
- ordered

Set

- duplicates not allowed

Genres -> Set

Reason

Anime cannot have duplicate genres.

---

## Repository

Repository MUST be interface.

Spring generates implementation automatically.

No need to implement CRUD methods.

---

## Service

Current

Class

Future

Interface

Implementation

Reason

Refactor when project becomes bigger.

Don't overengineer v0.1.

---

## Constructors

No Args Constructor

Required by JPA.

Parameterized Constructor

Don't include generated id.

---

## Question Bank

Why final?

Dependency Injection?

Why interfaces?

Bean Lifecycle?

@ManyToMany?

@OneToMany?

Cascade?

Lazy vs Eager?

DTO?

PATCH vs PUT?

Validation?

JWT?

# AniVault Development Journal

# v0.1

---

## Step 1

Created Spring Boot Project

Configuration

- Maven
- Java
- Spring Boot 4.1.0
- JDK 25
- Jar
- application.properties

Dependencies

- Spring Web
- Spring Data JPA
- MySQL Driver

Reason

Keep only required dependencies.
Others will be added later.

---

## Step 2

Created Package Structure

controller

service

repository

entity

enums

exception

Reason

Organized project from beginning.

---

## Step 3

Designed Anime Entity

Instead of coding directly,
designed the entity first.

Fields decided

- id
- title
- genres
- studio
- episodes
- watchedEpisodes
- animeStatus
- watchStatus

---

## Step 4

Separated Status

Initially

status

Changed to

AnimeStatus

WatchStatus

Reason

Anime Status

ONGOING
COMPLETED
UPCOMING

Watch Status

PLANNING
WATCHING
COMPLETED
DROPPED

These represent different things.

---

## Step 5

Genres

Initially

Thought about List.

Later changed to Set.

Reason

Genres should not contain duplicates.

---

## Step 6

Episodes

Data Type

int

Reason

Whole numbers.

---

## Step 7

Entity Creation

Learned

@Entity

@Table

@Id

@GeneratedValue

GenerationType.IDENTITY

@ElementCollection

@Enumerated(EnumType.STRING)

Created constructors.

Generated getters and setters.

---

## Step 8

Repository

Created

AnimeRepository

extends JpaRepository<Anime, Long>

Reason

Spring provides CRUD implementation automatically.

---

## Step 9

Service Design Discussion

Initially discussed

Interface

Realized

No need for interface in v0.1.

Decision

AnimeService -> Class

Later

AnimeService Interface

AnimeServiceImpl Class

Reason

Refactor after understanding interfaces.

---

## Learning Philosophy

Don't build professional directly.

Start basic.

Improve every version.

Refactor old code whenever new concepts are learned.

---

## Version Plan

v0.1

CRUD

v0.2

Pagination

Sorting

v0.3

Validation

DTO

v0.4

Authentication

v0.5

React

v1.0

Production Ready

---

Current Progress

✅ Spring Boot Project

✅ Package Structure

✅ Enums

✅ Entity

✅ Repository

⬜ Service

⬜ Controller

⬜ MySQL

⬜ CRUD

⬜ Postman

---

## Step 10

Created Service Layer

Created

AnimeService

Learned

- @Service
- @Autowired
- Service communicates with Repository
- Service contains business logic

Methods Added

- addAnime()
- getAllAnime()
- getAnimeById()
- getAnimeByTitle()
- getAnimeByStudio()
- getAnimeByGenre()
- getAnimeByAnimeStatus()
- getAnimeByWatchStatus()

Learned

Repository methods are called inside Service.

Example

animeRepository.findAll()

animeRepository.findById(id)

animeRepository.save(anime)

---

## Step 11

Created Custom Repository Methods

Learned

Spring Data JPA can generate queries from method names.

Examples

findByTitle()

findByStudio()

findByAnimeStatus()

findByWatchStatus()

findByGenresContaining()

No SQL required.

Spring generates SQL automatically.

Important

Repository only declares methods.

No implementation is written.

Example

Optional<Anime> findByTitle(String title);

---

## Step 12

Created REST Controller

Created

AnimeController

Annotations Learned

@RestController

@RequestMapping

@PostMapping

@GetMapping

@RequestBody

@PathVariable

Controller communicates with Service.

Controller receives HTTP Requests.

Service performs business logic.

Repository accesses Database.

---

## Step 13

Configured MySQL

Updated

application.properties

Added

spring.datasource.url

spring.datasource.username

spring.datasource.password

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

spring.jpa.properties.hibernate.format_sql=true

Learned

ddl-auto=update

Creates or updates database tables automatically.

show-sql=true

Displays generated SQL queries in console.

---

## Step 14

Connected Spring Boot to MySQL

Application Started Successfully.

Verified

Tomcat started

Database connected

Hibernate initialized

Repository initialized

No startup errors.

---

## Step 15

Hibernate Generated Tables Automatically

Tables Created

anime

anime_genres

Reason

@Entity

creates

anime

table.

@ElementCollection

creates

anime_genres

table.

Learned

camelCase

↓

snake_case

Example

animeStatus

↓

anime_status

watchStatus

↓

watch_status

watchedEpisodes

↓

watched_episodes

Generated automatically by Hibernate.

---

## Step 16

Tested First API

POST

/anime

Successfully inserted first Anime.

JSON automatically converted into Anime object using @RequestBody.

Database generated

id

automatically.

Verified

Controller

↓

Service

↓

Repository

↓

Hibernate

↓

MySQL

Entire flow working successfully.

---

Current Progress

✅ Spring Boot Project

✅ Package Structure

✅ Enums

✅ Entity

✅ Repository

✅ Service

✅ Controller

✅ MySQL Configuration

✅ Hibernate Configuration

✅ Database Connection

✅ Auto Table Creation

✅ First POST API

⬜ Test Remaining GET APIs

⬜ PUT API

⬜ DELETE API

⬜ Exception Handling

⬜ Validation

⬜ DTO

⬜ Pagination

⬜ Sorting

---

## Step 17

Implemented Update API (PUT)

Endpoint

PUT /anime/{id}

Learned

REST Standard

Resource ID should come from URL.

Example

PUT /anime/1

Request body contains only updated data.

Service Method

updateAnime(Long id, Anime anime)

Process

1. Find existing anime using id.
2. If not found, throw ResourceNotFoundException.
3. Update required fields.
4. Save updated object.
5. Return updated anime.

Important

Never use

animeRepository.findById(id).get()

Reason

If record does not exist,
.get() throws NoSuchElementException.

Better

orElseThrow()

returns custom exception.

---

## Step 18

Implemented Global Exception Handling

Created

ResourceNotFoundException

GlobalExceptionHandler

Annotations Learned

@RestControllerAdvice

Acts as a global exception handler for all controllers.

@ExceptionHandler

Executes method whenever specified exception is thrown.

Flow

Controller

↓

Service

↓

Repository

↓

ResourceNotFoundException

↓

GlobalExceptionHandler

↓

HTTP Response

Current Response

404 Not Found

Anime not found with id: 100

Future Improvement

Return custom JSON ErrorResponse instead of plain text.

---

## Step 19

Improved GET API

Changed

getAnimeById()

Old

Returns

Optional<Anime>

New

Returns

Anime

Uses

orElseThrow()

Reason

API should either

Return Anime

OR

Return 404 Not Found

Instead of exposing Optional to client.

Also changed

Controller return type

Optional<Anime>

↓

Anime

---

## Step 20

Improved Title Search

Changed

getAnimeByTitle()

Old

Optional<Anime>

New

Anime

Uses

orElseThrow()

Message

Anime not found with title: {title}

Reason

Title is unique in AniVault.

Therefore return a single Anime.

---

## Step 21

Completed CRUD

Implemented

POST

GET

PUT

DELETE

CRUD Flow

POST

Create Anime

GET

Read Anime

PUT

Update Anime

DELETE

Delete Anime

All CRUD APIs tested successfully using Postman.

---

## Important Design Decisions

Single Object

Return Type

Anime

Examples

getAnimeById()

getAnimeByTitle()

Reason

Only one object should exist.

Multiple Objects

Return Type

List<Anime>

Examples

getAnimeByStudio()

getAnimeByGenre()

getAnimeByAnimeStatus()

getAnimeByWatchStatus()

Reason

Multiple anime can satisfy search criteria.

---

## Collection Selection

Use List

When returning search results.

Examples

Anime by Studio

Anime by Genre

Anime by Status

Reason

Maintains order.

Works naturally with Spring Data JPA.

Each database row is already unique.

Use Set

When duplicates should never exist.

Example

Set<String> genres

Reason

Anime cannot have duplicate genres.

Example

Action

Action

Drama

Automatically becomes

Action

Drama

---

## Important Interview Points

Why List instead of Set?

Search results represent database rows.

Database already returns unique entities.

List preserves order and is the standard return type for Spring Data JPA.

Why Set for genres?

Genres should never contain duplicate values.

Set automatically removes duplicates.

Why use orElseThrow() instead of get()?

get()

Throws NoSuchElementException if value is absent.

orElseThrow()

Throws meaningful custom exception.

Works with Global Exception Handler.

Produces proper HTTP 404 response.

Why use ID in URL instead of Request Body?

REST standard.

URL identifies the resource.

Request Body contains updated data.

Avoids conflicting IDs between URL and body.

---

# Validation

## What is Validation?

Validation is the process of checking whether the data received from the client is valid before processing or storing it in the database.

It ensures that only correct and meaningful data enters the application.

Example

Title

✅ Naruto

❌ ""

❌ "      "

Episodes

✅ 220

❌ 0

❌ -10

Without validation,

invalid data can be stored in the database.

---

## Why Validation?

Imagine a user sends

```json
{
    "title": "",
    "genres": [],
    "episodes": -10
}
```

Without validation,

Spring Boot accepts the request.

↓

Service executes.

↓

Repository saves the object.

↓

Database stores invalid data.

This leads to inconsistent and meaningless records.

Validation stops the request before it reaches the business logic.

---

## Manual Validation

Before Bean Validation,

developers used manual checks.

Example

```java
if (anime.getTitle() == null || anime.getTitle().trim().isEmpty()) {
    throw new IllegalArgumentException("Title cannot be blank");
}
```

Problems

- Too many if statements.
- Repeated code.
- Difficult to maintain.
- Validation logic gets mixed with business logic.
- Same validation must be written in multiple places.

---

## Bean Validation

Instead of writing manual checks,

Java provides Bean Validation.

Spring Boot integrates Bean Validation using Jakarta Validation.

Validation is written using annotations.

Example

```java
@NotBlank
private String title;
```

Now Spring automatically performs the validation.

No manual if statements are required.

---

## Why use Validation Annotations?

Instead of

```java
if(title == null || title.trim().isEmpty())
```

we simply write

```java
@NotBlank
private String title;
```

Advantages

- Cleaner code.
- Easy to understand.
- Reusable.
- Less boilerplate code.
- Validation stays close to the field it validates.

---

# Common Validation Annotations

## @NotBlank

Used for

String

Rejects

- null
- ""
- "     " (only spaces)

Example

```java
@NotBlank(message = "Title cannot be blank")
private String title;
```

If title contains only spaces,

validation fails.

---

## @NotEmpty

Used for

Collections

Examples

- List
- Set

Rejects

- null
- Empty Collection

Example

```java
@NotEmpty(message = "Genres cannot be empty")
private Set<String> genres;
```

Accepted

```text
Action
Drama
```

Rejected

```text
[]
```

---

## @NotNull

Used for

Objects

Enums

Rejects only

- null

Example

```java
@NotNull(message = "Anime status is required")
private AnimeStatus animeStatus;
```

Unlike @NotBlank,

it does not check empty strings.

It only checks whether the object exists.

---

## @Min

Used for

Numeric values.

Ensures that the value is greater than or equal to the specified minimum.

Example

```java
@Min(value = 1, message = "Episodes must be at least 1")
private int episodes;
```

Accepted

1

12

220

Rejected

0

-5

---

## message Attribute

Every validation annotation can have a custom error message.

Example

```java
@NotBlank(message = "Title cannot be blank")
```

Without custom message,

Spring returns its default message.

Using custom messages makes the API more user friendly.

---

# Why Validation is placed in RequestDTO?

Current Project

Validation annotations are placed in

AnimeRequestDTO

instead of

Anime Entity.

Reason

RequestDTO represents incoming client data.

It is the first object created from the HTTP request.

If validation fails,

the request is rejected immediately.

Entity should only represent the database table.

Keeping validation in RequestDTO separates

Input Validation

from

Database Representation.

---

# @Valid

Example

```java
@PostMapping
public AnimeResponseDTO addAnime(
        @Valid @RequestBody AnimeRequestDTO requestDTO) {
```

`@Valid`

tells Spring Boot

"Validate this object before executing the controller method."

If validation succeeds

↓

Controller method executes.

If validation fails

↓

Controller method is never executed.

---

# Validation Flow

Client

↓

HTTP Request

↓

RequestDTO

↓

@Valid

↓

Validation Performed

↓

Valid?

YES

↓

Controller

↓

Service

↓

Repository

↓

Database

NO

↓

MethodArgumentNotValidException

↓

Global Exception Handler

↓

400 Bad Request

---

# Advantages of Validation

- Prevents invalid data.
- Reduces manual if statements.
- Keeps business logic clean.
- Improves API quality.
- Provides meaningful error messages.
- Stops invalid requests before they reach the Service layer.

# AniVault Notes

---

# Step 22 - Validation

## Purpose
Validation ensures that invalid data is not stored in the database.

Instead of checking values manually using `if` statements, Spring Boot provides validation annotations.

---

## Common Validation Annotations

### `@NotBlank`

- Used for **String**
- Rejects:
    - `null`
    - `""`
    - `"   "` (only spaces)

```java
@NotBlank(message = "Title cannot be blank")
private String title;
```

---

### `@NotEmpty`

- Used for **Collections** (`List`, `Set`)
- Rejects:
    - `null`
    - Empty collection

```java
@NotEmpty(message = "Genres cannot be empty")
private Set<String> genres;
```

---

### `@NotNull`

- Used for **Objects and Enums**
- Rejects only `null`

```java
@NotNull(message = "Anime status is required")
private AnimeStatus animeStatus;
```

---

### `@Min`

- Used for numeric values

```java
@Min(value = 1, message = "Episodes must be at least 1")
private int episodes;
```

---

## Enable Validation

```java
@PostMapping
public Anime addAnime(@Valid @RequestBody Anime anime)
```

`@Valid` tells Spring Boot to validate the incoming request.

---

# Step 23 - Global Validation Exception

Use `@RestControllerAdvice` to return meaningful validation errors.

Example response:

```json
{
    "title": "Title cannot be blank",
    "genres": "Genres cannot be empty"
}
```

---

# Step 24 - DTO (Data Transfer Object)

## Why DTO?

```
Client
   ↓
RequestDTO
   ↓
Entity
   ↓
Database
   ↓
Entity
   ↓
ResponseDTO
   ↓
Client
```

### AnimeRequestDTO

Purpose:

- Receives client request
- Contains validation annotations
- Does **not** contain `id`

Generate:

- No-Args Constructor
- All-Args Constructor
- Getters
- Setters

---

### AnimeResponseDTO

Purpose:

- Sends response to client
- Contains `id`
- No validation annotations

Generate:

- No-Args Constructor
- All-Args Constructor
- Getters
- Setters

---

# Step 25 - Manual Mapping

## RequestDTO → Entity

```java
Anime anime = new Anime();

anime.setTitle(requestDTO.getTitle());
anime.setGenres(requestDTO.getGenres());
anime.setStudio(requestDTO.getStudio());
anime.setEpisodes(requestDTO.getEpisodes());
anime.setWatchedEpisodes(requestDTO.getWatchedEpisodes());
anime.setAnimeStatus(requestDTO.getAnimeStatus());
anime.setWatchStatus(requestDTO.getWatchStatus());
```

Purpose:

Convert RequestDTO into Entity.

---

## Save Entity

```java
Anime savedAnime = animeRepository.save(anime);
```

Database generates the `id`.

---

## Entity → ResponseDTO

```java
AnimeResponseDTO responseDTO = new AnimeResponseDTO();

responseDTO.setId(savedAnime.getId());
responseDTO.setTitle(savedAnime.getTitle());
responseDTO.setGenres(savedAnime.getGenres());
responseDTO.setStudio(savedAnime.getStudio());
responseDTO.setEpisodes(savedAnime.getEpisodes());
responseDTO.setWatchedEpisodes(savedAnime.getWatchedEpisodes());
responseDTO.setAnimeStatus(savedAnime.getAnimeStatus());
responseDTO.setWatchStatus(savedAnime.getWatchStatus());
```

Purpose:

Convert Entity into ResponseDTO.

---

# Step 26 - Optional

Repository:

```java
Optional<Anime> findByTitle(String title);
```

Why Optional?

- Record may or may not exist.
- Avoids returning `null`.

Service decides what to do.

```java
return animeRepository.findByTitle(title)
        .orElseThrow(() ->
                new ResourceNotFoundException(
                        "Anime not found with title: " + title));
```

### Rule

Repository → `Optional<Entity>`

Service → `Entity`

Controller → `ResponseDTO`

---

# Step 27 - RequestDTO vs ResponseDTO

### RequestDTO

Purpose:

Receive data from client.

---

### ResponseDTO

Purpose:

Send data back to client.

---

### Entity

Purpose:

Represents a database table.

Should **never** be exposed directly.

---

# Step 28 - Mapper

Without Mapper

```java
anime.setTitle(requestDTO.getTitle());
anime.setGenres(requestDTO.getGenres());
...
```

With MapStruct

```java
Anime anime = animeMapper.toEntity(requestDTO);

AnimeResponseDTO responseDTO =
        animeMapper.toResponseDTO(anime);
```

---

# Step 29 - Why AnimeMapper is an Interface?

```java
@Mapper(componentModel = "spring")
public interface AnimeMapper {

    Anime toEntity(AnimeRequestDTO requestDTO);

    AnimeResponseDTO toResponseDTO(Anime anime);

}
```

You only declare the methods.

MapStruct generates the implementation automatically.

---

## Framework Pattern

### Repository

```java
public interface AnimeRepository
```

↓

Spring Data JPA generates implementation.

---

### Mapper

```java
public interface AnimeMapper
```

↓

MapStruct generates implementation.

---

## Rule

**Developer writes WHAT.**

```java
Anime toEntity(AnimeRequestDTO dto);
```

**Framework writes HOW.**
AniVault Notes

Step 22 - Validation

Use validation to prevent invalid data.

@NotBlank → String (null, empty, spaces not allowed)

@NotEmpty → Collections (null or empty not allowed)

@NotNull → Objects/Enums (null not allowed)

@Min → Minimum numeric value.

Enable validation:

@PostMapping
public Anime addAnime(@Valid @RequestBody Anime anime)

Step 23 - Global Validation Exception

Use @RestControllerAdvice to return meaningful validation messages.

Step 24 - DTO

Flow:

Client → RequestDTO → Entity → Database → Entity → ResponseDTO → Client

RequestDTO: receives request, contains validation, no id.

ResponseDTO: sends response, contains id, no validation.

Step 25 - Manual Mapping

RequestDTO → Entity

Anime anime = new Anime();
anime.setTitle(requestDTO.getTitle());

Entity → ResponseDTO

AnimeResponseDTO dto = new AnimeResponseDTO();
dto.setTitle(savedAnime.getTitle());

Step 26 - Optional

Repository:

Optional<Anime> findByTitle(String title);

Service:

return animeRepository.findByTitle(title)
.orElseThrow(() -> new ResourceNotFoundException("Anime not found"));

Rule: - Repository → Optional<Entity>{=html} - Service → Entity -Controller → ResponseDTO

Step 27 - DTO Roles

RequestDTO → Input from client.

ResponseDTO → Output to client.

Entity → Database representation.

Step 28 - Mapper

Without Mapper:

anime.setTitle(requestDTO.getTitle());

With MapStruct:

Anime anime = animeMapper.toEntity(requestDTO);
AnimeResponseDTO dto = animeMapper.toResponseDTO(anime);

Step 29 - AnimeMapper Interface

@Mapper(componentModel = "spring")
public interface AnimeMapper {
Anime toEntity(AnimeRequestDTO dto);
AnimeResponseDTO toResponseDTO(Anime anime);
}

Developer writes WHAT. Framework writes HOW.