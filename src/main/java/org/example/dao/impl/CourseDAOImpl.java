package org.example.dao.impl;

import org.example.config.ConnectionPool;
import org.example.dao.CourseDAO;
import org.example.model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    @Override
    public boolean addCourse(Course course) {

        String sql = """
                INSERT INTO courses
                (
                    course_code,
                    course_name,
                    credits,
                    department_id
                )
                VALUES
                (
                    ?,?,?,?
                )
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, course.getCourseCode());
            ps.setString(2, course.getCourseName());
            ps.setInt(3, course.getCredits());
            ps.setInt(4, course.getDepartmentId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public boolean updateCourse(Course course) {

        String sql = """
                UPDATE courses
                SET
                    course_code=?,
                    course_name=?,
                    credits=?,
                    department_id=?
                WHERE course_id=?
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, course.getCourseCode());
            ps.setString(2, course.getCourseName());
            ps.setInt(3, course.getCredits());
            ps.setInt(4, course.getDepartmentId());
            ps.setInt(5, course.getCourseId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public boolean deleteCourse(int courseId) {

        String sql = """
                DELETE FROM courses
                WHERE course_id=?
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, courseId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public Course getCourseById(int courseId) {

        String sql = """
                SELECT *
                FROM courses
                WHERE course_id=?
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, courseId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return mapCourse(rs);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public Course getCourseByCode(String courseCode) {

        String sql = """
                SELECT *
                FROM courses
                WHERE course_code=?
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, courseCode);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return mapCourse(rs);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public List<Course> getAllCourses() {

        List<Course> list = new ArrayList<>();

        String sql = """
                SELECT *
                FROM courses
                ORDER BY course_name
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                list.add(mapCourse(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return list;
    }

    @Override
    public List<Course> searchCourses(String keyword) {

        List<Course> list = new ArrayList<>();

        String sql = """
                SELECT *
                FROM courses
                WHERE course_name LIKE ?
                OR course_code LIKE ?
                ORDER BY course_name
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            String search = "%" + keyword + "%";

            ps.setString(1, search);
            ps.setString(2, search);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(mapCourse(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return list;
    }

    @Override
    public boolean courseExists(String courseCode) {

        String sql = """
                SELECT COUNT(*)
                FROM courses
                WHERE course_code=?
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, courseCode);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1) > 0;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    private Course mapCourse(ResultSet rs) throws SQLException {

        Course course = new Course();

        course.setCourseId(rs.getInt("course_id"));
        course.setCourseCode(rs.getString("course_code"));
        course.setCourseName(rs.getString("course_name"));
        course.setCredits(rs.getInt("credits"));
        course.setDepartmentId(rs.getInt("department_id"));

        return course;

    }

}