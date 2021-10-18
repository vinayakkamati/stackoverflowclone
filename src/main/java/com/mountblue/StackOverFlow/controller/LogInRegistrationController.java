package com.mountblue.StackOverFlow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogInRegistrationController {

//    @GetMapping("/registration")
//    public String createNewUser() {
//        return "registration";
//    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
