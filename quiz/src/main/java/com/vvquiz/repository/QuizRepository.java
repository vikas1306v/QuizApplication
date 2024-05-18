package com.vvquiz.repository;

import com.vvquiz.entities.Quiz;
import com.vvquiz.repository.customrepository.CustomQuizRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> , CustomQuizRepository {
}
