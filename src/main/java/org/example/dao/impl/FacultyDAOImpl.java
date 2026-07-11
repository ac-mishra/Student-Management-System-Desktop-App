package org.example.dao.impl;

import org.example.config.ConnectionPool;
import org.example.dao.FacultyDAO;
import org.example.model.Faculty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultyDAOImpl implements FacultyDAO {

    @Override
    public boolean addFaculty(Faculty faculty) {

        String sql = """
                INSERT INTO faculty
                (
                    faculty_name,
                    email,
                    phone,
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

            ps.setString(1, faculty.getFacultyName());
            ps.setString(2, faculty.getEmail());
            ps.setString(3, faculty.getPhone());
            ps.setInt(4, faculty.getDepartmentId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public boolean updateFaculty(Faculty faculty) {

        String sql = """
                UPDATE faculty
                SET
                    faculty_name=?,
                    email=?,
                    phone=?,
                    department_id=?
                WHERE faculty_id=?
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, faculty.getFacultyName());
            ps.setString(2, faculty.getEmail());
            ps.setString(3, faculty.getPhone());
            ps.setInt(4, faculty.getDepartmentId());
            ps.setInt(5, faculty.getFacultyId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public boolean deleteFaculty(int facultyId) {

        String sql = """
                DELETE FROM faculty
                WHERE faculty_id=?
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, facultyId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public Faculty getFacultyById(int facultyId) {

        String sql = """
                SELECT *
                FROM faculty
                WHERE faculty_id=?
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, facultyId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return mapFaculty(rs);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public Faculty getFacultyByEmail(String email) {

        String sql = """
                SELECT *
                FROM faculty
                WHERE email=?
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return mapFaculty(rs);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public List<Faculty> getAllFaculty() {

        List<Faculty> list = new ArrayList<>();

        String sql = """
                SELECT *
                FROM faculty
                ORDER BY faculty_name
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                list.add(mapFaculty(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return list;
    }

    @Override
    public List<Faculty> searchFaculty(String keyword) {

        List<Faculty> list = new ArrayList<>();

        String sql = """
                SELECT *
                FROM faculty
                WHERE faculty_name LIKE ?
                OR email LIKE ?
                OR phone LIKE ?
                ORDER BY faculty_name
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            String search = "%" + keyword + "%";

            ps.setString(1, search);
            ps.setString(2, search);
            ps.setString(3, search);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(mapFaculty(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return list;
    }

    @Override
    public boolean facultyExists(String email) {

        String sql = """
                SELECT COUNT(*)
                FROM faculty
                WHERE email=?
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1) > 0;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    private Faculty mapFaculty(ResultSet rs) throws SQLException {

        Faculty faculty = new Faculty();

        faculty.setFacultyId(rs.getInt("faculty_id"));
        faculty.setFacultyName(rs.getString("faculty_name"));
        faculty.setEmail(rs.getString("email"));
        faculty.setPhone(rs.getString("phone"));
        faculty.setDepartmentId(rs.getInt("department_id"));

        return faculty;
    }

}