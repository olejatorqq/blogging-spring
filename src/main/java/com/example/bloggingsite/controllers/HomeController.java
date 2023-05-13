package com.example.bloggingsite.controllers;

import com.example.bloggingsite.domain.Message;
import com.example.bloggingsite.domain.User;
import com.example.bloggingsite.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.multi.MultiButtonUI;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class HomeController {

    private final MessageRepository messageRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public HomeController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/home")
    public String home(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepository.findByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "home";
    }

    @PostMapping("/home")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String text,
                      @RequestParam String tag,
                      @RequestParam("file") MultipartFile file,
                      Model model) throws IOException {

        Message message = new Message(text, tag, user);

        if (!file.isEmpty()){
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            message.setFilename(resultFilename);
        }

        messageRepository.save(message);

        Iterable<Message> messages = messageRepository.findAll();

        model.addAttribute("messages", messages);

        return "redirect:/home";
    }

}