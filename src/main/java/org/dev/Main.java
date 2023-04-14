package org.dev;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class Main {
    public static void main(String[] args) {
        String connectionString = "jdbc:postgresql://localhost:5432/quiz-app-portal";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "code001");

        try(Connection connection = sql2o.open()){
            String createUserTableSql = "CREATE TABLE user_table ("
                    + "id SERIAL PRIMARY KEY,"
                    + "firstName VARCHAR(50) NOT NULL,"
                    + "lastName VARCHAR(50) NOT NULL,"
                    + "email VARCHAR(100) NOT NULL,"
                    + "password VARCHAR(100) NOT NULL"
                    + ")";
            connection.createQuery(createUserTableSql).executeUpdate();
        }
    }
}