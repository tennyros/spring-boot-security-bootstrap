package com.github.tennyros.bootstrap.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.github.tennyros.bootstrap.models.User;

@Controller
public class UserController {

    @GetMapping(value = "/user")
    public String showUserInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "user";
    }
}
