package com.mountblue.StackOverFlow.repository;

import com.mountblue.StackOverFlow.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
