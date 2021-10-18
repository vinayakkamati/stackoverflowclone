package com.mountblue.StackOverFlow.service;

import com.mountblue.StackOverFlow.model.Question;

import java.util.List;

public interface QuestionService {
    public Question save(Question question);

    List<Question> getAllQuestions();

    Question getQuestionById(Integer questionId);

    void deletePostById(Integer questionId);

    List<Question> findAllQuestionsBySorting(String sortField, String sortDirection);

    List<Question> findAllQuestions(String keyword);

}
