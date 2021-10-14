package com.mountblue.StackOverFlow.service;

import com.mountblue.StackOverFlow.model.Question;

import java.util.List;

public interface QuestionService {
    public Question save(Question question);

    List<Question> getAllQuestions();
}
