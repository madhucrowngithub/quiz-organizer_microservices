package com.quiz.quiz_services.controller;

import com.quiz.quiz_services.dto.QuestionDto;
import com.quiz.quiz_services.dto.QuizDto;
import com.quiz.quiz_services.dto.Response;
import com.quiz.quiz_services.services.QuizServices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizServices quizServices;

    @PostMapping("createQuiz")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizServices.createQuiz(quizDto);
    }

    @GetMapping("getQuiz/{quizId}")
    public ResponseEntity<List<QuestionDto>> getQuiz(@PathVariable int quizId){
        return quizServices.getQuizQuestions(quizId);
    }

    @PostMapping("submit")
    public ResponseEntity<Integer> submitQuiz(@RequestBody List<Response> responses){
        return quizServices.submitQuiz(responses);
    }
}
