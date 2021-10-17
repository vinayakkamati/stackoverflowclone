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
public class SortController {

    @Autowired
    QuestionService questionService;

    @RequestMapping("/sort")
    public String getSortResult(@RequestParam(value = "sortField") String sortField,
                                @RequestParam(value = "sortDir") String sortDir,
                                Model model) {
        List<Question> listQuestions = questionService.findAllQuestionsBySorting( sortField, sortDir);

        model.addAttribute("listQuestions", listQuestions);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("desc") ? "asc" : "desc");

        return "questions";
    }
}
