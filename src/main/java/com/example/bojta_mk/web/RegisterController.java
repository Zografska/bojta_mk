package com.example.bojta_mk.web;

import com.example.bojta_mk.model.enumerations.Role;
import com.example.bojta_mk.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(Model model){
        model.addAttribute("bodyContent", "register");
        return "master.html";
    }
    @PostMapping
    public String postRegisterPage(@RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String repeatedPassword,
                                   @RequestParam String name,
                                   @RequestParam String surname,
                                   @RequestParam String phone){
        userService.register(username,password,repeatedPassword,name,surname,phone, Role.ROLE_USER);
        return "redirect:/home";
    }
}
