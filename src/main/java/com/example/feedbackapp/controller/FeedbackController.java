package com.example.feedbackapp.controller;

import com.example.feedbackapp.model.Feedback;
import com.example.feedbackapp.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public String getFeedbackPage(Model model) {
        model.addAttribute("feedbackList", feedbackService.getAllFeedback());
        return "feedback";
    }

    @PostMapping("/submit")
    public String submitFeedback(@ModelAttribute Feedback feedback) {
        feedbackService.addFeedback(feedback);

        // Send to Google Sheets
        RestTemplate restTemplate = new RestTemplate();
        String googleAppsScriptUrl = "https://script.google.com/macros/s/AKfycbxfxnXKKJ7FRXPMxbkvnHzjiOWgjmP0uzIU-hjWa3iDyjg7Una22okmpOX5ZB1E13_UYQ/exec"; // Replace with your Apps Script URL
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Feedback> request = new HttpEntity<>(feedback, headers);
        restTemplate.postForObject(googleAppsScriptUrl, request, String.class);

        return "redirect:/feedback";
    }

    @PostMapping("/update/{index}")
    public String updateFeedback(@PathVariable int index, @ModelAttribute Feedback feedback) {
        feedbackService.updateFeedback(index, feedback);
        return "redirect:/feedback";
    }

    @PostMapping("/delete/{index}")
    public String deleteFeedback(@PathVariable int index) {
        feedbackService.deleteFeedback(index);
        return "redirect:/feedback";
    }
}