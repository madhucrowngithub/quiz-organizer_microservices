package com.quiz.question_services.dto.mapper;

import com.quiz.question_services.dto.QuizDto;
import com.quiz.question_services.model.Question;

public interface QuestionToQuizMapper {

    public QuizDto mapQuestionToQuiz(Question question) ;
}
