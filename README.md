# 🚀 GraphQL Student Management API

A comprehensive Spring Boot application that integrates GraphQL with Hasura and PostgreSQL, featuring a complete student management system with REST API endpoints and Swagger documentation.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.1-green)
![GraphQL](https://img.shields.io/badge/GraphQL-Hasura-purple)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
![Docker](https://img.shields.io/badge/Docker-Compose-blue)

---

## 📋 Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Technologies Used](#technologies-used)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Docker Setup](#docker-setup)
- [Configuration](#configuration)
- [API Documentation](#api-documentation)
- [GraphQL Operations](#graphql-operations)
- [Database Schema](#database-schema)
- [Development](#development)
- [Testing](#testing)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)

---

## 🎯 Overview

This project demonstrates a modern microservices architecture using:

- **Spring Boot 3.3.1** as the main application framework
- **Hasura GraphQL Engine** for real-time GraphQL API
- **PostgreSQL 16** as the primary database
- **GraphQL Kotlin Client** for type-safe GraphQL operations
- **Liquibase** for database version control and migrations
- **Docker Compose** for containerized development environment
- **Swagger UI** for interactive API documentation

The application provides a complete CRUD (Create, Read, Update, Delete) interface for student management through both REST and GraphQL endpoints.

---

## 🏗️ Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Client Apps   │    │   Swagger UI    │    │  Hasura Console │
│                 │    │                 │    │                 │
└─────────┬───────┘    └─────────┬───────┘    └─────────┬───────┘
          │                      │                      │
          │ REST API             │ Documentation        │ GraphQL
          │                      │                      │
┌─────────▼──────────────────────▼──────────────────────▼───────┐
│                Spring Boot Application                        │
│                     (Port 8085)                              │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐   │
│  │ Controllers │  │  Services   │  │   GraphQL Client    │   │
│  └─────────────┘  └─────────────┘  └─────────────────────┘   │
└─────────────────────────┬─────────────────────────────────────┘
                          │
                          │ GraphQL Queries/Mutations
                          │
┌─────────────────────────▼─────────────────────────────────────┐
│                  Hasura GraphQL Engine                       │
│                     (Port 8081)                              │
└─────────────────────────┬─────────────────────────────────────┘
                          │
                          │ SQL Queries
                          │
┌─────────────────────────▼─────────────────────────────────────┐
│                    PostgreSQL Database                       │
│                     (Port 5434)                              │
└───────────────────────────────────────────────────────────────┘
```

---

## 🛠️ Technologies Used

### Backend Framework

- **Java 17** - Latest LTS version with modern language features
- **Spring Boot 3.3.1** - Application framework with auto-configuration
- **Spring Web** - RESTful web services
- **Spring Data JPA** - Data persistence layer
- **Spring WebFlux** - Reactive programming support

### GraphQL Stack

- **Hasura GraphQL Engine** - Real-time GraphQL API with subscriptions
- **GraphQL Kotlin Client 7.0.0** - Type-safe GraphQL client
- **Ktor HTTP Client 2.3.5** - Asynchronous HTTP client

### Database & Persistence

- **PostgreSQL 16** - Advanced open-source relational database
- **Liquibase** - Database version control and migration tool
- **HikariCP** - High-performance JDBC connection pool

### Development Tools

- **Kotlin 1.9.0** - Modern JVM language for GraphQL client generation
- **Maven** - Dependency management and build automation
- **Lombok** - Boilerplate code reduction
- **Jackson** - JSON processing library

### Documentation & Testing

- **Swagger UI (springdoc-openapi)** - Interactive API documentation
- **Spring Boot Test** - Testing framework
- **JUnit 5** - Unit testing framework

### DevOps & Containerization

- **Docker** - Containerization platform
- **Docker Compose** - Multi-container application orchestration

---

## ✨ Features

### 🎓 Student Management

- **Create Student** - Add new students with validation
- **Read Students** - Retrieve all students or by specific ID
- **Update Student** - Modify existing student information
- **Delete Student** - Remove students from the system

### 🔄 Dual API Support

- **REST API** - Traditional HTTP endpoints for CRUD operations
- **GraphQL API** - Flexible query language through Hasura

### 📊 Real-time Capabilities

- **GraphQL Subscriptions** - Real-time data updates via Hasura
- **Live Queries** - Automatic UI updates when data changes

### 🛡️ Data Integrity

- **Email Uniqueness** - Prevents duplicate email addresses
- **Input Validation** - Comprehensive data validation
- **Error Handling** - Structured error responses

### 📖 Documentation

- **Swagger UI** - Interactive API documentation at `/swagger-ui.html`
- **GraphQL Playground** - Query interface via Hasura Console

---

## 📋 Prerequisites

Before running this application, ensure you have the following installed:

### Required Software

- **Docker** (version 20.0 or higher)
- **Docker Compose** (version 2.0 or higher)
- **Java Development Kit (JDK) 17** or higher
- **Apache Maven** (version 3.6 or higher)

### System Requirements

- **RAM**: Minimum 4GB (8GB recommended)
- **Disk Space**: At least 2GB free space
- **Network**: Internet connection for downloading dependencies

### Verification Commands

```bash
# Check Docker installation
docker --version
docker-compose --version

# Check Java installation
java -version
javac -version

# Check Maven installation
mvn --version
```

---

## 🚀 Quick Start

### 1. Clone the Repository

```bash
git clone <repository-url>
cd graphql
```

### 2. Environment Setup

Create a `.env` file in the project root (if not exists):

```bash
# PostgreSQL Configuration
POSTGRES_USER=zayed
POSTGRES_PASSWORD=zayed
POSTGRES_DB=graphql

# Hasura Configuration
HASURA_GRAPHQL_DATABASE_URL=postgres://zayed:zayed@postgres:5432/graphql
HASURA_GRAPHQL_ENABLE_CONSOLE=true

# Spring Boot Configuration
SPRING_BOOT_DB_URL=postgres://zayed:zayed@postgres:5432/graphql
```

### 3. Start Infrastructure Services

```bash
# Start PostgreSQL and Hasura
docker-compose up -d

# Verify services are running
docker-compose ps
```

### 4. Build and Run the Application

```bash
# Clean and compile the project
mvn clean compile

# Generate GraphQL client code
mvn graphql-kotlin:generate-client

# Run the application
mvn spring-boot:run
```

### 5. Verify Installation

- **Spring Boot App**: http://localhost:8085
- **Swagger UI**: http://localhost:8085/swagger-ui.html
- **Hasura Console**: http://localhost:8081/console

---

## 🐳 Docker Setup

This project includes a comprehensive Docker setup for easy development and deployment.

### Docker Compose Services

The `docker-compose.yml` file defines the following services:

#### 🗄️ PostgreSQL Database

```yaml
postgres:
  image: postgres:16
  restart: always
  ports:
    - "5434:5432"
  environment:
    POSTGRES_USER: zayed
    POSTGRES_PASSWORD: zayed
    POSTGRES_DB: graphql
  volumes:
    - db_data:/var/lib/postgresql/data
  healthcheck:
    test: ["CMD-SHELL", "pg_isready -U $$POSTGRES_USER -d $$POSTGRES_DB"]
    interval: 5s
    timeout: 5s
    retries: 5
```

#### 🚀 Hasura GraphQL Engine

```yaml
hasura:
  image: hasura/graphql-engine:latest
  ports:
    - "8081:8080"
  restart: always
  depends_on:
    postgres:
      condition: service_healthy
  environment:
    HASURA_GRAPHQL_DATABASE_URL: postgres://zayed:zayed@postgres:5432/graphql
    HASURA_GRAPHQL_ENABLE_CONSOLE: "true"
    HASURA_GRAPHQL_ENABLED_APIS: "graphql,metadata"
    HASURA_GRAPHQL_ENABLE_CORS: "true"
    HASURA_GRAPHQL_CORS_DOMAIN: "*"
```

### Docker Commands

```bash
# Start all services in detached mode
docker-compose up -d

# View service logs
docker-compose logs -f

# Stop all services
docker-compose down

# Stop and remove volumes (⚠️ This will delete all data)
docker-compose down -v

# Rebuild and start services
docker-compose up -d --build

# Check service status
docker-compose ps

# Access PostgreSQL directly
docker-compose exec postgres psql -U zayed -d graphql
```

### Creating a Dockerfile for Spring Boot App

Create a `Dockerfile` in the project root to containerize the Spring Boot application:

```dockerfile
# Multi-stage build for Spring Boot application
FROM maven:3.9.4-openjdk-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Create non-root user for security
RUN groupadd -r spring && useradd -r -g spring spring

# Copy the built JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Change ownership to spring user
RUN chown spring:spring app.jar

# Switch to non-root user
USER spring

# Expose port
EXPOSE 8085

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8085/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Complete Docker Compose with Spring Boot

Update your `docker-compose.yml` to include the Spring Boot application:

```yaml
version: "3.8"

services:
  postgres:
    image: postgres:16
    restart: always
    volumes:
      - db_data:/var/lib/postgresql/data
    env_file:
      - .env
    ports:
      - "5434:5432"
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U $$POSTGRES_USER -d $$POSTGRES_DB"]
      interval: 5s
      timeout: 5s
      retries: 5

  hasura:
    image: hasura/graphql-engine:latest
    ports:
      - "8081:8080"
    restart: always
    env_file:
      - .env
    depends_on:
      postgres:
        condition: service_healthy
    environment:
      HASURA_GRAPHQL_DATABASE_URL: postgres://zayed:zayed@postgres:5432/graphql
      HASURA_GRAPHQL_ENABLE_CONSOLE: "true"
      HASURA_GRAPHQL_ENABLED_APIS: "graphql,metadata"
      HASURA_GRAPHQL_ENABLE_CORS: "true"
      HASURA_GRAPHQL_CORS_DOMAIN: "*"

  spring-app:
    build: .
    ports:
      - "8085:8085"
    restart: always
    depends_on:
      postgres:
        condition: service_healthy
      hasura:
        condition: service_started
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/graphql
      SPRING_DATASOURCE_USERNAME: zayed
      SPRING_DATASOURCE_PASSWORD: zayed
      HASURA_GRAPHQL_ENDPOINT: http://hasura:8080/v1/graphql
    healthcheck:
      test: ["CMD", "curl", "-f", "http://localhost:8085/actuator/health"]
      interval: 30s
      timeout: 10s
      retries: 3

volumes:
  db_data:
```

### Running with Docker Compose

```bash
# Build and start all services
docker-compose up -d --build

# View logs for all services
docker-compose logs -f

# View logs for specific service
docker-compose logs -f spring-app

# Scale the Spring Boot application (if needed)
docker-compose up -d --scale spring-app=2
```

---

## ⚙️ Configuration

### Application Properties

The main configuration file is located at `src/main/resources/application.properties`:

```properties
# Application Configuration
spring.application.name=GraphQlAPI
server.port=8085

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5434/graphql
spring.datasource.username=zayed
spring.datasource.password=zayed
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.open-in-view=false

# Liquibase Configuration
spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.yaml
spring.liquibase.enabled=true

# GraphQL Client Configuration
hasura.graphql.endpoint=http://localhost:8081/v1/graphql

# Swagger Configuration
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

### Environment Variables

For different environments, you can override properties using environment variables:

```bash
# Database Configuration
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5434/graphql
export SPRING_DATASOURCE_USERNAME=your_username
export SPRING_DATASOURCE_PASSWORD=your_password

# Hasura Configuration
export HASURA_GRAPHQL_ENDPOINT=http://localhost:8081/v1/graphql

# Server Configuration
export SERVER_PORT=8085
```

### Profile-based Configuration

Create environment-specific configuration files:

- `application-dev.properties` - Development environment
- `application-prod.properties` - Production environment
- `application-test.properties` - Testing environment

Run with specific profile:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## 📖 API Documentation

### REST API Endpoints

The application provides RESTful endpoints for student management:

#### Base URL

```
http://localhost:8085
```

#### Endpoints

| Method   | Endpoint         | Description        | Request Body   |
| -------- | ---------------- | ------------------ | -------------- |
| `GET`    | `/students`      | Get all students   | None           |
| `GET`    | `/students/{id}` | Get student by ID  | None           |
| `POST`   | `/students`      | Create new student | `StudentInput` |
| `PUT`    | `/students/{id}` | Update student     | `StudentInput` |
| `DELETE` | `/students/{id}` | Delete student     | None           |

#### Request/Response Examples

**Create Student (POST /students)**

```json
// Request
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "age": 22
}

// Response
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "age": 22
}
```

**Get All Students (GET /students)**

```json
// Response
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john.doe@example.com",
    "age": 22
  },
  {
    "id": 2,
    "name": "Jane Smith",
    "email": "jane.smith@example.com",
    "age": 21
  }
]
```

### Swagger UI

Interactive API documentation is available at:

- **URL**: http://localhost:8085/swagger-ui.html
- **API Docs JSON**: http://localhost:8085/api-docs

The Swagger UI provides:

- Interactive API testing
- Request/response schemas
- Authentication details
- Example requests and responses

---

## 🔄 GraphQL Operations

### Available Queries and Mutations

The application uses the following GraphQL operations through Hasura:

#### Queries

**Get All Students**

```graphql
query GetAllStudents {
  student {
    id
    name
    email
    age
  }
}
```

**Get Student by ID**

```graphql
query GetStudentById($id: Int!) {
  student_by_pk(id: $id) {
    id
    name
    email
    age
  }
}
```

#### Mutations

**Insert New Student**

```graphql
mutation InsertNewStudent($name: String!, $email: String!, $age: Int!) {
  insert_student_one(object: { name: $name, email: $email, age: $age }) {
    id
    name
    email
    age
  }
}
```

**Update Student**

```graphql
mutation UpdateStudentById(
  $id: Int!
  $name: String!
  $email: String!
  $age: Int!
) {
  update_student_by_pk(
    pk_columns: { id: $id }
    _set: { name: $name, email: $email, age: $age }
  ) {
    id
    name
    email
    age
  }
}
```

**Delete Student**

```graphql
mutation DeleteStudentById($id: Int!) {
  delete_student_by_pk(id: $id) {
    id
    name
    email
    age
  }
}
```

### GraphQL Client Generation

The project uses GraphQL Kotlin Client to generate type-safe client code:

#### Schema Introspection

First, you need to generate the GraphQL schema from Hasura:

```bash
# Install GraphQL CLI globally (if not already installed)
npm install -g graphql-cli

# Generate schema from Hasura endpoint
gq "http://localhost:8080/v1/graphql" --introspect > schema.graphql

# Alternative using curl
curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"query": "query IntrospectionQuery { __schema { queryType { name } mutationType { name } subscriptionType { name } types { ...FullType } directives { name description locations args { ...InputValue } } } } fragment FullType on __Type { kind name description fields(includeDeprecated: true) { name description args { ...InputValue } type { ...TypeRef } isDeprecated deprecationReason } inputFields { ...InputValue } interfaces { ...TypeRef } enumValues(includeDeprecated: true) { name description isDeprecated deprecationReason } possibleTypes { ...TypeRef } } fragment InputValue on __InputValue { name description type { ...TypeRef } defaultValue } fragment TypeRef on __Type { kind name ofType { kind name ofType { kind name ofType { kind name ofType { kind name ofType { kind name ofType { kind name ofType { kind name } } } } } } } }"}' \
  http://localhost:8080/v1/graphql > schema.json
```

#### Client Code Generation

```bash
# Generate GraphQL client classes
mvn graphql-kotlin:generate-client

# Generated classes location
target/generated-sources/graphql/
```

#### Manual Schema Update

If you need to update the schema manually, place it in:

```
src/main/resources/graphql/schema.graphql
```

### Hasura Console

Access the Hasura Console for GraphQL operations:

- **URL**: http://localhost:8081/console
- **Features**:
  - GraphQL Playground
  - Database management
  - Real-time subscriptions
  - Permissions and roles
  - Event triggers

---

## 🗄️ Database Schema

### Student Table

The application uses a simple student table with the following structure:

```sql
CREATE TABLE student (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    age INTEGER NOT NULL CHECK (age > 0)
);
```

### Table Details

| Column  | Type           | Constraints               | Description                         |
| ------- | -------------- | ------------------------- | ----------------------------------- |
| `id`    | `SERIAL`       | PRIMARY KEY               | Auto-incrementing unique identifier |
| `name`  | `VARCHAR(255)` | NOT NULL                  | Student's full name                 |
| `email` | `VARCHAR(255)` | UNIQUE, NOT NULL          | Student's email address             |
| `age`   | `INTEGER`      | NOT NULL, CHECK (age > 0) | Student's age                       |

### Database Migrations

Database schema is managed using Liquibase:

- **Master changelog**: `src/main/resources/db/changelog/db.changelog-master.yaml`
- **Migration files**: Located in `src/main/resources/db/changelog/`

#### Running Migrations

```bash
# Run migrations manually
mvn liquibase:update

# Rollback migrations
mvn liquibase:rollback -Dliquibase.rollbackCount=1

# Generate migration diff
mvn liquibase:diff

# Validate migrations
mvn liquibase:validate
```

### Sample Data

You can insert sample data using the Hasura Console or REST API:

```sql
INSERT INTO student (name, email, age) VALUES
('Alice Johnson', 'alice.johnson@example.com', 20),
('Bob Wilson', 'bob.wilson@example.com', 22),
('Carol Brown', 'carol.brown@example.com', 19),
('David Lee', 'david.lee@example.com', 21);
```

---

## 🛠️ Development

### Project Structure

```
graphql/
├── src/
│   ├── main/
│   │   ├── java/com/sofa/graphql/
│   │   │   ├── GraphQlApiApplication.java      # Main application class
│   │   │   ├── config/
│   │   │   │   └── HasuraConfig.java           # Hasura client configuration
│   │   │   ├── controller/
│   │   │   │   └── StudentController.java      # REST API endpoints
│   │   │   ├── service/
│   │   │   │   ├── HasuraService.java          # GraphQL service layer
│   │   │   │   ├── StudentMapper.java          # Entity mapping utilities
│   │   │   │   └── model/
│   │   │   │       └── Student.java            # Student data model
│   │   │   └── exception/
│   │   │       ├── GraphQLRequestException.java
│   │   │       ├── DuplicateEmailException.java
│   │   │       ├── StudentRestException.java
│   │   │       └── StudentErrorResponse.java
│   │   └── resources/
│   │       ├── application.properties          # Application configuration
│   │       ├── db/changelog/                   # Liquibase migrations
│   │       └── graphql/                        # GraphQL queries and schema
│   │           ├── schema.graphql
│   │           ├── GetAllStudents.graphql
│   │           ├── GetStudentById.graphql
│   │           ├── InsertNewStudent.graphql
│   │           ├── UpdateStudentById.graphql
│   │           └── DeleteStudentById.graphql
│   └── test/                                   # Test files
├── target/
│   └── generated-sources/graphql/              # Generated GraphQL client code
├── docker-compose.yml                          # Docker services configuration
├── .env                                        # Environment variables
├── pom.xml                                     # Maven configuration
└── README.md                                   # Project documentation
```

### Development Workflow

1. **Start Infrastructure**

   ```bash
   docker-compose up -d
   ```

2. **Generate GraphQL Schema**

   ```bash
   gq "http://localhost:8080/v1/graphql" --introspect > src/main/resources/graphql/schema.graphql
   ```

3. **Generate Client Code**

   ```bash
   mvn graphql-kotlin:generate-client
   ```

4. **Run Application**

   ```bash
   mvn spring-boot:run
   ```

5. **Test Changes**
   ```bash
   mvn test
   ```

### Code Style and Standards

- **Java**: Follow Oracle Java Code Conventions
- **Kotlin**: Follow Kotlin Coding Conventions for generated code
- **GraphQL**: Use descriptive query and mutation names
- **REST**: Follow RESTful API design principles

### IDE Setup

#### IntelliJ IDEA

1. Import as Maven project
2. Enable annotation processing for Lombok
3. Install GraphQL plugin for syntax highlighting
4. Configure code style settings

#### VS Code

1. Install Java Extension Pack
2. Install GraphQL extension
3. Configure Maven integration

---

## 🧪 Testing

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=StudentControllerTest

# Run tests with coverage
mvn test jacoco:report

# Integration tests
mvn verify
```

### Test Structure

```
src/test/java/com/sofa/graphql/
├── controller/
│   └── StudentControllerTest.java      # REST API tests
├── service/
│   └── HasuraServiceTest.java          # Service layer tests
└── integration/
    └── GraphQLIntegrationTest.java     # End-to-end tests
```

### Test Data

Use `@TestPropertySource` for test-specific configurations:

```java
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
```

### Mock Testing

Example service test with mocked dependencies:

```java
@ExtendWith(MockitoExtension.class)
class HasuraServiceTest {

    @Mock
    private OkHttpClient client;

    @Mock
    private ObjectMapper mapper;

    @InjectMocks
    private HasuraService hasuraService;

    @Test
    void shouldGetAllStudents() {
        // Test implementation
    }
}
```

---

## 🔧 Troubleshooting

### Common Issues

#### 1. Port Already in Use

```bash
# Check what's using the port
netstat -ano | findstr :8085
netstat -ano | findstr :8081
netstat -ano | findstr :5434

# Kill the process (Windows)
taskkill /PID <process_id> /F

# Kill the process (Linux/Mac)
kill -9 <process_id>
```

#### 2. Docker Services Not Starting

```bash
# Check Docker status
docker-compose ps

# View service logs
docker-compose logs postgres
docker-compose logs hasura

# Restart services
docker-compose restart
```

#### 3. Database Connection Issues

```bash
# Test PostgreSQL connection
docker-compose exec postgres psql -U zayed -d graphql -c "SELECT 1;"

# Check application logs
mvn spring-boot:run | grep -i error
```

#### 4. GraphQL Schema Issues

```bash
# Regenerate schema
gq "http://localhost:8080/v1/graphql" --introspect > src/main/resources/graphql/schema.graphql

# Regenerate client code
mvn clean graphql-kotlin:generate-client compile
```

#### 5. Maven Build Issues

```bash
# Clean and rebuild
mvn clean install

# Skip tests if needed
mvn clean install -DskipTests

# Update dependencies
mvn dependency:resolve
```

### Performance Optimization

#### Database

- Add indexes for frequently queried columns
- Use connection pooling (HikariCP is already configured)
- Monitor query performance with `spring.jpa.show-sql=true`

#### Application

- Enable caching for frequently accessed data
- Use async processing for heavy operations
- Monitor memory usage and GC performance

#### GraphQL

- Use field selection to minimize data transfer
- Implement query complexity analysis
- Cache GraphQL responses when appropriate

### Monitoring and Logging

#### Application Metrics

```properties
# Enable actuator endpoints
management.endpoints.web.exposure.include=health,info,metrics,prometheus
management.endpoint.health.show-details=always
```

#### Logging Configuration

```properties
# Logging levels
logging.level.com.sofa.graphql=DEBUG
logging.level.org.springframework.web=INFO
logging.level.org.hibernate.SQL=DEBUG
```

---

## 🤝 Contributing

### Getting Started

1. **Fork the repository**
2. **Create a feature branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. **Make your changes**
4. **Add tests for new functionality**
5. **Run tests and ensure they pass**
   ```bash
   mvn test
   ```
6. **Commit your changes**
   ```bash
   git commit -m "Add: your feature description"
   ```
7. **Push to your fork**
   ```bash
   git push origin feature/your-feature-name
   ```
8. **Create a Pull Request**

### Code Guidelines

- Write clear, self-documenting code
- Add JavaDoc comments for public methods
- Follow existing code style and patterns
- Include unit tests for new features
- Update documentation as needed

### Pull Request Process

1. Ensure all tests pass
2. Update README.md if needed
3. Add description of changes
4. Request review from maintainers
5. Address feedback promptly

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 📞 Support

For questions, issues, or contributions:

- **Issues**: [GitHub Issues](https://github.com/your-repo/issues)
- **Discussions**: [GitHub Discussions](https://github.com/your-repo/discussions)
- **Email**: your-email@example.com

---

## 🙏 Acknowledgments

- [Spring Boot](https://spring.io/projects/spring-boot) - Application framework
- [Hasura](https://hasura.io/) - GraphQL engine
- [PostgreSQL](https://www.postgresql.org/) - Database system
- [GraphQL Kotlin](https://github.com/ExpediaGroup/graphql-kotlin) - GraphQL client
- [Docker](https://www.docker.com/) - Containerization platform

---

**Happy Coding! 🚀**

#### Running Migrations

```bash
# Run migrations manually
mvn liquibase:update

# Rollback migrations
mvn liquibase:rollback -Dliquibase.rollbackCount=1

# Generate migration diff
mvn liquibase:diff
```
