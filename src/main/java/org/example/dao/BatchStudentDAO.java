package org.example.dao;

import org.example.config.ConnectionPool;
import org.example.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BatchStudentDAO {

    public boolean batchInsert(List<Student> students) {

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
                    admission_date,
                    status,
                    department_id
                )
                VALUES
                (
                    ?,?,?,?,?,?,?,?,?,?,?
                )
                """;

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)

        ) {

            connection.setAutoCommit(false);

            for (Student student : students) {

                ps.setString(1, student.getRollNo());

                ps.setString(2, student.getFirstName());

                ps.setString(3, student.getLastName());

                ps.setString(4, student.getGender());

                ps.setObject(5, student.getDob());

                ps.setString(6, student.getEmail());

                ps.setString(7, student.getPhone());

                ps.setString(8, student.getAddress());

                ps.setObject(9, student.getAdmissionDate());

                ps.setString(10, student.getStatus());

                ps.setInt(11, student.getDepartmentId());

                ps.addBatch();

            }

            ps.executeBatch();

            connection.commit();

            return true;

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    public boolean batchUpdate(List<Student> students) {

        String sql = """
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
                    admission_date=?,
                    status=?,
                    department_id=?
                WHERE student_id=?
                """;

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)

        ) {

            connection.setAutoCommit(false);

            for (Student student : students) {

                ps.setString(1, student.getRollNo());

                ps.setString(2, student.getFirstName());

                ps.setString(3, student.getLastName());

                ps.setString(4, student.getGender());

                ps.setObject(5, student.getDob());

                ps.setString(6, student.getEmail());

                ps.setString(7, student.getPhone());

                ps.setString(8, student.getAddress());

                ps.setObject(9, student.getAdmissionDate());

                ps.setString(10, student.getStatus());

                ps.setInt(11, student.getDepartmentId());

                ps.setInt(12, student.getStudentId());

                ps.addBatch();

            }

            ps.executeBatch();

            connection.commit();

            return true;

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    public boolean batchDelete(List<Integer> studentIds) {

        String sql =
                "DELETE FROM students WHERE student_id=?";

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)

        ) {

            connection.setAutoCommit(false);

            for (Integer id : studentIds) {

                ps.setInt(1, id);

                ps.addBatch();

            }

            ps.executeBatch();

            connection.commit();

            return true;

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

}