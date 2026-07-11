package org.example.dao.impl;

import org.example.config.ConnectionPool;
import org.example.dao.MarksDAO;
import org.example.model.Marks;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarksDAOImpl implements MarksDAO {

    @Override
    public boolean addMarks(Marks marks) {

        String sql = """
                INSERT INTO marks
                (
                    student_id,
                    course_id,
                    semester,
                    internal_marks,
                    external_marks,
                    total_marks,
                    grade
                )
                VALUES
                (
                    ?,?,?,?,?,?,?
                )
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, marks.getStudentId());
            ps.setInt(2, marks.getCourseId());
            ps.setInt(3, marks.getSemester());
            ps.setDouble(4, marks.getInternalMarks());
            ps.setDouble(5, marks.getExternalMarks());
            ps.setDouble(6, marks.getTotalMarks());
            ps.setString(7, marks.getGrade());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }
    @Override
    public boolean updateMarks(Marks marks) {

        String sql = """
                UPDATE marks
                SET
                    student_id=?,
                    course_id=?,
                    semester=?,
                    internal_marks=?,
                    external_marks=?,
                    total_marks=?,
                    grade=?
                WHERE marks_id=?
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, marks.getStudentId());
            ps.setInt(2, marks.getCourseId());
            ps.setInt(3, marks.getSemester());
            ps.setDouble(4, marks.getInternalMarks());
            ps.setDouble(5, marks.getExternalMarks());
            ps.setDouble(6, marks.getTotalMarks());
            ps.setString(7, marks.getGrade());
            ps.setInt(8, marks.getMarksId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }
    @Override
    public boolean deleteMarks(int marksId) {

        String sql = "DELETE FROM marks WHERE marks_id=?";

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, marksId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

    @Override
    public Marks getMarksById(int marksId) {

        String sql = "SELECT * FROM marks WHERE marks_id=?";

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, marksId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return mapMarks(rs);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;

    }
    @Override
    public List<Marks> getAllMarks() {

        List<Marks> marksList = new ArrayList<>();

        String sql = """
                SELECT *
                FROM marks
                ORDER BY marks_id DESC
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                marksList.add(
                        mapMarks(rs)
                );

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return marksList;

    }
    @Override
    public List<Marks> getMarksByStudent(int studentId) {

        List<Marks> marksList = new ArrayList<>();

        String sql = """
                SELECT *
                FROM marks
                WHERE student_id = ?
                ORDER BY semester
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                marksList.add(
                        mapMarks(rs)
                );

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return marksList;

    }
    @Override
    public List<Marks> getMarksByCourse(int courseId) {

        List<Marks> marksList = new ArrayList<>();

        String sql = """
                SELECT *
                FROM marks
                WHERE course_id = ?
                ORDER BY semester
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, courseId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                marksList.add(
                        mapMarks(rs)
                );

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return marksList;

    }
    private Marks mapMarks(ResultSet rs)
            throws SQLException {

        Marks marks = new Marks();

        marks.setMarksId(
                rs.getInt("marks_id")
        );

        marks.setStudentId(
                rs.getInt("student_id")
        );

        marks.setCourseId(
                rs.getInt("course_id")
        );

        marks.setSemester(
                rs.getInt("semester")
        );

        marks.setInternalMarks(
                rs.getDouble("internal_marks")
        );

        marks.setExternalMarks(
                rs.getDouble("external_marks")
        );

        marks.setTotalMarks(
                rs.getDouble("total_marks")
        );

        marks.setGrade(
                rs.getString("grade")
        );

        return marks;

    }

}