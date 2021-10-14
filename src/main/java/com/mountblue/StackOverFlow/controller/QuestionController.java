package com.mountblue.StackOverFlow.controller;

import com.mountblue.StackOverFlow.model.Question;
import com.mountblue.StackOverFlow.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/new")
    public String createQuestion(Model model) {
        model.addAttribute("question", new Question());

        return "newquestion";
    }

    @PostMapping("/postQuestion")
    public String saveQuestion(@ModelAttribute("question") Question question) {


        questionService.save(question);
        return "redirect:/new";
    }

    @GetMapping("/questions")
    public String showQuestions(Model model) {
        List<Question> listQuestions = questionService.getAllQuestions();

        model.addAttribute("listQuestions", listQuestions);


        return "questions";
    }
}

