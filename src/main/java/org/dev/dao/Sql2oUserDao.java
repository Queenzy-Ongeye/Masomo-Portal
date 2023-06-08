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

//   =================== Finding user by email and password ================= //

    @Override
    public User getUser(String email, String password) {
        String sql = "SELECT id as userId, email, password FROM user_table WHERE email = :email AND password = :password";
        try(Connection connection = sql2o.open()){
            return connection.createQuery(sql)
                    .addParameter("email", email)
                    .addParameter("password", password)
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
    public void deleteUserByEmail(String email) {
        String sql = "DELETE FROM user_table WHERE email = :email";

        try(Connection connection = sql2o.open()){
            connection.createQuery(sql)
                    .addParameter("userId")
                    .addParameter("email", email)
                    .executeUpdate();
        }

    }

    //    ======================== Checking if a user exists =================== //
    public boolean checkUserCredentials(String email, String password){
        User user = getUser(email, password);

        if (user == null){
            return false;
        }
        System.out.println(user.getUserId());
        return user.getEmail().equals(email) && user.getPassword().equals(password);
    }
}
