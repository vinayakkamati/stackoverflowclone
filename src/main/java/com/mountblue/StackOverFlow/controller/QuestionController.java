package com.mountblue.StackOverFlow.controller;

import com.mountblue.StackOverFlow.model.Question;
import com.mountblue.StackOverFlow.model.Tag;
import com.mountblue.StackOverFlow.model.User;
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

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class QuestionController {
    QuestionService questionService;
    UserService userService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/new")
    public String createQuestion(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserEmail = authentication.getName();
            user = userService.getUserByEmail(currentUserEmail);

        }
        model.addAttribute("question", new Question());
        model.addAttribute("user", user);

        return "newquestion";
    }

    @PostMapping("/postQuestion")
    public String saveQuestion(@ModelAttribute("question") Question question) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserEmail = authentication.getName();
            user = userService.getUserByEmail(currentUserEmail);

        }
        Set<Tag> quesTags = new HashSet<>();
        String tagString = question.getTag();
        String[] tags = tagString.split(" ");
        for(String tag:tags ){
            if(tag.length() > 0){
                Tag currTag = new Tag();
                currTag.setTagName(tag.trim());
                quesTags.add(currTag);
            }
        }
        question.setTags(quesTags);
        question.setAuthorId(user.getUserId());
        question.setAuthor(user);
        questionService.save(question);
        return "redirect:/questions";
    }

    @GetMapping("/questions")
    public String showQuestions(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserEmail = authentication.getName();
            user = userService.getUserByEmail(currentUserEmail);

        }
        List<Question> listQuestions = questionService.getAllQuestions();
        model.addAttribute("listQuestions", listQuestions);
        model.addAttribute("user", user);
        return "questions";
    }

    @GetMapping("/questions/{questionId}")
    public String getSelectedQuestion(@PathVariable("questionId") Integer questionId,
                                      Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserEmail = authentication.getName();
            user = userService.getUserByEmail(currentUserEmail);

        }
        Question question = questionService.getQuestionById(questionId);
        model.addAttribute("question", question);
        model.addAttribute("user", user);
        return "question";
    }

    public String showQuestion(Integer questionId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserEmail = authentication.getName();
            user = userService.getUserByEmail(currentUserEmail);

        }
        Question question = questionService.getQuestionById(questionId);
        model.addAttribute("question", question);
        model.addAttribute("user", user);
        return "question";
    }


    @PostMapping("/question/edit/{questionId}")
    public String editQuestion(@PathVariable(value = "questionId") Integer questionId,
                           @RequestParam("title") String title,
                           @RequestParam("description") String description,
                           @RequestParam("tag") String tag,
                           Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserEmail = authentication.getName();
            user = userService.getUserByEmail(currentUserEmail);

        }
        Question question = questionService.getQuestionById(questionId);

        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);

        questionService.save(question);
        return getSelectedQuestion(questionId, model);
    }

    @GetMapping("/update/{questionId}")
    public ModelAndView updatePost(@PathVariable(name = "questionId") Integer questionId){
        ModelAndView editView = new ModelAndView("editquestion");
        if(questionId != null) {
            Question question = questionService.getQuestionById(questionId);
            question.setQuestionId(questionId);
            editView.addObject("question", question);
        }
        return editView;
    }

    @GetMapping("/question/delete/{questionId}")
    public String deleteQuestion(@PathVariable(value = "questionId") Integer questionId) {
        questionService.deletePostById(questionId);
        return "redirect:/questions";
    }
}

