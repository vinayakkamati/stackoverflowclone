package com.mountblue.StackOverFlow.service.impl;

import com.mountblue.StackOverFlow.model.Answer;
import com.mountblue.StackOverFlow.model.Question;
import com.mountblue.StackOverFlow.repository.AnswerRepository;
import com.mountblue.StackOverFlow.service.AnswerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public Answer getAnswerById(Integer answerId) {
        Optional<Answer> optional = answerRepository.findById(answerId);
        Answer answer = null;

        if (optional.isPresent()) {
            answer = optional.get();
        } else {
            throw new RuntimeException("answer not found!");
        }
        return answer;
    }

    @Override
    public void deleteAnswerById(Integer answerId) {
        this.answerRepository.deleteById(answerId);
    }
}
