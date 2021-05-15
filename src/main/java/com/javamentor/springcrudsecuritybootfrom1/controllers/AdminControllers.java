package com.javamentor.springcrudsecuritybootfrom1.controllers;

import com.javamentor.springcrudsecuritybootfrom1.Model.User;
import com.javamentor.springcrudsecuritybootfrom1.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AdminControllers {
    private UserServiceImpl userService;

    @Autowired
    public AdminControllers(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);

        return "login";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/users/admin")
    public String getAllUsers(ModelMap modelMap, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());

        //modelMap.addAttribute("users", userService.getAllUsers());
        modelMap.addAttribute("principalUsername", principal.getName());
        modelMap.addAttribute("user", userService.getUserByUsername(principal.getName()));
        modelMap.addAttribute("userRolesPrefixFree", user.getUserRolesPrefixFree());


        return "getAllUsers";
    }

    @GetMapping("/users/user")
    public String getUserByLogin(ModelMap modelMap, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());

        modelMap.addAttribute("user", userService.getUserByUsername(principal.getName()));
        modelMap.addAttribute("userRolesPrefixFree", user.getUserRolesPrefixFree());
        modelMap.addAttribute("principalUsername", principal.getName());

        return "user";
    }


}
