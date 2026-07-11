package org.example.dao.impl;

import org.example.config.ConnectionPool;
import org.example.dao.UserDAO;
import org.example.model.User;
import org.example.util.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean registerUser(User user) {

        String sql = """
                INSERT INTO users
                (
                    username,
                    password,
                    role
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

            ps.setString(1, user.getUsername());
            ps.setString(
                    2,
                    PasswordUtil.hashPassword(user.getPassword())
            );            ps.setString(3, user.getRole());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public User login(String username, String password) {

        String sql = """
            SELECT *
            FROM users
            WHERE username=?
            """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String storedPassword = rs.getString("password");

                if (PasswordUtil.verifyPassword(password, storedPassword)) {

                    return mapUser(rs);

                }

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;

    }
    @Override
    public boolean updateUser(User user) {

        String sql = """
                UPDATE users
                SET
                    username=?,
                    password=?,
                    role=?
                WHERE user_id=?
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.setInt(4, user.getUserId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public boolean deleteUser(int userId) {

        String sql = "DELETE FROM users WHERE user_id=?";

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, userId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public User getUserById(int userId) {

        String sql = "SELECT * FROM users WHERE user_id=?";

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return mapUser(rs);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public User getUserByUsername(String username) {

        String sql = "SELECT * FROM users WHERE username=?";

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return mapUser(rs);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();

        String sql = "SELECT * FROM users ORDER BY username";

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                list.add(mapUser(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return list;
    }

    @Override
    public boolean userExists(String username) {

        String sql = "SELECT COUNT(*) FROM users WHERE username=?";

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1) > 0;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public boolean changePassword(int userId, String newPassword) {

        String sql = """
                UPDATE users
                SET password=?
                WHERE user_id=?
                """;

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, newPassword);
            ps.setInt(2, userId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    private User mapUser(ResultSet rs) throws SQLException {

        User user = new User();

        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));

        return user;
    }

}