package org.example.dao;

import org.example.config.ConnectionPool;
import org.example.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentProcedureDAO {

    public List<Student> getStudentsByDepartment(int departmentId) {

        List<Student> students = new ArrayList<>();

        String sql =
                "{CALL GetStudentsByDepartment(?)}";

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                CallableStatement cs =
                        connection.prepareCall(sql)

        ) {

            cs.setInt(1, departmentId);

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {

                students.add(mapStudent(rs));

            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return students;

    }

    public List<Student> getStudentsByCourse(int courseId) {

        List<Student> students = new ArrayList<>();

        String sql =
                "{CALL GetStudentsByCourse(?)}";

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                CallableStatement cs =
                        connection.prepareCall(sql)

        ) {

            cs.setInt(1, courseId);

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {

                students.add(mapStudent(rs));

            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return students;

    }

    public List<Student> getTopStudents() {

        List<Student> students = new ArrayList<>();

        String sql =
                "{CALL GetTopStudents()}";

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                CallableStatement cs =
                        connection.prepareCall(sql)

        ) {

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {

                Student student = new Student();

                student.setStudentId(
                        rs.getInt("student_id")
                );

                students.add(student);

            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return students;

    }

    private Student mapStudent(ResultSet rs)
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

        student.setDob(
                rs.getDate("dob").toLocalDate()
        );

        student.setEmail(
                rs.getString("email")
        );

        student.setPhone(
                rs.getString("phone")
        );

        student.setAddress(
                rs.getString("address")
        );

        student.setAdmissionDate(
                rs.getDate("admission_date").toLocalDate()
        );

        student.setStatus(
                rs.getString("status")
        );

        student.setDepartmentId(
                rs.getInt("department_id")
        );

        return student;

    }

}