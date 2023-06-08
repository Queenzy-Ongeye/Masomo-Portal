package org.dev.dao;

import org.dev.models.User;

import java.util.List;

public interface UserDao {
//    Create
    void addUsers(User user);

//    Read
    User getUser(String email, String password);
    List<User> getAllUsers();

//    Update
    void updateUser(User user);

//    Delete
    void deleteUserByEmail(String email);
}
