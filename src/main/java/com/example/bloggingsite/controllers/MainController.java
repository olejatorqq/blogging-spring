package com.example.bloggingsite.controllers;

import com.example.bloggingsite.domain.Message;
import com.example.bloggingsite.domain.User;
import com.example.bloggingsite.repos.MessageRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {

    private final MessageRepository messageRepository;

    public MainController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public String greeting(Model model) {
        return "greeting";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("messages", messageRepository.findAll());
        return "home";
    }

    @PostMapping("/home")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String text,
                      @RequestParam String tag,
                      Model model) {

        Message message = new Message(text, tag, user);
        messageRepository.save(message);

        Iterable<Message> messages = messageRepository.findAll();

        model.addAttribute("messages", messages);

        return "home";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter,
                         Model model){
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()){
            messages = messageRepository.findByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }
        model.addAttribute("messages", messages);
        return "home";
    }
}