package com.mountblue.StackOverFlow.repository;

import com.mountblue.StackOverFlow.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query("SELECT q FROM Question q WHERE q.title LIKE %?1% OR " +
            "lower(q.tag) LIKE %?1%")
    public List<Question> findAll(String keyword);
}
