package com.mountblue.StackOverFlow.service;

import com.mountblue.StackOverFlow.model.AnsComment;

public interface AnsCommentService {
    void save(AnsComment ansComment);

    AnsComment getAnsCommentById(Integer commentId);

    void deleteAnswerCommentById(Integer commentId);
}
