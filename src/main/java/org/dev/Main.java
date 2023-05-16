package org.dev;

import org.dev.dao.UserDao;
import org.dev.exceptions.UserController;
import org.dev.models.User;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final UserController userController = new UserController();
    public static void main(String[] args) {
        UserController userController = new UserController();

        // Use the userController instance to call the desired methods
        String signUpResult = userController.signUp();
        System.out.println(signUpResult);

        String logInResult = userController.logIn();
        System.out.println(logInResult);




    }


}