package com.quizapp.QuizApp;

import com.quizapp.QuizApp.admin.Admin;
import com.quizapp.QuizApp.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Login as 'admin' or 'user': ");
        String role = scanner.nextLine().trim().toLowerCase();

        if (role.equals("admin")) {
            Admin admin = context.getBean(Admin.class);
            if (admin.login()) {
                admin.addQuestions();
            } else {
                System.out.println("Incorrect password.");
            }
        } else if (role.equals("user")) {
            User user = context.getBean(User.class);
            user.takeQuiz();
        } else {
            System.out.println("Invalid role.");
        }
    }
}
