package com.example.bloggingsite.controllers;

import com.example.bloggingsite.domain.Message;
import com.example.bloggingsite.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class GreetingsController {

    private final MessageRepo messageRepo;

    public GreetingsController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Model model) {

        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("messages", messageRepo.findAll());
        return "home";
    }

    @PostMapping
    public String add(@RequestParam String text,
                      @RequestParam String tag,
                      Model model) {

        Message message = new Message(text, tag);
        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);

        return "home";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter,
                         Model model){
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()){
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages", messages);


        return "home";

    }
}