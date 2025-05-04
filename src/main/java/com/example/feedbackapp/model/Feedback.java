package com.example.feedbackapp.model;

import lombok.Data;

@Data
public class Feedback {
    private String eventName;
    private int rating;
    private String feedback;
}