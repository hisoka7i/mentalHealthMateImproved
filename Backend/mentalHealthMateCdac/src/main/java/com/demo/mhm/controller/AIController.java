package com.demo.mhm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.mhm.service.AIService;

@RestController
@RequestMapping("/chatbot")
public class AIController {
    @Autowired
    private AIService aiService;

    @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestBody Map<String, String> payload) {
        String question = payload.get("question");
        if (question == null || question.isEmpty()) {
            return ResponseEntity.badRequest().body("Question is required.");
        }
        String answer = aiService.getAnswer(question);
        System.out.println(answer + " << -- This is the response.");
        return ResponseEntity.ok(answer);
    }
}
