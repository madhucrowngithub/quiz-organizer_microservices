package com.quiz.question_services.services;

import com.quiz.question_services.dto.QuizDto;
import com.quiz.question_services.model.Question;
import com.quiz.question_services.model.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionServices {
     public ResponseEntity<List<Question>> getAllQuestions();
     public ResponseEntity<List<Question>> getQuestionsByCategory(String category);

     public ResponseEntity<String> addQuestion(Question question);
     public ResponseEntity<String> updateQuestion(Question question);
     public ResponseEntity<List<Integer>> generateQuiz(String category, int numQuestions);

    public ResponseEntity<List<QuizDto>> getQuestions(List<Integer> questionIds);

    public ResponseEntity<Integer> getScore(List<Response> responses);
}
