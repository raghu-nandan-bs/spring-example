# School Management System

A simple Spring Boot REST API for managing courses, students, and teachers with PostgreSQL database.

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- Docker and Docker Compose

## Quick Start

1. **Start PostgreSQL database:**
   ```bash
   docker-compose up -d
   ```

2. **Run the Spring Boot application:**
   ```bash
   mvn spring-boot:run
   ```

3. **Access the API at:** `http://localhost:8080`

## API Endpoints

### Courses
- `GET /api/courses` - Get all courses
- `GET /api/courses/{id}` - Get course by ID
- `POST /api/courses` - Create new course
- `PUT /api/courses/{id}` - Update course
- `DELETE /api/courses/{id}` - Delete course

### Students
- `GET /api/students` - Get all students
- `GET /api/students/{id}` - Get student by ID
- `POST /api/students` - Create new student
- `PUT /api/students/{id}` - Update student
- `DELETE /api/students/{id}` - Delete student

### Teachers
- `GET /api/teachers` - Get all teachers
- `GET /api/teachers/{id}` - Get teacher by ID
- `POST /api/teachers` - Create new teacher
- `PUT /api/teachers/{id}` - Update teacher
- `DELETE /api/teachers/{id}` - Delete teacher

## Example Usage

**Create a course:**
```bash
curl -X POST http://localhost:8080/api/courses \
  -H "Content-Type: application/json" \
  -d '{"name":"Java Programming","description":"Learn Java basics","credits":3}'
```

**Create a student:**
```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"firstName":"John","lastName":"Doe","email":"john.doe@example.com","phoneNumber":"123-456-7890"}'
```

## Database

PostgreSQL runs on `localhost:5432` with:
- Database: `school_management`
- Username: `postgres`
- Password: `password`

Tables are automatically created when the application starts.