package com.quizapp.QuizApp.dao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")

public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String text;

    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private String correctOption;

    public Question() {
    }

    public Question(String text, String[] options, String correctOption) {
        this.text = text;
        this.option1 = options[0];
        this.option2 = options[1];
        this.option3 = options[2];
        this.option4 = options[3];
        this.correctOption = correctOption;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getOptions() {
        return new String[] { option1, option2, option3, option4 };
    }

    public void setOptions(String[] options) {
        this.option1 = options[0];
        this.option2 = options[1];
        this.option3 = options[2];
        this.option4 = options[3];
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    // Optional helper methods

    public void display() {
        System.out.println(text);
        System.out.println("1. " + option1);
        System.out.println("2. " + option2);
        System.out.println("3. " + option3);
        System.out.println("4. " + option4);
    }

    public boolean isCorrect(String userChoice) {
        return userChoice == correctOption;
    }
}
