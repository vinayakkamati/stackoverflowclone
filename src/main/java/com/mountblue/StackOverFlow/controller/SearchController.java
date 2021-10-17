package com.mountblue.StackOverFlow.controller;

import com.mountblue.StackOverFlow.model.Question;
import com.mountblue.StackOverFlow.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    QuestionService questionService;

    @RequestMapping("/search")
    public String getSearchResult(@RequestParam("searchBar") String keyword, Model model) {
        List<Question> listQuestions = questionService.findAllQuestions(keyword);
        model.addAttribute("listQuestions", listQuestions);

        return "questions";
    }
}
