package org.example.dao.impl;

import org.example.config.ConnectionPool;
import org.example.dao.StudentDAO;
import org.example.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean addStudent(Student student) {

        String sql = """
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

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = ConnectionPool.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setString(1, student.getRollNo());

            preparedStatement.setString(2, student.getFirstName());

            preparedStatement.setString(3, student.getLastName());

            preparedStatement.setString(4, student.getGender());

            preparedStatement.setDate(
                    5,
                    Date.valueOf(student.getDob())
            );

            preparedStatement.setString(6, student.getEmail());

            preparedStatement.setString(7, student.getPhone());

            preparedStatement.setString(8, student.getAddress());

            preparedStatement.setInt(9, student.getDepartmentId());

            preparedStatement.setDate(
                    10,
                    Date.valueOf(student.getAdmissionDate())
            );

            preparedStatement.setString(11, student.getStatus());

            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {

                ResultSet generatedKeys =
                        preparedStatement.getGeneratedKeys();

                if (generatedKeys.next()) {

                    student.setStudentId(generatedKeys.getInt(1));

                }

                connection.commit();

                return true;
            }

            connection.rollback();

        }
        catch (Exception e) {

            try {

                if (connection != null)
                    connection.rollback();

            }
            catch (SQLException ex) {

                ex.printStackTrace();

            }

            e.printStackTrace();
        }
        finally {

            try {

                if (preparedStatement != null)
                    preparedStatement.close();

                if (connection != null)
                    connection.close();

            }
            catch (SQLException e) {

                e.printStackTrace();

            }

        }

        return false;

    }

    @Override
    public boolean updateStudent(Student student) {

        String sql = """
            UPDATE students
            SET
                roll_no = ?,
                first_name = ?,
                last_name = ?,
                gender = ?,
                dob = ?,
                email = ?,
                phone = ?,
                address = ?,
                department_id = ?,
                admission_date = ?,
                status = ?
            WHERE student_id = ?
            """;

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = ConnectionPool.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, student.getRollNo());

            preparedStatement.setString(2, student.getFirstName());

            preparedStatement.setString(3, student.getLastName());

            preparedStatement.setString(4, student.getGender());

            preparedStatement.setDate(5,
                    Date.valueOf(student.getDob()));

            preparedStatement.setString(6,
                    student.getEmail());

            preparedStatement.setString(7,
                    student.getPhone());

            preparedStatement.setString(8,
                    student.getAddress());

            preparedStatement.setInt(9,
                    student.getDepartmentId());

            preparedStatement.setDate(10,
                    Date.valueOf(student.getAdmissionDate()));

            preparedStatement.setString(11,
                    student.getStatus());

            preparedStatement.setInt(12,
                    student.getStudentId());

            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {

                connection.commit();

                return true;

            }

            connection.rollback();

        } catch (Exception e) {

            try {

                if (connection != null) {

                    connection.rollback();

                }

            } catch (SQLException ex) {

                ex.printStackTrace();

            }

            e.printStackTrace();

        } finally {

            try {

                if (preparedStatement != null)
                    preparedStatement.close();

                if (connection != null)
                    connection.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

        return false;

    }

    @Override
    public boolean deleteStudent(int studentId) {

        String sql = """
            DELETE FROM students
            WHERE student_id = ?
            """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {

            preparedStatement.setInt(1, studentId);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public Student getStudentById(int studentId) {

        String sql = """
            SELECT *
            FROM students
            WHERE student_id = ?
            """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {

            preparedStatement.setInt(1, studentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    Student student = new Student();

                    student.setStudentId(resultSet.getInt("student_id"));
                    student.setRollNo(resultSet.getString("roll_no"));
                    student.setFirstName(resultSet.getString("first_name"));
                    student.setLastName(resultSet.getString("last_name"));
                    student.setGender(resultSet.getString("gender"));

                    Date dob = resultSet.getDate("dob");
                    if (dob != null) {
                        student.setDob(dob.toLocalDate());
                    }

                    student.setEmail(resultSet.getString("email"));
                    student.setPhone(resultSet.getString("phone"));
                    student.setAddress(resultSet.getString("address"));
                    student.setDepartmentId(resultSet.getInt("department_id"));

                    Date admissionDate = resultSet.getDate("admission_date");
                    if (admissionDate != null) {
                        student.setAdmissionDate(admissionDate.toLocalDate());
                    }

                    student.setStatus(resultSet.getString("status"));

                    return student;
                }

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }
    @Override
    public Student getStudentByRollNo(String rollNo) {

        String sql = """
            SELECT *
            FROM students
            WHERE roll_no = ?
            """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {

            preparedStatement.setString(1, rollNo);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    return mapResultSetToStudent(resultSet);

                }

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;

    }

    @Override
    public List<Student> getAllStudents() {

        List<Student> studentList = new ArrayList<>();

        String sql = """
            SELECT *
            FROM students
            ORDER BY student_id
            """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            while (resultSet.next()) {

                Student student = new Student();

                student.setStudentId(resultSet.getInt("student_id"));

                student.setRollNo(resultSet.getString("roll_no"));

                student.setFirstName(resultSet.getString("first_name"));

                student.setLastName(resultSet.getString("last_name"));

                student.setGender(resultSet.getString("gender"));

                Date dob = resultSet.getDate("dob");
                if (dob != null) {
                    student.setDob(dob.toLocalDate());
                }

                student.setEmail(resultSet.getString("email"));

                student.setPhone(resultSet.getString("phone"));

                student.setAddress(resultSet.getString("address"));

                student.setDepartmentId(resultSet.getInt("department_id"));

                Date admissionDate = resultSet.getDate("admission_date");
                if (admissionDate != null) {
                    student.setAdmissionDate(admissionDate.toLocalDate());
                }

                student.setStatus(resultSet.getString("status"));

                studentList.add(student);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return studentList;

    }

    @Override
    public List<Student> searchStudents(String keyword) {

        List<Student> studentList = new ArrayList<>();

        String sql = """
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

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {

            String search = "%" + keyword + "%";

            preparedStatement.setString(1, search);
            preparedStatement.setString(2, search);
            preparedStatement.setString(3, search);
            preparedStatement.setString(4, search);
            preparedStatement.setString(5, search);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    studentList.add(mapResultSetToStudent(resultSet));

                }

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return studentList;

    }

    @Override
    public boolean studentExists(String rollNo) {

        String sql = """
            SELECT COUNT(*)
            FROM students
            WHERE roll_no = ?
            """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {

            preparedStatement.setString(1, rollNo);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    return resultSet.getInt(1) > 0;

                }

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }
    private Student mapResultSetToStudent(ResultSet resultSet) throws SQLException {

        Student student = new Student();

        student.setStudentId(resultSet.getInt("student_id"));
        student.setRollNo(resultSet.getString("roll_no"));
        student.setFirstName(resultSet.getString("first_name"));
        student.setLastName(resultSet.getString("last_name"));
        student.setGender(resultSet.getString("gender"));

        Date dob = resultSet.getDate("dob");

        if (dob != null) {

            student.setDob(dob.toLocalDate());

        }

        student.setEmail(resultSet.getString("email"));
        student.setPhone(resultSet.getString("phone"));
        student.setAddress(resultSet.getString("address"));
        student.setDepartmentId(resultSet.getInt("department_id"));

        Date admissionDate = resultSet.getDate("admission_date");

        if (admissionDate != null) {

            student.setAdmissionDate(admissionDate.toLocalDate());

        }

        student.setStatus(resultSet.getString("status"));

        return student;

    }

}