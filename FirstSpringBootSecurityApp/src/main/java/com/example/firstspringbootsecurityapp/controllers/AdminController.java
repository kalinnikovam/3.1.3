package com.example.firstspringbootsecurityapp.controllers;




import com.example.firstspringbootsecurityapp.exception_handling.IncorrectDataException;
import com.example.firstspringbootsecurityapp.models.Role;
import com.example.firstspringbootsecurityapp.models.User;
import com.example.firstspringbootsecurityapp.service.RoleService;
import com.example.firstspringbootsecurityapp.service.UserService;
import com.example.firstspringbootsecurityapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
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

        model.addAttribute("roles", roleService.allRoles());

        return "save";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(value = "select-role") String[] roleNames) {

        System.out.println(user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRoles(roleService.getRoleByNames(roleNames));

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
            model.addAttribute("roles", roleService.allRoles());
        } else {
            throw new IncorrectDataException("There is no user with ID: " + id);
        }

        return "update";
    }

    @GetMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
    @RequestParam(value = "select-role") String[] roleNames) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roleService.getRoleByNames(roleNames));

        userService.update(user.getId(), user);
        return "redirect:/admin/";
    }

}
