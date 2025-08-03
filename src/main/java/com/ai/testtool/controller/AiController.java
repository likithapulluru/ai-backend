package com.ai.testtool.controller;

import com.ai.testtool.service.OpenRouterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://ai-testtool.netlify.app/")
@RestController
@RequestMapping("/api/ai")
public class AiController {

    @Autowired
    private OpenRouterService openRouterService;
    
    @GetMapping("/")
    public String home() {
        return "Backend is live";
    }

    @PostMapping("/generate-test")
    public String generateTest(@RequestBody String scenario) {
        return openRouterService.askAi("Write a Selenium Java TestNG test case for: " + scenario);
    }

    @PostMapping("/classify-bug")
    public String classifyBug(@RequestBody String bugReport) {
        return openRouterService.askAi("Classify this bug report: " + bugReport);
    }
}
