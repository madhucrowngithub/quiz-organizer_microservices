package com.quiz.quiz_services.repository;

import com.quiz.quiz_services.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {
}
