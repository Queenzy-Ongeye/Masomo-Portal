package org.dev.dao;

import org.dev.models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oUserDao implements UserDao{
    private final Sql2o sql2o;

    public Sql2oUserDao(Sql2o sql2o) {
        this.sql2o = sql2o;

    }


    //    ======================= Adding a new user =====================//
    @Override
    public void addUsers(User user) {
        String sql = "INSERT INTO user_table (firstName, lastName, email, password) VALUES (:firstName, :lastName, :email, :password)";
        try(Connection connection = sql2o.open()){
            int userId = (int) connection.createQuery(sql, true)
                    .addParameter("firstName", user.getFirstName())
                    .addParameter("lastName", user.getLastName())
                    .addParameter("email", user.getEmail())
                    .addParameter("password", user.getPassword())
                    .executeUpdate()
                    .getKey();
            user.setUserId(userId);
        }

    }

//   =================== Finding user by it's Id ================= //

    @Override
    public User getUserById(int userId) {
        String sql = "SELECT id FROM user_table WHERE id = :userId";
        try(Connection connection = sql2o.open()){
            return connection.createQuery(sql)
                    .addParameter("userId", userId)
                    .executeAndFetchFirst(User.class);

        }
    }

//    =============== Listing out all users ================== //

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user_table";
        try(Connection connection = sql2o.open()){
            return connection.createQuery(sql)
                    .executeAndFetch(User.class);
        }
    }

//    ==================== Updating user's details =============== //
    @Override
    public void updateUser(User user) {
        String sql = "UPDATE user_table SET firstName = :firstName, lastName = :lastName, email = :email, password = :password WHERE userId = :userId";
        try(Connection connection = sql2o.open()){
            connection.createQuery(sql)
                    .addParameter("firstName", user.getFirstName())
                    .addParameter("lastName", user.getLastName())
                    .addParameter("email", user.getEmail())
                    .addParameter("password", user.getPassword())
                    .executeUpdate();
        }

    }

//    ======================== Deleting user by it's Id ====================== //
    @Override
    public void deleteUserById(int userId) {
        String sql = "DELETE FROM user_table WHERE userId = :userId";

        try(Connection connection = sql2o.open()){
            connection.createQuery(sql)
                    .addParameter("userId", userId)
                    .executeUpdate();
        }

    }

    //    ======================== Checking if a user exists =================== //
    public boolean checkUserCredentials(int userId,String email, String password){
        User user = getUserById(userId);

        if (user == null){
            return false;
        }

        return user.getEmail().equals(email) && user.getPassword().equals(password);
    }


}
