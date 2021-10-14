package com.mountblue.StackOverFlow.service.impl;

import com.mountblue.StackOverFlow.model.Question;
import com.mountblue.StackOverFlow.repository.QuestionRepository;
import com.mountblue.StackOverFlow.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}
