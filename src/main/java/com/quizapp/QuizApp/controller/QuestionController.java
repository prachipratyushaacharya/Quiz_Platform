package com.quizapp.QuizApp.controller;

import java.util.List;

import com.quizapp.QuizApp.dao.QuestionRepository;
import com.quizapp.QuizApp.dao.model.Question;
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
}
