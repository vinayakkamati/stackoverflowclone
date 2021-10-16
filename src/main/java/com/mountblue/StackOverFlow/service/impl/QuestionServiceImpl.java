package com.mountblue.StackOverFlow.service.impl;

import com.mountblue.StackOverFlow.model.Question;
import com.mountblue.StackOverFlow.repository.QuestionRepository;
import com.mountblue.StackOverFlow.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Question getQuestionById(Integer questionId) {
        Optional<Question> optional = questionRepository.findById(questionId);
        Question question = null;

        if (optional.isPresent()) {
            question = optional.get();
        } else {
            throw new RuntimeException("Post not found!");
        }
        return question;
    }

    @Override
    public void deletePostById(Integer questionId) {
        this.questionRepository.deleteById(questionId);
    }

    @Override
    public List<Question> findAllQuestions(String keyword) {
        return questionRepository.findAll(keyword);
    }
}
