CREATE DATABASE IF NOT EXISTS student_management_system;

USE student_management_system;

CREATE TABLE users
(
    user_id INT PRIMARY KEY AUTO_INCREMENT,

    username VARCHAR(50) UNIQUE NOT NULL,

    password VARCHAR(255) NOT NULL,

    role VARCHAR(20) NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE departments
(
    department_id INT PRIMARY KEY AUTO_INCREMENT,

    department_name VARCHAR(100) UNIQUE NOT NULL,

    department_code VARCHAR(20) UNIQUE NOT NULL,

    description VARCHAR(255)
);
CREATE TABLE students
(
    student_id INT PRIMARY KEY AUTO_INCREMENT,

    roll_no VARCHAR(30) UNIQUE NOT NULL,

    first_name VARCHAR(50) NOT NULL,

    last_name VARCHAR(50) NOT NULL,

    gender VARCHAR(10),

    dob DATE,

    email VARCHAR(100) UNIQUE,

    phone VARCHAR(15),

    address TEXT,

    department_id INT,

    admission_date DATE,

    status VARCHAR(20),

    FOREIGN KEY(department_id)
        REFERENCES departments(department_id)
);
CREATE TABLE faculty
(
    faculty_id INT PRIMARY KEY AUTO_INCREMENT,

    faculty_name VARCHAR(100) NOT NULL,

    email VARCHAR(100) UNIQUE,

    phone VARCHAR(15),

    department_id INT,

    FOREIGN KEY(department_id)
        REFERENCES departments(department_id)
);
CREATE TABLE courses
(
    course_id INT PRIMARY KEY AUTO_INCREMENT,

    course_code VARCHAR(20) UNIQUE NOT NULL,

    course_name VARCHAR(100) NOT NULL,

    credits INT,

    department_id INT,

    FOREIGN KEY(department_id)
        REFERENCES departments(department_id)
);
CREATE TABLE enrollments
(
    enrollment_id INT PRIMARY KEY AUTO_INCREMENT,

    student_id INT,

    course_id INT,

    enrollment_date DATE,

    FOREIGN KEY(student_id)
        REFERENCES students(student_id)
        ON DELETE CASCADE,

    FOREIGN KEY(course_id)
        REFERENCES courses(course_id)
        ON DELETE CASCADE
);
CREATE TABLE enrollments
(
    enrollment_id INT PRIMARY KEY AUTO_INCREMENT,

    student_id INT,

    course_id INT,

    enrollment_date DATE,

    FOREIGN KEY(student_id)
        REFERENCES students(student_id)
        ON DELETE CASCADE,

    FOREIGN KEY(course_id)
        REFERENCES courses(course_id)
        ON DELETE CASCADE
);
CREATE TABLE attendance
(
    attendance_id INT PRIMARY KEY AUTO_INCREMENT,

    student_id INT,

    course_id INT,

    attendance_date DATE,

    status VARCHAR(20),

    FOREIGN KEY(student_id)
        REFERENCES students(student_id)
        ON DELETE CASCADE,

    FOREIGN KEY(course_id)
        REFERENCES courses(course_id)
        ON DELETE CASCADE
);
CREATE TABLE marks
(
    mark_id INT PRIMARY KEY AUTO_INCREMENT,

    student_id INT,

    course_id INT,

    marks DECIMAL(5,2),

    grade VARCHAR(2),

    FOREIGN KEY(student_id)
        REFERENCES students(student_id)
        ON DELETE CASCADE,

    FOREIGN KEY(course_id)
        REFERENCES courses(course_id)
        ON DELETE CASCADE
);
CREATE INDEX idx_student_email
    ON students(email);

CREATE INDEX idx_student_roll
    ON students(roll_no);

CREATE INDEX idx_course_code
    ON courses(course_code);

CREATE INDEX idx_department_code
    ON departments(department_code);

CREATE TABLE attendance
(
    attendance_id INT PRIMARY KEY AUTO_INCREMENT,

    student_id INT,

    course_id INT,

    attendance_date DATE,

    status VARCHAR(20),

    FOREIGN KEY(student_id)
        REFERENCES students(student_id)
        ON DELETE CASCADE,

    FOREIGN KEY(course_id)
        REFERENCES courses(course_id)
        ON DELETE CASCADE
);
CREATE TABLE marks
(
    mark_id INT PRIMARY KEY AUTO_INCREMENT,

    student_id INT,

    course_id INT,

    marks DECIMAL(5,2),

    grade VARCHAR(2),

    FOREIGN KEY(student_id)
        REFERENCES students(student_id)
        ON DELETE CASCADE,

    FOREIGN KEY(course_id)
        REFERENCES courses(course_id)
        ON DELETE CASCADE
);
