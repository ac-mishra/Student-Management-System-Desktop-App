# 🎓 Student Management System

A desktop-based **Student Management System** developed using **Java Swing**, **JDBC**, **MySQL**, and the **MVC Architecture**. The application provides an efficient way to manage students, departments, courses, faculty, attendance, marks, reports, and database backups through an intuitive graphical user interface.

---

# Features

- 🔐 Secure Login System
- 📊 Dashboard with Statistics
- 👨‍🎓 Student Management
- 🏢 Department Management
- 📚 Course Management
- 👨‍🏫 Faculty Management
- 📅 Attendance Management
- 📝 Marks Management
- 📄 Report Generation
- 💾 Database Backup
- 🔍 Search Functionality
- 📤 Export Reports to PDF & Excel

---

# Technologies Used

| Technology | Purpose |
|------------|----------|
| Java 21+ | Programming Language |
| Java Swing | GUI Development |
| JDBC | Database Connectivity |
| MySQL | Database |
| Maven | Dependency Management |
| FlatLaf | Modern UI Theme |
| MigLayout | Advanced Layout Manager |
| Apache POI | Excel Export |
| iText PDF | PDF Export |
| BCrypt | Password Encryption |

---

# Project Structure

```text
StudentManagementSystem
│
├── src
│   └── main
│       ├── java
│       │   └── org.example
│       │       ├── config
│       │       ├── dao
│       │       ├── dao.impl
│       │       ├── model
│       │       ├── service
│       │       ├── service.impl
│       │       ├── ui
│       │       │   ├── login
│       │       │   ├── dashboard
│       │       │   ├── student
│       │       │   ├── department
│       │       │   ├── course
│       │       │   ├── faculty
│       │       │   ├── attendance
│       │       │   ├── marks
│       │       │   ├── reports
│       │       │   └── component
│       │       ├── util
│       │       └── Main.java
│       └── resources
│
├── database
├── images
├── README.md
└── pom.xml
```

---

# Default Login Credentials

| Username | Password |
|----------|----------|
| admin | admin |

---

# Application Screenshots

## Login

![Login](screenshots/login.png)

---

## Dashboard

![Dashboard](screenshots/dashboard.png)

---

## Student Management

![Students](screenshots/students.png)

---

## Department Management

![Departments](screenshots/departments.png)

---

## Course Management

![Courses](screenshots/courses.png)

---

## Faculty Management

![Faculty](screenshots/faculty.png)

---

## Attendance Management

![Attendance](screenshots/attendance.png)

---

## Marks Management

![Marks](screenshots/marks.png)

---

## Reports

![Reports](screenshots/reports.png)

---

# UML Diagrams

## Use Case Diagram

![Use Case](screenshots/use case diagram.png)

---

## Class Diagram

![Class Diagram](screenshots/class diagram.png)

---

## Sequence Diagram

![Sequence Diagram](screenshots/sequence diagram.png)

---

## Activity Diagram

![Activity Diagram](screenshots/activity diagram.png)

---

## Component Diagram

![Component Diagram](screenshots/component diagram.png)

---

## Deployment Diagram

![Deployment Diagram](screenshots/deployment diagram.png)

---

## Flowcharts

![Flowcharts](screenshots/flowchart.png)

---

# Database Schema

The project uses MySQL and contains the following tables:

- users
- students
- departments
- courses
- faculty
- attendance
- marks

Database script:

```
database/student_management_system.sql
```

---

# Installation

Clone the repository

```bash
git clone https://github.com/your-username/StudentManagementSystem.git
```

Go to the project

```bash
cd StudentManagementSystem
```

Import into IntelliJ IDEA.

Configure MySQL database.

Run

```
Main.java
```

---

# Future Enhancements

- Role-Based Authentication
- Student Portal
- Faculty Portal
- Parent Portal
- Email Notifications
- SMS Notifications
- Cloud Database
- REST API
- Mobile Application
- Analytics Dashboard

---

# Author

**Amrit Chandan Mishra**

Java Developer

GitHub:
https://github.com/ac-mishra

---

# License

This project is developed for educational purposes.