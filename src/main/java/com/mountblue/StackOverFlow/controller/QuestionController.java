package com.mountblue.StackOverFlow.controller;

import com.mountblue.StackOverFlow.model.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuestionController {

    @GetMapping("/new")
    public String createQuestion(Model model) {
        model.addAttribute("question", new Question());

        return "newquestion";
    }
}
