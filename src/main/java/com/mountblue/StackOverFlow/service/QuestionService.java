package com.mountblue.StackOverFlow.service;

import com.mountblue.StackOverFlow.model.Question;

import java.util.List;

public interface QuestionService {
    public Question save(Question question);

    List<Question> getAllQuestions();

    Question getQuestionById(Integer questionId);

    void deletePostById(Integer questionId);
<<<<<<< HEAD

    List<Question> findAllQuestionsBySorting(String sortField, String sortDirection);
||||||| 0b42035
=======

    List<Question> findAllQuestions(String keyword);
>>>>>>> 0af1f4e90926e62a433147042cd9622fc91e014f
}
