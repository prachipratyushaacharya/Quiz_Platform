//This tells Spring Boot: When someone opens http://localhost:8080, redirect them to login.html.
package com.quizapp.QuizApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login.html";
    }
}
