# GraphQlAPI

A Spring Boot project integrating GraphQL with Hasura and PostgreSQL, featuring Swagger UI for API documentation.

---

## Overview

- Built with Spring Boot 3.3.1, Java 17, and Kotlin 1.9.
- Uses Hasura as a GraphQL engine connected to PostgreSQL database.
- Communicates with Hasura using GraphQL Kotlin Client.
- Database changes managed with Liquibase.
- Swagger (springdoc-openapi) integrated for automatic API documentation.
- Docker Compose setup to easily run PostgreSQL and Hasura services.

---

## Technologies Used

- Java 17 + Kotlin 1.9
- Spring Boot 3.3.1 (Web, Data JPA, WebFlux)
- GraphQL Kotlin Client and Ktor HTTP Client
- PostgreSQL 16 (via Docker)
- Hasura GraphQL Engine
- Liquibase for database migrations
- Swagger UI with springdoc-openapi-starter-webmvc-ui
- Lombok and Apache Commons Lang3

---

## Prerequisites

- Docker & Docker Compose
- JDK 17 or higher
- Maven 3.x or higher
- Internet connection for downloading dependencies

---

## Setup and Running

1. **Start PostgreSQL and Hasura with Docker Compose:**

   Make sure to have a `.env` file containing the required environment variables for PostgreSQL credentials (user, password, database name).

   Run:

   ```bash
   docker-compose up -d
## Configure and Run the Spring Boot Application

1. **Verify your `application.properties` or `application.yml` matches your setup:**

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/graphql
spring.datasource.username=zayed
spring.datasource.password=zayed
server.port=8085
hasura.graphql.endpoint=http://localhost:8081/v1/graphql
