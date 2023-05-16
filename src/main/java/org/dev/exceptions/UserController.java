package org.dev.exceptions;

import org.dev.dao.Sql2oUserDao;
import org.dev.models.User;

import java.util.Scanner;

import static org.dev.database.DBase.sql2o;


public class UserController {
    private final Sql2oUserDao sql2oUserDao = new Sql2oUserDao(sql2o);

    public String signUp(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);

        sql2oUserDao.addUsers(user);



        return "User created";
    }

    public String logIn(){

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your user ID: ");
        int userId = scanner.nextInt();

        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        boolean isValidUser = sql2oUserDao.checkUserCredentials(userId, email, password);

        if (isValidUser){
            return "Logged in successfully";
        }else{
            return "Invalid credentials";
        }

    }

}
