package com.example.firstspringbootsecurityapp.controllers;



import com.example.firstspringbootsecurityapp.exception_handling.IncorrectData;
import com.example.firstspringbootsecurityapp.exception_handling.IncorrectDataException;
import com.example.firstspringbootsecurityapp.models.User;
import com.example.firstspringbootsecurityapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userService;

    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "table";
    }

    @RequestMapping("/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "save";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {

        userService.add(user);

        return "redirect:/admin/";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {

            userService.delete(id);

        return "redirect:/admin/";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id,
                             Model model) {
        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            throw new IncorrectDataException("There is no user with ID: " + id);
        }

        return "update";
    }

    @GetMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user.getId(), user);
        return "redirect:/admin/";
    }


}
