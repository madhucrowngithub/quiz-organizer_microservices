package com.quiz.question_services.dto.mapper.imp;

import com.quiz.question_services.dto.QuizDto;
import com.quiz.question_services.dto.mapper.QuestionToQuizMapper;
import com.quiz.question_services.model.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionToQuizMapperImp implements QuestionToQuizMapper {


    public QuizDto mapQuestionToQuiz(Question question) {
        return new QuizDto(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
    }
}
