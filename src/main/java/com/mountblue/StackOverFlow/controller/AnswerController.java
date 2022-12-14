package com.mountblue.StackOverFlow.controller;

import com.mountblue.StackOverFlow.model.Answer;
import com.mountblue.StackOverFlow.model.Question;
import com.mountblue.StackOverFlow.model.User;
import com.mountblue.StackOverFlow.service.AnswerService;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
public class AnswerController {
    AnswerService answerService;
    QuestionService questionService;
    QuestionController questionController;
    UserService userService;

    @Autowired
    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setQuestionController(QuestionController questionController) {
        this.questionController = questionController;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/postAnswer/{quesId}")
    public String saveAnswer(@PathVariable(value = "quesId") Integer quesId, @RequestParam(value = "answerContent") String answerContent) {
        User user = userService.getUserFromContext();
        Question question = questionService.getQuestionById(quesId);
        Answer answer = new Answer();
        answer.setContent(answerContent);
        List<Answer> answers = question.getAnswers();
        answers.add(answer);
        question.setAnswers(answers);
        answer.setQuestion(question);
        answer.setAuthor(user);
        answerService.save(answer);
        return "redirect:/questions";
    }

    @PostMapping("/editAnswer/{answerId}")
    public String editAnswer(@PathVariable(value = "answerId") Integer answerId,
                             @RequestParam("questionId") Integer questionId,
                             @ModelAttribute("answer") Answer answer,
                             Model model) {
        Answer prevAns = answerService.getAnswerById(answerId);
        prevAns.setContent(answer.getContent());
        answerService.save(prevAns);
        return questionController.showQuestion(questionId, model);
    }

    @GetMapping("/updateAnswer/{answerId}")
    public ModelAndView updateAnswer(@PathVariable(name = "answerId") Integer answerId, @RequestParam("questionId") Integer questionId) {
        ModelAndView editView = new ModelAndView("editanswer");
        if (answerId != null) {
            Answer answer = answerService.getAnswerById(answerId);
            answer.setAnswerId(answerId);
            editView.addObject("answer", answer);
            editView.addObject("quesId", questionId);
        }
        return editView;
    }

    @GetMapping("/deleteAnswer/{answerId}")
    public String deleteAnswer(@PathVariable(value = "answerId") Integer answerId,
                               @RequestParam("questionId") Integer questionId,
                               Model model) {
        answerService.deleteAnswerById(answerId);
        return questionController.showQuestion(questionId, model);
    }


    @PostMapping("/answerUpvote/{answerId}")
    public String upvoteAnswer(@PathVariable(name = "answerId") Integer answerId,   @RequestParam( value = "questionId") Integer questionId , Model model){
        User user = userService.getUserFromContext();
        Answer answer = answerService.getAnswerById(answerId);
        Set<User> upVotes= answer.getUpVotes();
        Set<User>downVotes=answer.getDownVotes();

        if(upVotes.contains(user)){
            upVotes.remove(user);
        }else{
            if(downVotes.contains(user)){
                downVotes.remove(user);
            }
            upVotes.add(user);
        }
        answer.setUpVotes(upVotes);
        answer.setDownVotes(downVotes);
        answerService.save(answer);
        Question question = questionService.getQuestionById(questionId);
        model.addAttribute("question", question);
        model.addAttribute("user", user);
        return questionController.showQuestion(questionId, model);
    }

    @PostMapping("/answerDownvote/{answerId}")
    public String downVoteAnswer(@PathVariable(name = "answerId") Integer answerId,   @RequestParam( value = "questionId") Integer questionId , Model model){
        User user = userService.getUserFromContext();
        Answer answer = answerService.getAnswerById(answerId);
        Set<User> upVotes= answer.getUpVotes();
        Set<User>downVotes=answer.getDownVotes();

        if(downVotes.contains(user)){
            downVotes.remove(user);
        }else {
            if(upVotes.contains(user)){
                upVotes.remove(user);
            }
            downVotes.add(user);
        }
        answer.setUpVotes(upVotes);
        answer.setDownVotes(downVotes);
        answerService.save(answer);
        Question question = questionService.getQuestionById(questionId);
        model.addAttribute("question", question);
        model.addAttribute("user", user);
        return questionController.showQuestion(questionId, model);
    }

}
