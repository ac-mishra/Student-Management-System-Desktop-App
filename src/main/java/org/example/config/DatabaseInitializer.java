package org.example.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initializeDatabase() {

        createDepartmentTable();

        createStudentTable();

        createCourseTable();

        createFacultyTable();

        createAttendanceTable();

        createMarksTable();

        createIndexes();

        createStoredProcedures();

    }

    private static void execute(String sql) {

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                Statement statement =
                        connection.createStatement()

        ) {

            statement.execute(sql);

        }

        catch (SQLException e) {

            e.printStackTrace();

        }

    }
    private static void createDepartmentTable() {

        String sql = """

            CREATE TABLE IF NOT EXISTS departments(

                department_id INT PRIMARY KEY AUTO_INCREMENT,

                department_code VARCHAR(20) UNIQUE NOT NULL,

                department_name VARCHAR(100) NOT NULL

            )

            """;

        execute(sql);

    }

    private static void createStudentTable() {

        String sql = """

            CREATE TABLE IF NOT EXISTS students(

                student_id INT PRIMARY KEY AUTO_INCREMENT,

                roll_no VARCHAR(20) UNIQUE NOT NULL,

                first_name VARCHAR(50),

                last_name VARCHAR(50),

                gender VARCHAR(10),

                dob DATE,

                email VARCHAR(100),

                phone VARCHAR(20),

                address TEXT,

                admission_date DATE,

                status VARCHAR(20),

                department_id INT,

                FOREIGN KEY(department_id)

                REFERENCES departments(department_id)

            )

            """;

        execute(sql);

    }
    private static void createCourseTable() {

        String sql = """

            CREATE TABLE IF NOT EXISTS courses(

                course_id INT PRIMARY KEY AUTO_INCREMENT,

                course_code VARCHAR(20) UNIQUE NOT NULL,

                course_name VARCHAR(100) NOT NULL,

                credits INT NOT NULL,

                department_id INT,

                FOREIGN KEY(department_id)

                REFERENCES departments(department_id)

            )

            """;

        execute(sql);

    }

    private static void createFacultyTable() {

        String sql = """

            CREATE TABLE IF NOT EXISTS faculty(

                faculty_id INT PRIMARY KEY AUTO_INCREMENT,

                faculty_name VARCHAR(100) NOT NULL,

                email VARCHAR(100),

                phone VARCHAR(20),

                department_id INT,

                FOREIGN KEY(department_id)

                REFERENCES departments(department_id)

            )

            """;

        execute(sql);

    }
    private static void createAttendanceTable() {

        String sql = """

            CREATE TABLE IF NOT EXISTS attendance(

                attendance_id INT PRIMARY KEY AUTO_INCREMENT,

                student_id INT NOT NULL,

                course_id INT NOT NULL,

                attendance_date DATE NOT NULL,

                status VARCHAR(20) NOT NULL,

                FOREIGN KEY(student_id)

                REFERENCES students(student_id)

                ON DELETE CASCADE,

                FOREIGN KEY(course_id)

                REFERENCES courses(course_id)

                ON DELETE CASCADE

            )

            """;

        execute(sql);

    }

    private static void createMarksTable() {

        String sql = """

            CREATE TABLE IF NOT EXISTS marks(

                marks_id INT PRIMARY KEY AUTO_INCREMENT,

                student_id INT NOT NULL,

                course_id INT NOT NULL,

                semester INT NOT NULL,

                internal_marks DOUBLE,

                external_marks DOUBLE,

                total_marks DOUBLE,

                grade VARCHAR(5),

                FOREIGN KEY(student_id)

                REFERENCES students(student_id)

                ON DELETE CASCADE,

                FOREIGN KEY(course_id)

                REFERENCES courses(course_id)

                ON DELETE CASCADE

            )

            """;

        execute(sql);

    }
    private static void createIndexes() {

        execute("""

            CREATE INDEX idx_student_roll

            ON students(roll_no)

            """);

        execute("""

            CREATE INDEX idx_student_email

            ON students(email)

            """);

        execute("""

            CREATE INDEX idx_student_department

            ON students(department_id)

            """);

        execute("""

            CREATE INDEX idx_course_department

            ON courses(department_id)

            """);

        execute("""

            CREATE INDEX idx_faculty_department

            ON faculty(department_id)

            """);

        execute("""

            CREATE INDEX idx_attendance_student

            ON attendance(student_id)

            """);

        execute("""

            CREATE INDEX idx_attendance_course

            ON attendance(course_id)

            """);

        execute("""

            CREATE INDEX idx_attendance_date

            ON attendance(attendance_date)

            """);

        execute("""

            CREATE INDEX idx_marks_student

            ON marks(student_id)

            """);

        execute("""

            CREATE INDEX idx_marks_course

            ON marks(course_id)

            """);

    }
    private static void createStoredProcedures() {

        execute("DROP PROCEDURE IF EXISTS GetStudentsByDepartment");

        execute("""

            CREATE PROCEDURE GetStudentsByDepartment(

                IN deptId INT

            )

            BEGIN

                SELECT *

                FROM students

                WHERE department_id = deptId

                ORDER BY first_name,last_name;

            END

            """);



        execute("DROP PROCEDURE IF EXISTS GetStudentsByCourse");

        execute("""

            CREATE PROCEDURE GetStudentsByCourse(

                IN courseId INT

            )

            BEGIN

                SELECT s.*

                FROM students s

                INNER JOIN marks m

                ON s.student_id=m.student_id

                WHERE m.course_id=courseId;

            END

            """);



        execute("DROP PROCEDURE IF EXISTS GetAttendanceByDate");

        execute("""

            CREATE PROCEDURE GetAttendanceByDate(

                IN attDate DATE

            )

            BEGIN

                SELECT *

                FROM attendance

                WHERE attendance_date=attDate;

            END

            """);



        execute("DROP PROCEDURE IF EXISTS GetTopStudents");

        execute("""

            CREATE PROCEDURE GetTopStudents()

            BEGIN

                SELECT

                    student_id,

                    AVG(total_marks) average_marks

                FROM marks

                GROUP BY student_id

                ORDER BY average_marks DESC

                LIMIT 10;

            END

            """);



        execute("DROP PROCEDURE IF EXISTS GetDepartmentStatistics");

        execute("""

            CREATE PROCEDURE GetDepartmentStatistics()

            BEGIN

                SELECT

                    d.department_name,

                    COUNT(s.student_id) total_students

                FROM departments d

                LEFT JOIN students s

                ON d.department_id=s.department_id

                GROUP BY d.department_id;

            END

            """);

    }

}