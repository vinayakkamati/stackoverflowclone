package com.mountblue.StackOverFlow.controller;

import com.mountblue.StackOverFlow.model.Answer;
import com.mountblue.StackOverFlow.model.Question;
import com.mountblue.StackOverFlow.model.User;
import com.mountblue.StackOverFlow.service.AnswerService;
import com.mountblue.StackOverFlow.service.QuestionService;
import com.mountblue.StackOverFlow.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AnswerController {

    @Autowired
    AnswerService answerService;
    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionController questionController;
    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping("/postAnswer/{quesId}")
    public String saveAnswer(@PathVariable(value = "quesId") Integer quesId, @RequestParam(value = "answerContent") String answerContent) {
        User user = userServiceImpl.getCurrentUser();
        Question question = questionService.getQuestionById(quesId);
        Answer answer = new Answer();
        answer.setContent(answerContent);
        List<Answer> answers = question.getAnswers();
        answers.add(answer);
        question.setAnswers(answers);
        answer.setQuestion(question);
        answer.setAuthorId(user.getUserId());
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
    public ModelAndView updateAnswer(@PathVariable(name = "answerId") Integer answerId, @RequestParam("questionId")Integer questionId){
        ModelAndView editView = new ModelAndView("editanswer");
        if(answerId != null) {
            Answer answer = answerService.getAnswerById(answerId);
            answer.setAnswerId(answerId);
            editView.addObject("answer", answer);
            editView.addObject("quesId", questionId);
        }
        return editView;
    }

    @GetMapping("/deleteAnswer/{answerId}")
    public String deleteAnswer(@PathVariable(value = "answerId") Integer answerId,
                               @RequestParam("questionId")Integer questionId,
                               Model model) {
        answerService.deleteAnswerById(answerId);
        return questionController.showQuestion(questionId, model);
    }

}
