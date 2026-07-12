package org.example.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.config.ConnectionPool;
import org.example.dao.DepartmentDAO;
import org.example.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {

    private static final Logger logger =
            LogManager.getLogger(DepartmentDAOImpl.class);

    private static final String INSERT_DEPARTMENT = """
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

    private static final String UPDATE_DEPARTMENT = """
            UPDATE departments
            SET
                department_name=?,
                department_code=?,
                description=?
            WHERE department_id=?
            """;

    private static final String DELETE_DEPARTMENT = """
            DELETE FROM departments
            WHERE department_id=?
            """;

    private static final String GET_BY_ID = """
            SELECT *
            FROM departments
            WHERE department_id=?
            """;

    private static final String GET_BY_CODE = """
            SELECT *
            FROM departments
            WHERE department_code=?
            """;

    private static final String GET_ALL = """
            SELECT *
            FROM departments
            ORDER BY department_name
            """;

    private static final String SEARCH = """
            SELECT *
            FROM departments
            WHERE
                department_name LIKE ?
                OR department_code LIKE ?
            ORDER BY department_name
            """;

    private static final String EXISTS = """
            SELECT COUNT(*)
            FROM departments
            WHERE department_code=?
            """;

    @Override
    public boolean addDepartment(Department department) {

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                INSERT_DEPARTMENT,
                                Statement.RETURN_GENERATED_KEYS
                        )

        ) {

            connection.setAutoCommit(false);

            ps.setString(
                    1,
                    department.getDepartmentName()
            );

            ps.setString(
                    2,
                    department.getDepartmentCode()
            );

            ps.setString(
                    3,
                    department.getDescription()
            );

            int rows =
                    ps.executeUpdate();

            if (rows > 0) {

                try (ResultSet rs =
                             ps.getGeneratedKeys()) {

                    if (rs.next()) {

                        department.setDepartmentId(
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
                    "Unable to add department.",
                    e
            );

        }

        return false;

    }
    @Override
    public boolean updateDepartment(Department department) {

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                UPDATE_DEPARTMENT
                        )

        ) {

            connection.setAutoCommit(false);

            ps.setString(
                    1,
                    department.getDepartmentName()
            );

            ps.setString(
                    2,
                    department.getDepartmentCode()
            );

            ps.setString(
                    3,
                    department.getDescription()
            );

            ps.setInt(
                    4,
                    department.getDepartmentId()
            );

            int rows =
                    ps.executeUpdate();

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
                    "Unable to update department.",
                    e
            );

        }

        return false;

    }

    @Override
    public boolean deleteDepartment(int departmentId) {

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                DELETE_DEPARTMENT
                        )

        ) {

            ps.setInt(
                    1,
                    departmentId
            );

            return ps.executeUpdate() > 0;

        }

        catch (SQLException e) {

            logger.error(
                    "Unable to delete department.",
                    e
            );

        }

        return false;

    }
    @Override
    public Department getDepartmentById(int departmentId) {

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                GET_BY_ID
                        )

        ) {

            ps.setInt(
                    1,
                    departmentId
            );

            try (ResultSet rs =
                         ps.executeQuery()) {

                if (rs.next()) {

                    return mapDepartment(rs);

                }

            }

        }

        catch (SQLException e) {

            logger.error(
                    "Unable to fetch department by id.",
                    e
            );

        }

        return null;

    }

    @Override
    public Department getDepartmentByCode(String departmentCode) {

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                GET_BY_CODE
                        )

        ) {

            ps.setString(
                    1,
                    departmentCode
            );

            try (ResultSet rs =
                         ps.executeQuery()) {

                if (rs.next()) {

                    return mapDepartment(rs);

                }

            }

        }

        catch (SQLException e) {

            logger.error(
                    "Unable to fetch department by code.",
                    e
            );

        }

        return null;

    }

    @Override
    public List<Department> getAllDepartments() {

        List<Department> departments =
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

                departments.add(
                        mapDepartment(rs)
                );

            }

        }

        catch (SQLException e) {

            logger.error(
                    "Unable to load departments.",
                    e
            );

        }

        return departments;

    }
    @Override
    public List<Department> searchDepartments(String keyword) {

        List<Department> departments =
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

            try (ResultSet rs =
                         ps.executeQuery()) {

                while (rs.next()) {

                    departments.add(
                            mapDepartment(rs)
                    );

                }

            }

        }

        catch (SQLException e) {

            logger.error(
                    "Unable to search departments.",
                    e
            );

        }

        return departments;

    }

    @Override
    public boolean departmentExists(String departmentCode) {

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                EXISTS
                        )

        ) {

            ps.setString(
                    1,
                    departmentCode
            );

            try (ResultSet rs =
                         ps.executeQuery()) {

                if (rs.next()) {

                    return rs.getInt(1) > 0;

                }

            }

        }

        catch (SQLException e) {

            logger.error(
                    "Unable to check whether department exists.",
                    e
            );

        }

        return false;

    }

    private Department mapDepartment(ResultSet rs)
            throws SQLException {

        Department department =
                new Department();

        department.setDepartmentId(
                rs.getInt("department_id")
        );

        department.setDepartmentName(
                rs.getString("department_name")
        );

        department.setDepartmentCode(
                rs.getString("department_code")
        );

        department.setDescription(
                rs.getString("description")
        );

        return department;

    }

}