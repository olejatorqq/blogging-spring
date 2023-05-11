package com.example.bloggingsite.controllers;

import com.example.bloggingsite.domain.Role;
import com.example.bloggingsite.domain.User;
import com.example.bloggingsite.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    private final UserRepository userRepo;

    public RegistrationController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user,
                          Model model){
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null){
            model.addAttribute("message", "User already exist!");
            return "/registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }

}
