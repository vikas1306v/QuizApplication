package com.vvquiz.repository;

import com.vvquiz.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    Optional<Answer> findByQuestionId(String questionId);
}
