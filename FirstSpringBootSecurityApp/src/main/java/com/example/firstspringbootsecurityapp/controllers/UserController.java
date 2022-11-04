package com.example.firstspringbootsecurityapp.controllers;


import com.example.firstspringbootsecurityapp.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user")

    public String userInfo(Model model) {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("userInfo", user);
        return "user";
    }

}
