package com.quizapp.QuizApp.user;

import com.quizapp.QuizApp.dao.QuestionDAO;
import com.quizapp.QuizApp.dao.model.Question;

import java.util.List;
import java.util.Scanner;

public class User {
    private Scanner scanner = new Scanner(System.in);
    private QuestionDAO dao = new QuestionDAO();

    public void takeQuiz() {
        List<Question> questions = dao.getAllQuestions();
        int score = 0;

        for (Question q : questions) {
            System.out.println();
            q.display();
            System.out.print("Your answer (1-4): ");
            int ans = scanner.nextInt();

            if (q.isCorrect(ans)) {
                System.out.println("✅ Correct!");
                score++;
            } else {
                System.out.println("❌ Wrong!");
            }
        }

        System.out.println("\nQuiz Over. Your score: " + score + "/" + questions.size());
    }
}
