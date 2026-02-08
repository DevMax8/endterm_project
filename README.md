# Endterm Project – Student Course Enrollment System

## 1. Project Overview

This Endterm Project was completed as the final assignment of the course.  
The main goal of the project is to practice building a REST API using Spring Boot, JDBC, and PostgreSQL.

The application manages three main entities:

- Students
- Courses
- Enrollments

The system supports full CRUD operations and all endpoints were tested using Postman.

---

## 2. Technologies Used

The project was developed using the following tools and technologies:

- Java
- Spring Boot
- JDBC Template
- PostgreSQL
- Maven
- Postman

---

## 3. Database Structure

The PostgreSQL database contains three tables:

### Students
Stores information about students such as full name, email, and age.

### Courses
Stores course data including course name and credits.

### Enrollments
Connects students with courses and represents which student is enrolled in which course.

Foreign key constraints are used to ensure correct relationships between the tables.

---

## 4. Implemented Features (CRUD)

The REST API provides the following functionality:

### Student API
- Create a new student
- Get all students
- Get student by ID
- Update student information
- Delete student

### Course API
- Create a new course
- Get all courses
- Get course by ID
- Update course information
- Delete course

### Enrollment API
- Enroll a student into a course
- Get all enrollments
- Get enrollment by ID
- Update enrollment
- Remove enrollment

---

## 5. Postman Testing

All endpoints were tested successfully using Postman.

Examples of requests:

- `GET /students` → returns list of students
- `POST /courses` → creates a new course
- `POST /enrollments` → enrolls a student into a course
- `PUT /enrollments/{id}` → updates enrollment data
- `DELETE /enrollments/{id}` → deletes enrollment

---

## 6. Design Patterns Used

This project includes several design patterns required by the assignment:

1. **Builder Pattern**  
   Used for building Enrollment objects in a structured way.

2. **Factory Pattern**  
   Used to create Course objects through a factory class.

3. **Singleton Pattern**  
   Implemented for the LoggerService to ensure only one instance is used.

---

## 7. Exception Handling

A Global Exception Handler was implemented to handle common errors such as:

- Resource not found
- Invalid request data
- Database constraint violations

This makes API responses clearer and easier to debug.

---

## 8. Conclusion

This Endterm Project demonstrates how to build a working REST API with Spring Boot and PostgreSQL.  
It includes database relationships, CRUD functionality, design patterns, and exception handling as required in the final assignment.
