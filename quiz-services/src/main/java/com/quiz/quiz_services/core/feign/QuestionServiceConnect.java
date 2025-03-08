package com.quiz.quiz_services.core.feign;

import com.quiz.quiz_services.dto.QuestionDto;
import com.quiz.quiz_services.dto.QuizDto;
import com.quiz.quiz_services.dto.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "QUESTION-SERVICES")
public interface QuestionServiceConnect {

    @GetMapping("question/generateQuiz")
    public ResponseEntity<List<Integer>> generateQuiz(@RequestParam String category,
                                                      @RequestParam int numQuestions);


    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionDto>> getQuestions(@RequestBody List<Integer> questionIds);

    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
