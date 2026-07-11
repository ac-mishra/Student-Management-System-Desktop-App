package org.example.dao.impl;

import org.example.config.ConnectionPool;
import org.example.dao.AttendanceDAO;
import org.example.model.Attendance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAOImpl implements AttendanceDAO {

    @Override
    public boolean addAttendance(Attendance attendance) {

        String sql = """
                INSERT INTO attendance
                (
                    student_id,
                    course_id,
                    attendance_date,
                    status
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

            ps.setInt(1, attendance.getStudentId());
            ps.setInt(2, attendance.getCourseId());
            ps.setDate(3, Date.valueOf(attendance.getAttendanceDate()));
            ps.setString(4, attendance.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public boolean updateAttendance(Attendance attendance) {

        String sql = """
                UPDATE attendance
                SET
                    student_id=?,
                    course_id=?,
                    attendance_date=?,
                    status=?
                WHERE attendance_id=?
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, attendance.getStudentId());
            ps.setInt(2, attendance.getCourseId());
            ps.setDate(3, Date.valueOf(attendance.getAttendanceDate()));
            ps.setString(4, attendance.getStatus());
            ps.setInt(5, attendance.getAttendanceId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public boolean deleteAttendance(int attendanceId) {

        String sql = "DELETE FROM attendance WHERE attendance_id=?";

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, attendanceId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public Attendance getAttendanceById(int attendanceId) {

        String sql = "SELECT * FROM attendance WHERE attendance_id=?";

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, attendanceId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return mapAttendance(rs);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public List<Attendance> getAllAttendance() {

        List<Attendance> list = new ArrayList<>();

        String sql = "SELECT * FROM attendance ORDER BY attendance_date DESC";

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                list.add(mapAttendance(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return list;
    }

    @Override
    public List<Attendance> getAttendanceByStudent(int studentId) {

        List<Attendance> list = new ArrayList<>();

        String sql = "SELECT * FROM attendance WHERE student_id=?";

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(mapAttendance(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return list;
    }

    @Override
    public List<Attendance> getAttendanceByCourse(int courseId) {

        List<Attendance> list = new ArrayList<>();

        String sql = "SELECT * FROM attendance WHERE course_id=?";

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, courseId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(mapAttendance(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return list;
    }

    private Attendance mapAttendance(ResultSet rs) throws SQLException {

        Attendance attendance = new Attendance();

        attendance.setAttendanceId(rs.getInt("attendance_id"));
        attendance.setStudentId(rs.getInt("student_id"));
        attendance.setCourseId(rs.getInt("course_id"));
        attendance.setAttendanceDate(rs.getDate("attendance_date").toLocalDate());
        attendance.setStatus(rs.getString("status"));

        return attendance;
    }

}