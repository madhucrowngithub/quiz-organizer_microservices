package com.quiz.question_services.services.imp;

import com.quiz.question_services.dto.QuizDto;
import com.quiz.question_services.dto.mapper.QuestionToQuizMapper;
import com.quiz.question_services.model.Question;
import com.quiz.question_services.model.Response;
import com.quiz.question_services.repository.QuestionRepo;
import com.quiz.question_services.services.QuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuestionServicesImp implements QuestionServices {
    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    QuestionToQuizMapper questionToQuizMapper;
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionRepo.findAll();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        List<Question> questions = questionRepo.findByCategory(category);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionRepo.save(question);
        return new ResponseEntity<>("Question added successfully", HttpStatus.OK);
    }

    public ResponseEntity<String> updateQuestion(Question question) {
        questionRepo.save(question);
        return new ResponseEntity<>("Question updated successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Integer>> generateQuiz(String category, int numQuestions) {
       List<Integer> questions =  questionRepo.findRandomQuestionsByCategory(category, numQuestions);
       return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<QuizDto>> getQuestions(List<Integer> questionIds) {
        return new ResponseEntity<>(questionRepo.findAllById(questionIds).stream()
                .map(questionToQuizMapper::mapQuestionToQuiz).toList(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int score = 0;
        for (Response response : responses) {
            Question question = questionRepo.findById(response.getId()).get();
            if (question.getRightAnswer().equals(response.getResponse())) {
                score++;
            }
        }
        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
