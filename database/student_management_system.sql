CREATE TABLE attendance
(
    attendance_id INT PRIMARY KEY AUTO_INCREMENT,

    student_id INT NOT NULL,

    course_id INT NOT NULL,

    attendance_date DATE NOT NULL,

    status VARCHAR(20) NOT NULL,

    CONSTRAINT fk_attendance_student
        FOREIGN KEY(student_id)
        REFERENCES students(student_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_attendance_course
        FOREIGN KEY(course_id)
        REFERENCES courses(course_id)
        ON DELETE CASCADE
);

CREATE TABLE marks
(
    mark_id INT PRIMARY KEY AUTO_INCREMENT,

    student_id INT NOT NULL,

    course_id INT NOT NULL,

    marks DOUBLE NOT NULL,

    grade VARCHAR(5) NOT NULL,

    CONSTRAINT fk_marks_student
        FOREIGN KEY(student_id)
            REFERENCES students(student_id)
            ON DELETE CASCADE,

    CONSTRAINT fk_marks_course
        FOREIGN KEY(course_id)
            REFERENCES courses(course_id)
            ON DELETE CASCADE
);

CREATE INDEX idx_student_roll
    ON students(roll_no);

CREATE INDEX idx_student_email
    ON students(email);

CREATE INDEX idx_course_code
    ON courses(course_code);

CREATE INDEX idx_department_code
    ON departments(department_code);

CREATE INDEX idx_faculty_email
    ON faculty(email);

CREATE INDEX idx_attendance_student
    ON attendance(student_id);

CREATE INDEX idx_marks_student
    ON marks(student_id);

CREATE INDEX idx_enrollment_student
    ON enrollments(student_id);

INSERT INTO attendance
(
    student_id,
    course_id,
    attendance_date,
    status
)
VALUES
    (
        2,
        1,
        CURDATE(),
        'Present'
    );

INSERT INTO marks
(
    student_id,
    course_id,
    marks,
    grade
)
VALUES
    (
        2,
        1,
        92.50,
        'A+'
    );