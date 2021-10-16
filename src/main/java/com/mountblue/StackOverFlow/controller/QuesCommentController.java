package com.mountblue.StackOverFlow.controller;

import com.mountblue.StackOverFlow.model.Answer;
import com.mountblue.StackOverFlow.model.QuesComment;
import com.mountblue.StackOverFlow.model.Question;
import com.mountblue.StackOverFlow.service.QuesCommentService;
import com.mountblue.StackOverFlow.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class QuesCommentController {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuesCommentService quesCommentService;

    @Autowired
    QuestionController questionController;

    @PostMapping("/quesComment/{questionId}")
    public String saveComment(@PathVariable(value = "questionId") Integer questionId,
                              @RequestParam(value = "content") String content,
                              Model model) {
        Question question = questionService.getQuestionById(questionId);
        QuesComment quesComment = new QuesComment();
        quesComment.setContent(content);
        List<QuesComment> quesComments = question.getComments();
        quesComments.add(quesComment);
        question.setComments(quesComments);
        quesComment.setQuestion(question);
        quesCommentService.save(quesComment);
        return questionController.showQuestion(questionId, model);
    }

    @PostMapping("/updateQuesComment/{commentId}")
    public String updateComment(@PathVariable(value = "commentId") Integer commentId,
                             @RequestParam("questionId") Integer questionId,
                             @ModelAttribute("quesComment") QuesComment quesComment,
                             Model model) {
        QuesComment prevComment = quesCommentService.getQuesCommentById(commentId);
        prevComment.setContent(quesComment.getContent());
        quesCommentService.save(prevComment);
        return questionController.showQuestion(questionId, model);
    }

    @GetMapping("/editQuesComment/{commentId}")
    public String editComment(@PathVariable(name = "commentId") Integer commentId,
                                     @RequestParam("questionId") Integer questionId,
                                     Model model) {
        QuesComment quesComment = quesCommentService.getQuesCommentById(commentId);
        model.addAttribute("editComment", quesComment);
        return questionController.showQuestion(questionId, model);
    }

    @GetMapping("/deleteQuesComment/{commentId}")
    public String deleteComment(@PathVariable(value = "commentId") Integer commentId,
                                @RequestParam("questionId") Integer questionId,
                                Model model) {

        quesCommentService.deleteQuesCommentById(commentId);
        return questionController.showQuestion(questionId, model);
    }
}
