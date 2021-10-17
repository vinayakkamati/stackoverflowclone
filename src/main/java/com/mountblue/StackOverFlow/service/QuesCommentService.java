package com.mountblue.StackOverFlow.service;

import com.mountblue.StackOverFlow.model.QuesComment;

public interface QuesCommentService {

    public QuesComment save(QuesComment quesComment);

    QuesComment getQuesCommentById(Integer commentId);

    void deleteQuesCommentById(Integer commentId);
}
