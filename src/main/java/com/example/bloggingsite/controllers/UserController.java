package com.example.bloggingsite.controllers;

import com.example.bloggingsite.domain.Role;
import com.example.bloggingsite.domain.User;
import com.example.bloggingsite.repos.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user,
                               Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }

    @PostMapping
    public String userSave(@RequestParam("userId") User user,
                           @RequestParam Map<String, String> form,
                           @RequestParam String username){

        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()){
            System.out.println(key);
            if (roles.contains(key)){
                System.out.println("Зашел");
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);

        return "redirect:/user";
    }

}
