package com.example.bojta_mk.web;

import com.example.bojta_mk.model.User;
import com.example.bojta_mk.model.enumerations.Category;
import com.example.bojta_mk.service.MailService;
import com.example.bojta_mk.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/contact")
public class ContactFormController {
    private final MailService mailService;
    private final UserService userService;

    public ContactFormController(MailService mailService, UserService userService) {
        this.mailService = mailService;
        this.userService = userService;
    }

    @PostMapping
    public String getQuestionSentPage(@RequestParam(required = false) String error,@RequestParam String question, HttpServletRequest req, Model model) {
        String username = req.getRemoteUser();
        User user = userService.findByUsername(username);
        String message = getMessage(user,question);

        this.mailService.sendQuestion(message);
        model.addAttribute("bodyContent", "question-sent");
        model.addAttribute("categories", Category.values());
        return "master.html";
    }
    public String getMessage(User user, String question){
        return "Question sent from: " +
                user.getName() + " " +
                user.getSurname() +
                "\n" +
                "Contact number: " +
                user.getPhone() +
                "\n"
                +"Question: "
                +question;
    }
}
