package com.quizapp.QuizApp.controller;

import java.util.List;

import com.quizapp.QuizApp.dao.QuestionRepository;
import com.quizapp.QuizApp.dao.model.Question;
import com.quizapp.QuizApp.dao.model.Answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*")

public class QuestionController {

    @Autowired
    private QuestionRepository questionRepo;

    // Endpoint to add a new question
    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody List<Question> question) {
        questionRepo.saveAll(question);
        return ResponseEntity.ok("Questions Saved!!");
    }

    @GetMapping("/all")
    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    @PostMapping("/submit")
    public ResponseEntity<Integer> submitQuiz(@RequestBody List<Answer> answers) {
        int score = 0;
        List<Question> questions = questionRepo.findAll();

        for (int i = 0; i < questions.size(); i++) {
            if (i < answers.size()
                    && questions.get(i).getCorrectOption().equalsIgnoreCase(answers.get(i).getAnswer())) {
                score++;
            }
        }
        return ResponseEntity.ok(score);
    }

}
