package com.quiz.quiz_services.services;

import com.quiz.quiz_services.dto.QuestionDto;
import com.quiz.quiz_services.dto.QuizDto;
import com.quiz.quiz_services.dto.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizServices {

    ResponseEntity<String> createQuiz(QuizDto quizDto);

    ResponseEntity<List<QuestionDto>> getQuizQuestions(int quizId);

    ResponseEntity<Integer> submitQuiz(List<Response> responses);
}
