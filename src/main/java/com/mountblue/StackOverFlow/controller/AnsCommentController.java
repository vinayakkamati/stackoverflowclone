package com.mountblue.StackOverFlow.controller;

import com.mountblue.StackOverFlow.model.AnsComment;
import com.mountblue.StackOverFlow.model.Answer;
import com.mountblue.StackOverFlow.model.User;
import com.mountblue.StackOverFlow.service.AnsCommentService;
import com.mountblue.StackOverFlow.service.AnswerService;
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
public class AnsCommentController {
    AnswerService answerService;
    AnsCommentService ansCommentService;
    UserService userService;
    QuestionController questionController;

    @Autowired
    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Autowired
    public void setAnsCommentService(AnsCommentService ansCommentService) {
        this.ansCommentService = ansCommentService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setQuestionController(QuestionController questionController) {
        this.questionController = questionController;
    }

    @PostMapping("/ansComment/{ansId}")
    public String saveAnsComment(@PathVariable(value = "ansId") Integer answerId,
                                 @RequestParam(value = "content") String content,
                                 @RequestParam("questionId") Integer questionId,
                                 Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserEmail = authentication.getName();
            user = userService.getUserByEmail(currentUserEmail);

        }
        Answer answer = answerService.getAnswerById(answerId);
        AnsComment ansComment = new AnsComment();
        ansComment.setContent(content);
        List<AnsComment> ansComments = answer.getComments();
        ansComments.add(ansComment);
        answer.setComments(ansComments);
        ansComment.setAnswer(answer);
        ansComment.setEmail(user.getEmail());
        ansCommentService.save(ansComment);
        return questionController.showQuestion(questionId, model);
    }


    @GetMapping("/editAnswerComment/{commentId}")
    public String editAnsComment(@PathVariable(name = "commentId") Integer commentId,
                                 @RequestParam("questionId") Integer questionId,
                                 Model model) {
        AnsComment editAnsComment = ansCommentService.getAnsCommentById(commentId);
        model.addAttribute("editAnsComment", editAnsComment);
        return questionController.showQuestion(questionId, model);
    }


    @PostMapping("/updateAnsComment/{commentId}")
    public String updateComment(@PathVariable(value = "commentId") Integer commentId,
                                @RequestParam("questionId") Integer questionId,
                                @ModelAttribute("editAnsComment") AnsComment editAnsComment,
                                Model model) {
        AnsComment prevComment = ansCommentService.getAnsCommentById(commentId);
        prevComment.setContent(editAnsComment.getContent());
        ansCommentService.save(prevComment);
        model.addAttribute("editAnsComment", null);
        return questionController.showQuestion(questionId, model);
    }

    @PostMapping("/deleteAnsComment/{commentId}")
    public String deleteComment(@PathVariable(value = "commentId") Integer commentId,
                                @RequestParam("questionId") Integer questionId,
                                Model model) {
        ansCommentService.deleteAnswerCommentById(commentId);

        return questionController.showQuestion(questionId, model);
    }
}
