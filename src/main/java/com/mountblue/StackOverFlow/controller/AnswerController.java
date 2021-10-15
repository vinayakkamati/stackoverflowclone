package com.mountblue.StackOverFlow.controller;

import com.mountblue.StackOverFlow.model.Answer;
import com.mountblue.StackOverFlow.model.Question;
import com.mountblue.StackOverFlow.service.AnswerService;
import com.mountblue.StackOverFlow.service.QuestionService;
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

    @PostMapping("/question/edit/{answerId}")
    public String editQuestion(@PathVariable(value = "questionId") Integer answerId,
                               @RequestParam("content") String content,
                               Model model) {
        Answer answer = answerService.getAnswerById(answerId);
        answer.setContent(content);
        answerService.save(answer);

        return questionController.getSelectedQuestion(answerId, model);
    }

    @GetMapping("/update/{answerId}")
    public ModelAndView updateAnswer(@PathVariable(name = "answerId") Integer answerId){
        ModelAndView editView = new ModelAndView("editanswer");
        if(answerId != null) {
            Answer answer = answerService.getAnswerById(answerId);
            answer.setAnswerId(answerId);
            editView.addObject("answer", answer);
        }
        return editView;
    }

    @GetMapping("/question/delete/{answerId}")
    public String deleteAnswer(@PathVariable(value = "answerId") Integer answerId) {
        answerService.deletePostById(answerId);
        return "redirect:/question";
    }

}
