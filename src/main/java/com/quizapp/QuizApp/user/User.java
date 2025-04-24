package com.quizapp.QuizApp.user;

import com.quizapp.QuizApp.dao.QuestionRepository;
import com.quizapp.QuizApp.dao.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

public class User {
    private Scanner scanner = new Scanner(System.in);
    private QuestionRepository repository;

    @Autowired
    public User(QuestionRepository repository) {
        this.repository = repository;
    }

    public void takeQuiz() {
        List<Question> questions = repository.findAll();
        int score = 0;

        for (Question q : questions) {
            System.out.println();
            q.display();
            System.out.print("Your answer (1-4): ");
            int ans = scanner.nextInt();

            String answeString = convertIntToAnswerString(ans);

            if (q.isCorrect(answeString)) {
                System.out.println("✅ Correct!");
                score++;
            } else {
                System.out.println("❌ Wrong!");
            }
        }

        System.out.println("\nQuiz Over. Your score: " + score + "/" + questions.size());
    }

    private String convertIntToAnswerString(int answerChoice) {
        switch (answerChoice) {
            case 1:
                return "A";
            case 2:
                return "B";
            case 3:
                return "C";
            case 4:
                return "D";
            default:
                return ""; // or handle invalid input
        }
    }
}
