# Table Talk - Backend Service

<div align="center">
  <p>
    <a href="../README.md">Main Documentation</a> •
    <a href="../tt-react/README.md">Frontend Documentation</a> •
    <a href="../API.md">API Documentation</a>
  </p>
</div>

<div align="center">
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL" />
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker" />
</div>

<div align="center">
  <h3><a href="https://tt.gorocode.dev/manager" target="_blank">🔗 Live Demo</a></h3>
  <p>Demo credentials (all with password <code>Password1234$</code>):</p>
  <table>
    <tr>
      <td><strong>Username</strong></td>
      <td><strong>Role</strong></td>
    </tr>
    <tr>
      <td><code>admin</code></td>
      <td>Administrator (full access)</td>
    </tr>
    <tr>
      <td><code>manager</code></td>
      <td>Manager (reporting and management)</td>
    </tr>
    <tr>
      <td><code>worker</code></td>
      <td>Staff (day-to-day operations)</td>
    </tr>
  </table>
</div>

## 📋 Overview

The Table Talk backend is a Spring Boot application that provides a robust API for managing restaurant operations. It features real-time communication through WebSockets, secure authentication, and a comprehensive set of REST endpoints for table, order, menu, and product management.

## ✨ Features

- **RESTful API**: Complete set of endpoints for managing all aspects of the restaurant
- **WebSocket Support**: Real-time updates for kitchen and table order management
- **Authentication & Authorization**: Secure user authentication and role-based access control
- **Database Integration**: Persistent storage with PostgreSQL
- **Data Validation**: Comprehensive input validation and error handling
- **Printing Support**: Integration with receipt and kitchen printers

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.x
- **Language**: Java 17
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA
- **API Documentation**: Swagger/OpenAPI
- **Security**: Spring Security with JWT
- **Real-time Communication**: WebSocket (STOMP)
- **Build Tool**: Maven
- **Containerization**: Docker

## 🚀 Installation

### Prerequisites
- JDK 17+
- Maven
- PostgreSQL (or Docker)

### Local Development Setup

1. Clone the repository:
   ```bash
   git clone https://gitlab.com/goromigue/tt-bar-manager.git
   cd tt-bar-manager/tt-spring
   ```

2. Configure database in `application.properties`:
   ```properties
    FRONTEND_URL=http://localhost:5173
    SPRING_DATASOURCE_URL=jdbc:postgresql://postgres-db:5432/tabletalk
    SPRING_DATASOURCE_USERNAME=user
    SPRING_DATASOURCE_PASSWORD=tableuser
    SPRING_JPA_HIBERNATE_DDL_AUTO=update
    SPRING_JPA_SHOW_SQL=false
    JWT_SECRET=yourStrongSecretKeyHere
    JWT_EXPIRATION_MS=86400000
   ```

3. Build the application:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

5. The API will be available at http://localhost:8080

## 🏗️ Architecture

The backend follows a layered architecture with the following components:

### Controller Layer
- REST controllers that handle HTTP requests
- WebSocket message handlers

### Service Layer
- Business logic implementation
- Transaction management

### Data Access Layer
- JPA repositories for database operations
- Entity mappings

### DTOs (Data Transfer Objects)
- Objects for data exchange with clients
- Separation from internal entities

### Mappers
- Conversion between entities and DTOs
- Using MapStruct for efficient mapping

## 📦 API Endpoints

The backend exposes several endpoints for different aspects of the application:

- **/auth**: Authentication endpoints
- **/menu**: Menu and category management
- **/products**: Product information and stock management
- **/orders**: Order creation and management
- **/tables**: Table management
- **/map**: Restaurant layout management
- **/kitchen**: Kitchen-specific endpoints
- **/invoice**: Invoice generation and management
- **/print**: Printing service integration

For detailed API documentation, refer to the [API Documentation](../API.md).

## 🧪 Testing

The application includes different types of tests:

```bash
# Run unit tests
mvn test

# Run integration tests
mvn verify -P integration-test
```

## 🔄 Database Migrations

Database schema is managed through JPA entity definitions with Hibernate DDL:

```properties
spring.jpa.hibernate.ddl-auto=update
```

For production, it's recommended to use a proper database migration tool like Flyway or Liquibase.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.
