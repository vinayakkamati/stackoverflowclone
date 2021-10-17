package com.mountblue.StackOverFlow.service.impl;

import com.mountblue.StackOverFlow.model.AnsComment;
import com.mountblue.StackOverFlow.repository.AnsCommentRepository;
import com.mountblue.StackOverFlow.service.AnsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnsCommentServiceImpl implements AnsCommentService {
    @Autowired
    AnsCommentRepository ansCommentRepository;

    @Override
    public void save(AnsComment ansComment) {
        ansCommentRepository.save(ansComment);
    }

    @Override
    public AnsComment getAnsCommentById(Integer commentId) {
        Optional<AnsComment> optional = ansCommentRepository.findById(commentId);
        AnsComment ansComment = null;

        if (optional.isPresent()) {
            ansComment = optional.get();
        } else {
            throw new RuntimeException("comment not found!");
        }
        return ansComment;

    }

    @Override
    public void deleteAnswerCommentById(Integer commentId) {
           this. ansCommentRepository.deleteById(commentId);
    }
}
