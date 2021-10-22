package com.mountblue.StackOverFlow.controller;

import com.mountblue.StackOverFlow.model.QuesComment;
import com.mountblue.StackOverFlow.model.Question;
import com.mountblue.StackOverFlow.model.User;
import com.mountblue.StackOverFlow.service.QuesCommentService;
import com.mountblue.StackOverFlow.service.QuestionService;
import com.mountblue.StackOverFlow.service.UserService;
import com.mountblue.StackOverFlow.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class QuesCommentController {
    QuestionService questionService;
    QuesCommentService quesCommentService;
    QuestionController questionController;
    UserService userService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setQuesCommentService(QuesCommentService quesCommentService) {
        this.quesCommentService = quesCommentService;
    }

    @Autowired
    public void setQuestionController(QuestionController questionController) {
        this.questionController = questionController;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/quesComment/{questionId}")
    public String saveComment(@PathVariable(value = "questionId") Integer questionId,
                              @RequestParam(value = "content") String content,
                              Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserEmail = authentication.getName();
            user = userService.getUserByEmail(currentUserEmail);

        }
        Question question = questionService.getQuestionById(questionId);
        QuesComment quesComment = new QuesComment();
        quesComment.setContent(content);
        List<QuesComment> quesComments = question.getComments();
        quesComments.add(quesComment);
        question.setComments(quesComments);
        quesComment.setQuestion(question);
        quesComment.setEmail(user.getEmail());
        quesCommentService.save(quesComment);
        return questionController.showQuestion(questionId, model);
    }

    @PostMapping("/quesComment/updateQuesComment/{commentId}")
    public String updateComment(@PathVariable(value = "commentId") Integer commentId,
                             @RequestParam("questionId") Integer questionId,
                             @ModelAttribute("quesComment") QuesComment quesComment,
                             Model model) {
        QuesComment prevComment = quesCommentService.getQuesCommentById(commentId);
        prevComment.setContent(quesComment.getContent());
        quesCommentService.save(prevComment);
        return questionController.showQuestion(questionId, model);
    }

    @GetMapping("/quesComment/editQuesComment/{commentId}")
    public String editComment(@PathVariable(name = "commentId") Integer commentId,
                                     @RequestParam("questionId") Integer questionId,
                                     Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserEmail = authentication.getName();
            user = userService.getUserByEmail(currentUserEmail);

        }
        QuesComment quesComment = quesCommentService.getQuesCommentById(commentId);
        model.addAttribute("editComment", quesComment);
        return questionController.showQuestion(questionId, model);
    }

    @GetMapping("/quesComment/deleteQuesComment/{commentId}")
    public String deleteComment(@PathVariable(value = "commentId") Integer commentId,
                                @RequestParam("questionId") Integer questionId,
                                Model model) {
        quesCommentService.deleteQuesCommentById(commentId);
        return questionController.showQuestion(questionId, model);
    }
}
