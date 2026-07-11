package org.example.service.impl;

import org.example.dao.UserDAO;
import org.example.dao.impl.UserDAOImpl;
import org.example.model.User;
import org.example.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean registerUser(User user) {
        return userDAO.registerUser(user);
    }

    @Override
    public User login(String username, String password) {
        return userDAO.login(username, password);
    }

    @Override
    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    @Override
    public boolean deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }

    @Override
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public boolean userExists(String username) {
        return userDAO.userExists(username);
    }

    @Override
    public boolean changePassword(int userId, String newPassword) {
        return userDAO.changePassword(userId, newPassword);
    }

}