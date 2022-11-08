package com.example.firstspringbootsecurityapp.controllers;


import com.example.firstspringbootsecurityapp.models.Role;
import com.example.firstspringbootsecurityapp.models.User;
import com.example.firstspringbootsecurityapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {


    private final RoleService roleService;

    @Autowired
    public UserController(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping("/user")
    public String userInfo(Model model) {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("userInfo", user);
        model.addAttribute("admin-role", "ROLE_ADMIN");
        return "user";
    }

}
