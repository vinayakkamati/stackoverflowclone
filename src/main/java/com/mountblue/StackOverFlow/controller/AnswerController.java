package com.mountblue.StackOverFlow.controller;

import com.mountblue.StackOverFlow.model.Answer;
import com.mountblue.StackOverFlow.model.Question;
import com.mountblue.StackOverFlow.service.AnswerService;
import com.mountblue.StackOverFlow.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AnswerController {


    @Autowired
    AnswerService answerService;
    @Autowired
    QuestionService questionService;

    @PostMapping("/postAnswer/{quesId}")
    public String saveAnswer(@PathVariable(value = "quesId") Integer quesId, @RequestParam(value = "answerContent") String answerContent) {
        Question question = questionService.getQuestionById(quesId);
        Answer answer = new Answer();
        answer.setContent(answerContent);
        List<Answer> answers = question.getAnswers();
        answers.add(answer);
        question.setAnswers(answers);
        answer.setQuestion(question);
        answerService.save(answer);
        return "redirect:/questions";
    }


}
