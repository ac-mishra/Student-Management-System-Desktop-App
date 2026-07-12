package org.example.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.config.ConnectionPool;
import org.example.dao.StudentDAO;
import org.example.model.Student;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private static final Logger logger =
            LogManager.getLogger(StudentDAOImpl.class);

    private static final String INSERT_STUDENT = """
            INSERT INTO students
            (
                roll_no,
                first_name,
                last_name,
                gender,
                dob,
                email,
                phone,
                address,
                department_id,
                admission_date,
                status
            )
            VALUES
            (
                ?,?,?,?,?,?,?,?,?,?,?
            )
            """;

    private static final String UPDATE_STUDENT = """
            UPDATE students
            SET
                roll_no=?,
                first_name=?,
                last_name=?,
                gender=?,
                dob=?,
                email=?,
                phone=?,
                address=?,
                department_id=?,
                admission_date=?,
                status=?
            WHERE student_id=?
            """;

    private static final String DELETE_STUDENT = """
            DELETE FROM students
            WHERE student_id=?
            """;

    private static final String GET_BY_ID = """
            SELECT *
            FROM students
            WHERE student_id=?
            """;

    private static final String GET_BY_ROLL = """
            SELECT *
            FROM students
            WHERE roll_no=?
            """;

    private static final String GET_ALL = """
            SELECT *
            FROM students
            ORDER BY student_id
            """;

    private static final String SEARCH = """
            SELECT *
            FROM students
            WHERE
                roll_no LIKE ?
                OR first_name LIKE ?
                OR last_name LIKE ?
                OR email LIKE ?
                OR phone LIKE ?
            ORDER BY student_id
            """;

    private static final String EXISTS = """
            SELECT COUNT(*)
            FROM students
            WHERE roll_no=?
            """;

    @Override
    public boolean addStudent(Student student) {

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                INSERT_STUDENT,
                                Statement.RETURN_GENERATED_KEYS
                        )

        ) {

            connection.setAutoCommit(false);

            ps.setString(1, student.getRollNo());

            ps.setString(2, student.getFirstName());

            ps.setString(3, student.getLastName());

            ps.setString(4, student.getGender());

            if (student.getDob() != null) {

                ps.setDate(
                        5,
                        Date.valueOf(student.getDob())
                );

            } else {

                ps.setNull(
                        5,
                        Types.DATE
                );

            }

            ps.setString(6, student.getEmail());

            ps.setString(7, student.getPhone());

            ps.setString(8, student.getAddress());

            ps.setInt(9, student.getDepartmentId());

            if (student.getAdmissionDate() != null) {

                ps.setDate(
                        10,
                        Date.valueOf(student.getAdmissionDate())
                );

            } else {

                ps.setNull(
                        10,
                        Types.DATE
                );

            }

            ps.setString(11, student.getStatus());

            int rows = ps.executeUpdate();

            if (rows > 0) {

                try (ResultSet rs =
                             ps.getGeneratedKeys()) {

                    if (rs.next()) {

                        student.setStudentId(
                                rs.getInt(1)
                        );

                    }

                }

                connection.commit();

                connection.setAutoCommit(true);

                return true;

            }

            connection.rollback();

            connection.setAutoCommit(true);

        }

        catch (Exception e) {

            logger.error(
                    "Unable to add student.",
                    e
            );

        }

        return false;

    }
    @Override
    public boolean updateStudent(Student student) {

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                UPDATE_STUDENT
                        )

        ) {

            connection.setAutoCommit(false);

            ps.setString(1, student.getRollNo());

            ps.setString(2, student.getFirstName());

            ps.setString(3, student.getLastName());

            ps.setString(4, student.getGender());

            if (student.getDob() != null) {

                ps.setDate(
                        5,
                        Date.valueOf(student.getDob())
                );

            } else {

                ps.setNull(
                        5,
                        Types.DATE
                );

            }

            ps.setString(6, student.getEmail());

            ps.setString(7, student.getPhone());

            ps.setString(8, student.getAddress());

            ps.setInt(9, student.getDepartmentId());

            if (student.getAdmissionDate() != null) {

                ps.setDate(
                        10,
                        Date.valueOf(student.getAdmissionDate())
                );

            } else {

                ps.setNull(
                        10,
                        Types.DATE
                );

            }

            ps.setString(11, student.getStatus());

            ps.setInt(12, student.getStudentId());

            int rows = ps.executeUpdate();

            if (rows > 0) {

                connection.commit();

                connection.setAutoCommit(true);

                return true;

            }

            connection.rollback();

            connection.setAutoCommit(true);

        }

        catch (Exception e) {

            logger.error(
                    "Unable to update student.",
                    e
            );

        }

        return false;

    }

    @Override
    public boolean deleteStudent(int studentId) {

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                DELETE_STUDENT
                        )

        ) {

            ps.setInt(1, studentId);

            return ps.executeUpdate() > 0;

        }

        catch (SQLException e) {

            logger.error(
                    "Unable to delete student.",
                    e
            );

        }

        return false;

    }

    @Override
    public Student getStudentById(int studentId) {

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                GET_BY_ID
                        )

        ) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return mapResultSetToStudent(rs);

                }

            }

        }

        catch (SQLException e) {

            logger.error(
                    "Unable to fetch student by id.",
                    e
            );

        }

        return null;

    }
    @Override
    public Student getStudentByRollNo(String rollNo) {

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                GET_BY_ROLL
                        )

        ) {

            ps.setString(1, rollNo);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return mapResultSetToStudent(rs);

                }

            }

        }

        catch (SQLException e) {

            logger.error(
                    "Unable to fetch student by roll number.",
                    e
            );

        }

        return null;

    }

    @Override
    public List<Student> getAllStudents() {

        List<Student> students =
                new ArrayList<>();

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                GET_ALL
                        );

                ResultSet rs =
                        ps.executeQuery()

        ) {

            while (rs.next()) {

                students.add(
                        mapResultSetToStudent(rs)
                );

            }

        }

        catch (SQLException e) {

            logger.error(
                    "Unable to load students.",
                    e
            );

        }

        return students;

    }

    @Override
    public List<Student> searchStudents(String keyword) {

        List<Student> students =
                new ArrayList<>();

        String search =
                "%" + keyword + "%";

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                SEARCH
                        )

        ) {

            ps.setString(1, search);

            ps.setString(2, search);

            ps.setString(3, search);

            ps.setString(4, search);

            ps.setString(5, search);

            try (ResultSet rs =
                         ps.executeQuery()) {

                while (rs.next()) {

                    students.add(
                            mapResultSetToStudent(rs)
                    );

                }

            }

        }

        catch (SQLException e) {

            logger.error(
                    "Unable to search students.",
                    e
            );

        }

        return students;

    }
    @Override
    public boolean studentExists(String rollNo) {

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                EXISTS
                        )

        ) {

            ps.setString(1, rollNo);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return rs.getInt(1) > 0;

                }

            }

        }

        catch (SQLException e) {

            logger.error(
                    "Unable to check whether student exists.",
                    e
            );

        }

        return false;

    }

    private Student mapResultSetToStudent(ResultSet rs)
            throws SQLException {

        Student student = new Student();

        student.setStudentId(
                rs.getInt("student_id")
        );

        student.setRollNo(
                rs.getString("roll_no")
        );

        student.setFirstName(
                rs.getString("first_name")
        );

        student.setLastName(
                rs.getString("last_name")
        );

        student.setGender(
                rs.getString("gender")
        );

        Date dob = rs.getDate("dob");

        if (dob != null) {

            student.setDob(
                    dob.toLocalDate()
            );

        }

        student.setEmail(
                rs.getString("email")
        );

        student.setPhone(
                rs.getString("phone")
        );

        student.setAddress(
                rs.getString("address")
        );

        student.setDepartmentId(
                rs.getInt("department_id")
        );

        Date admissionDate =
                rs.getDate("admission_date");

        if (admissionDate != null) {

            student.setAdmissionDate(
                    admissionDate.toLocalDate()
            );

        }

        student.setStatus(
                rs.getString("status")
        );

        return student;

    }

}