package com.quizapp.QuizApp;

import com.quizapp.QuizApp.admin.Admin;
import com.quizapp.QuizApp.user.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Login as 'admin' or 'user': ");
        String role = scanner.nextLine().trim().toLowerCase();

        if (role.equals("admin")) {
            Admin admin = new Admin();
            if (admin.login()) {
                admin.addQuestions();
            } else {
                System.out.println("Incorrect password.");
            }
        } else if (role.equals("user")) {
            User user = new User();
            user.takeQuiz();
        } else {
            System.out.println("Invalid role.");
        }
    }
}
