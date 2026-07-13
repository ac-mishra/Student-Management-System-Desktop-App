# 🎓 Student Management System

A modern desktop-based Student Management System developed using Java Swing, Core Java, JDBC, MySQL, and the MVC architecture. The application provides a centralized platform to manage students, departments, courses, faculty, attendance, marks, reports, and administrative operations.

---

# Project Overview

The Student Management System is designed to automate and simplify the management of academic information within an educational institution. It replaces manual record keeping with a secure, efficient, and user-friendly desktop application.

The system allows administrators to manage students, departments, faculty members, courses, attendance records, examination marks, reports, and database backup from a single interface.

---

# Features

## Authentication

- Secure Login System
- Password Encryption
- User Authentication
- Session Management

## Dashboard

- Total Students
- Total Departments
- Total Courses
- Total Faculty
- Attendance Statistics
- Marks Statistics

## Student Management

- Add Student
- Update Student
- Delete Student
- Search Student
- View Student Records

## Department Management

- Add Department
- Update Department
- Delete Department
- Search Department

## Course Management

- Add Course
- Update Course
- Delete Course
- Department-wise Course Assignment

## Faculty Management

- Add Faculty
- Update Faculty
- Delete Faculty

## Attendance Management

- Student Attendance
- Attendance Status
- Attendance Reports

## Marks Management

- Internal Marks
- External Marks
- Total Marks
- Grade Calculation

## Reports

- Student Reports
- Attendance Reports
- Marks Reports

## Database

- MySQL Integration
- JDBC
- Connection Pool (HikariCP)

---

# Technology Stack

Frontend
- Java Swing
- MigLayout
- FlatLaf

Backend
- Core Java
- JDBC

Database
- MySQL 8.0

Build Tool
- Maven

IDE
- IntelliJ IDEA

Version Control
- Git
- GitHub

---

# Project Structure

```text
StudentManagementSystem
│
├── src
│   └── main
│       ├── java
│       │   └── org
│       │       └── example
│       │           ├── config
│       │           │   ├── ConnectionPool.java
│       │           │   └── DatabaseConfig.java
│       │           │
│       │           ├── dao
│       │           │   ├── UserDAO.java
│       │           │   ├── StudentDAO.java
│       │           │   ├── DepartmentDAO.java
│       │           │   ├── CourseDAO.java
│       │           │   ├── FacultyDAO.java
│       │           │   ├── AttendanceDAO.java
│       │           │   ├── MarksDAO.java
│       │           │   └── impl
│       │           │       ├── UserDAOImpl.java
│       │           │       ├── StudentDAOImpl.java
│       │           │       ├── DepartmentDAOImpl.java
│       │           │       ├── CourseDAOImpl.java
│       │           │       ├── FacultyDAOImpl.java
│       │           │       ├── AttendanceDAOImpl.java
│       │           │       └── MarksDAOImpl.java
│       │           │
│       │           ├── model
│       │           │   ├── User.java
│       │           │   ├── Student.java
│       │           │   ├── Department.java
│       │           │   ├── Course.java
│       │           │   ├── Faculty.java
│       │           │   ├── Attendance.java
│       │           │   └── Marks.java
│       │           │
│       │           ├── service
│       │           │   ├── UserService.java
│       │           │   ├── StudentService.java
│       │           │   ├── DepartmentService.java
│       │           │   ├── CourseService.java
│       │           │   ├── FacultyService.java
│       │           │   ├── AttendanceService.java
│       │           │   ├── MarksService.java
│       │           │   └── impl
│       │           │       ├── UserServiceImpl.java
│       │           │       ├── StudentServiceImpl.java
│       │           │       ├── DepartmentServiceImpl.java
│       │           │       ├── CourseServiceImpl.java
│       │           │       ├── FacultyServiceImpl.java
│       │           │       ├── AttendanceServiceImpl.java
│       │           │       └── MarksServiceImpl.java
│       │           │
│       │           ├── ui
│       │           │   ├── login
│       │           │   ├── dashboard
│       │           │   ├── student
│       │           │   ├── department
│       │           │   ├── course
│       │           │   ├── faculty
│       │           │   ├── attendance
│       │           │   ├── marks
│       │           │   ├── reports
│       │           │   └── component
│       │           │
│       │           ├── util
│       │           │   ├── PasswordUtil.java
│       │           │   ├── TableSearchUtil.java
│       │           │   ├── DatabaseBackupUtil.java
│       │           │   └── ValidationUtil.java
│       │           │
│       │           └── Main.java
│       │
│       └── resources
│           ├── images
│           ├── icons
│           └── application.properties
│
├── pom.xml
├── README.md
└── database
    └── student_management_system.sql
```

# Database Tables

- users
- students
- departments
- courses
- faculty
- attendance
- marks

---

# Software Requirements

Operating System
- Windows 10 / Windows 11

Java
- JDK 21+

IDE
- IntelliJ IDEA

Database
- MySQL 8.0

Database Tool
- MySQL Workbench

---

# Hardware Requirements

Processor
- Intel i3 or above

RAM
- Minimum 4 GB
- Recommended 8 GB

Storage
- Minimum 1 GB Free

---

# Installation

1. Clone Repository

git clone https://github.com/yourusername/student-management-system.git

2. Open Project in IntelliJ IDEA

3. Import Maven Dependencies

4. Create MySQL Database

5. Execute SQL Script

6. Update Database Configuration

7. Run Main.java
8. Default Login Credentials
   Field	Value\
   Username - admin\
   Password - admin\
   Role	Administrator

---

# Design Pattern

MVC (Model View Controller)

Model
- Database Entities

View
- Java Swing UI

Controller
- Handles Business Logic

DAO
- Database Operations

Service
- Business Layer

---

# Libraries Used

- MigLayout
- FlatLaf
- MySQL Connector
- HikariCP
- Apache POI
- OpenCSV
- iText PDF
- BCrypt
- Log4j

---

# Future Enhancements

- Student Photo Upload
- Role Based Access
- Email Notification
- SMS Notification
- Cloud Database
- Online Portal
- REST API
- Mobile Application

---

# Author

Amrit Chandan Mishra

Java Developer
