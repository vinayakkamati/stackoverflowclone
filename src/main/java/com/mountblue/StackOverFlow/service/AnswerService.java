package com.mountblue.StackOverFlow.service;

import com.mountblue.StackOverFlow.model.Answer;

public interface AnswerService {
    public Answer save(Answer answer);

    Answer getAnswerById(Integer answerId);

    void deleteAnswerById(Integer answerId);
}
