package org.dev.dao;

import org.dev.models.User;

import java.util.List;

public interface UserDao {
//    Create
    void addUsers(User user);

//    Read
    User getUserById(int userId);
    List<User> getAllUsers();

//    Update
    void updateUser(User user);

//    Delete
    void deleteUserById(int userId);
}
