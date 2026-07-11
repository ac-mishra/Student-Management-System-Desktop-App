package org.example.dao.impl;

import org.example.config.ConnectionPool;
import org.example.dao.DepartmentDAO;
import org.example.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {

    @Override
    public boolean addDepartment(Department department) {

        String sql = """
                INSERT INTO departments
                (
                    department_name,
                    department_code,
                    description
                )
                VALUES
                (
                    ?,?,?
                )
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, department.getDepartmentName());
            ps.setString(2, department.getDepartmentCode());
            ps.setString(3, department.getDescription());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public boolean updateDepartment(Department department) {

        String sql = """
                UPDATE departments
                SET
                    department_name=?,
                    department_code=?,
                    description=?
                WHERE department_id=?
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, department.getDepartmentName());
            ps.setString(2, department.getDepartmentCode());
            ps.setString(3, department.getDescription());
            ps.setInt(4, department.getDepartmentId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public boolean deleteDepartment(int departmentId) {

        String sql = """
                DELETE FROM departments
                WHERE department_id=?
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, departmentId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public Department getDepartmentById(int departmentId) {

        String sql = """
                SELECT *
                FROM departments
                WHERE department_id=?
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, departmentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return mapDepartment(rs);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public Department getDepartmentByCode(String departmentCode) {

        String sql = """
                SELECT *
                FROM departments
                WHERE department_code=?
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, departmentCode);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return mapDepartment(rs);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public List<Department> getAllDepartments() {

        List<Department> list = new ArrayList<>();

        String sql = """
                SELECT *
                FROM departments
                ORDER BY department_name
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                list.add(mapDepartment(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return list;
    }

    @Override
    public List<Department> searchDepartments(String keyword) {

        List<Department> list = new ArrayList<>();

        String sql = """
                SELECT *
                FROM departments
                WHERE department_name LIKE ?
                OR department_code LIKE ?
                ORDER BY department_name
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

                list.add(mapDepartment(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return list;
    }

    @Override
    public boolean departmentExists(String departmentCode) {

        String sql = """
                SELECT COUNT(*)
                FROM departments
                WHERE department_code=?
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, departmentCode);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1) > 0;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    private Department mapDepartment(ResultSet rs) throws SQLException {

        Department department = new Department();

        department.setDepartmentId(rs.getInt("department_id"));
        department.setDepartmentName(rs.getString("department_name"));
        department.setDepartmentCode(rs.getString("department_code"));
        department.setDescription(rs.getString("description"));

        return department;
    }

}