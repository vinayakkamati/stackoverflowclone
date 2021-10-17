package com.mountblue.StackOverFlow.service.impl;

import com.mountblue.StackOverFlow.model.Answer;
import com.mountblue.StackOverFlow.model.QuesComment;
import com.mountblue.StackOverFlow.repository.QuesCommentRepository;
import com.mountblue.StackOverFlow.service.QuesCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuesCommentServiceImpl implements QuesCommentService {

    @Autowired
    QuesCommentRepository quesCommentRepository;

    @Override
    public QuesComment save(QuesComment quesComment) {
        return quesCommentRepository.save(quesComment);
    }

    @Override
    public QuesComment getQuesCommentById(Integer commentId) {
        Optional<QuesComment> optional = quesCommentRepository.findById(commentId);
        QuesComment quesComment = null;

        if (optional.isPresent()) {
            quesComment = optional.get();
        } else {
            throw new RuntimeException("comment not found!");
        }
        return quesComment;
    }

    @Override
    public void deleteQuesCommentById(Integer commentId) {
        this.quesCommentRepository.deleteById(commentId);
    }
}
