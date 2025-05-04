package com.example.feedbackapp.service;

import com.example.feedbackapp.model.Feedback;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackService {
    private final List<Feedback> feedbackList = new ArrayList<>();

    public void addFeedback(Feedback feedback) {
        feedbackList.add(feedback);
    }

    public List<Feedback> getAllFeedback() {
        return new ArrayList<>(feedbackList);
    }

    public void updateFeedback(int index, Feedback feedback) {
        if (index >= 0 && index < feedbackList.size()) {
            feedbackList.set(index, feedback);
        }
    }

    public void deleteFeedback(int index) {
        if (index >= 0 && index < feedbackList.size()) {
            feedbackList.remove(index);
        }
    }
}