package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UserService {

    boolean registerUser(User user);

    User login(String username, String password);

    boolean updateUser(User user);

    boolean deleteUser(int userId);

    User getUserById(int userId);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    boolean userExists(String username);

    boolean changePassword(int userId, String newPassword);

}