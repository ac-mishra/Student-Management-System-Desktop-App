package org.example.dao.impl;

import org.example.config.ConnectionPool;
import org.example.dao.EnrollmentDAO;
import org.example.model.Enrollment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImpl implements EnrollmentDAO {

    @Override
    public boolean enrollStudent(Enrollment enrollment) {

        String sql = """
                INSERT INTO enrollments
                (
                    student_id,
                    course_id,
                    enrollment_date
                )
                VALUES
                (
                    ?,?,?
                )
                """;

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = ConnectionPool.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, enrollment.getStudentId());
            preparedStatement.setInt(2, enrollment.getCourseId());
            preparedStatement.setDate(3, Date.valueOf(enrollment.getEnrollmentDate()));

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
    public boolean updateEnrollment(Enrollment enrollment) {

        String sql = """
                UPDATE enrollments
                SET
                    student_id=?,
                    course_id=?,
                    enrollment_date=?
                WHERE enrollment_id=?
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, enrollment.getStudentId());
            ps.setInt(2, enrollment.getCourseId());
            ps.setDate(3, Date.valueOf(enrollment.getEnrollmentDate()));
            ps.setInt(4, enrollment.getEnrollmentId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public boolean deleteEnrollment(int enrollmentId) {

        String sql = "DELETE FROM enrollments WHERE enrollment_id=?";

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, enrollmentId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public Enrollment getEnrollmentById(int enrollmentId) {

        String sql = "SELECT * FROM enrollments WHERE enrollment_id=?";

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, enrollmentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return mapEnrollment(rs);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public List<Enrollment> getAllEnrollments() {

        List<Enrollment> list = new ArrayList<>();

        String sql = "SELECT * FROM enrollments ORDER BY enrollment_id";

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                list.add(mapEnrollment(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return list;
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudent(int studentId) {

        List<Enrollment> list = new ArrayList<>();

        String sql = "SELECT * FROM enrollments WHERE student_id=?";

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(mapEnrollment(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return list;
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourse(int courseId) {

        List<Enrollment> list = new ArrayList<>();

        String sql = "SELECT * FROM enrollments WHERE course_id=?";

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, courseId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(mapEnrollment(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return list;
    }

    @Override
    public boolean isStudentEnrolled(int studentId, int courseId) {

        String sql = """
                SELECT COUNT(*)
                FROM enrollments
                WHERE student_id=?
                AND course_id=?
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1) > 0;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    private Enrollment mapEnrollment(ResultSet rs) throws SQLException {

        Enrollment enrollment = new Enrollment();

        enrollment.setEnrollmentId(rs.getInt("enrollment_id"));
        enrollment.setStudentId(rs.getInt("student_id"));
        enrollment.setCourseId(rs.getInt("course_id"));
        enrollment.setEnrollmentDate(rs.getDate("enrollment_date").toLocalDate());

        return enrollment;
    }

}