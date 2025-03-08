package com.quiz.quiz_services.dto;

public record QuizDto(
        String category,
        int numQuestions,
        String title
){}
