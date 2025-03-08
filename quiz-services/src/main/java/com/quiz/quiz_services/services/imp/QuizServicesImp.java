package com.quiz.quiz_services.services.imp;

import com.quiz.quiz_services.core.feign.QuestionServiceConnect;
import com.quiz.quiz_services.dto.QuestionDto;
import com.quiz.quiz_services.dto.QuizDto;
import com.quiz.quiz_services.dto.Response;
import com.quiz.quiz_services.model.Quiz;
import com.quiz.quiz_services.repository.QuizRepo;
import com.quiz.quiz_services.services.QuizServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static jakarta.ws.rs.core.Response.ok;
@Service
public class QuizServicesImp implements QuizServices {
    @Autowired
    QuestionServiceConnect questionServiceConnect;
    @Autowired
    QuizRepo quizRepo;

    @Override
    public ResponseEntity<String> createQuiz(QuizDto quizDto) {
        List<Integer> questions = questionServiceConnect.generateQuiz(quizDto.category(), quizDto.numQuestions()).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(quizDto.title());
        quiz.setQuestionIds(questions);
        quizRepo.save(quiz);

        return new ResponseEntity<>("success",HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<List<QuestionDto>> getQuizQuestions(int quizId) {
        Quiz quiz = quizRepo.findById(quizId).get();
        return new ResponseEntity<>(questionServiceConnect.getQuestions(quiz.getQuestionIds()).getBody(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> submitQuiz(List<Response> responses) {
        return questionServiceConnect.getScore(responses);
    }
}
