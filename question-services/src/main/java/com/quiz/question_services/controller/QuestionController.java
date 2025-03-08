package com.quiz.question_services.controller;


import com.quiz.question_services.dto.QuizDto;
import com.quiz.question_services.model.Question;
import com.quiz.question_services.model.Response;
import com.quiz.question_services.services.QuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionServices questionServices;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion(){
        return questionServices.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionServices.getQuestionsByCategory(category);
    }

    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionServices.addQuestion(question);
    }

    @GetMapping("generateQuiz")
    public ResponseEntity<List<Integer>> generateQuiz(@RequestParam String category,
                                                      @RequestParam int numQuestions){
        return questionServices.generateQuiz(category, numQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuizDto>> getQuestions(@RequestBody List<Integer> questionIds){
        return questionServices.getQuestions(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionServices.getScore(responses);
    }
}
