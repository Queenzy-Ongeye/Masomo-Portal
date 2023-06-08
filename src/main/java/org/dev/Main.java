package org.dev;

import org.dev.dao.UserDao;
import org.dev.exceptions.UserController;
import org.dev.models.User;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome to Masomo Portal \n 'Where learning and fun combine \uD83D\uDE0A'");
        UserController userController = new UserController();

//         Use the userController instance to call the desired methods

        String signUpResult = userController.signUp();
        System.out.println(signUpResult);

        String logInResult = userController.logIn();
        System.out.println(logInResult);




    }


}