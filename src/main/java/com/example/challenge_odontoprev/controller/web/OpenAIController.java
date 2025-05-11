package com.example.challenge_odontoprev.controller.web;

import com.example.challenge_odontoprev.service.OpenAIService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OpenAIController {

    private final OpenAIService openAIService;

    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @GetMapping("/chatai")
    public String chatForm(Model model) {
        model.addAttribute("question", "");
        model.addAttribute("answer", "");
        return "chatai";
    }

    @PostMapping("/chatai")
    public String askQuestion(@RequestParam String question, Model model) {
        String answer = openAIService.askQuestion(question);
        model.addAttribute("question", question);
        model.addAttribute("answer", answer);
        return "chatai";
    }
}