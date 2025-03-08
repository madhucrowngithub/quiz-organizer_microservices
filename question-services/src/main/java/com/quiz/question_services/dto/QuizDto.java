package com.quiz.question_services.dto;

public record QuizDto(
        Integer id,
        String questionTitle,
        String option1,
        String option2,
        String option3,
        String option4
) {
}
