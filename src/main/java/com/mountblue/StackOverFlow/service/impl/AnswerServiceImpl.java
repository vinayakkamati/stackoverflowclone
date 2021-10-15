package com.mountblue.StackOverFlow.service.impl;

import com.mountblue.StackOverFlow.model.Answer;
import com.mountblue.StackOverFlow.repository.AnswerRepository;
import com.mountblue.StackOverFlow.service.AnswerService;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {

    private  final AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }
}
