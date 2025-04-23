package com.quizapp.QuizApp.admin;

import com.quizapp.QuizApp.dao.QuestionDAO;
import com.quizapp.QuizApp.dao.model.Question;

import java.util.Scanner;

public class Admin {
    private static final String ADMIN_PASSWORD = "admin123";
    private Scanner scanner = new Scanner(System.in);
    private QuestionDAO dao = new QuestionDAO();

    public boolean login() {
        System.out.print("Enter admin password: ");
        String input = scanner.nextLine();
        return input.equals(ADMIN_PASSWORD);
    }

    public void addQuestions() {
        while (true) {
            System.out.print("Enter question (or 'done' to finish): ");
            String qText = scanner.nextLine();
            if (qText.equalsIgnoreCase("done"))
                break;

            String[] options = new String[4];
            for (int i = 0; i < 4; i++) {
                System.out.print("Option " + (i + 1) + ": ");
                options[i] = scanner.nextLine();
            }

            int correct;
            while (true) {
                System.out.print("Correct option (1-4): ");
                correct = scanner.nextInt();
                scanner.nextLine(); // clear buffer
                if (correct >= 1 && correct <= 4)
                    break;
                System.out.println("Invalid choice.");
            }

            dao.insertQuestion(new Question(qText, options, correct));
            System.out.println("Question added!");
        }
    }
}
