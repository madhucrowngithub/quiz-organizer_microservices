package com.quiz.quiz_services.dto;

public record QuestionDto(
        Integer id,
        String questionTitle,
        String option1,
        String option2,
        String option3,
        String option4
) {
}
