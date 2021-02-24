package com.example.bojta_mk.web;

import com.example.bojta_mk.model.enumerations.Category;
import com.example.bojta_mk.model.enumerations.Role;
import com.example.bojta_mk.model.exeptions.PasswordsDoNotMatchException;
import com.example.bojta_mk.model.exeptions.UsernameAlreadyExistsException;
import com.example.bojta_mk.model.exeptions.UsernameMustBeAnEmailException;
import com.example.bojta_mk.service.MailService;
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
    private final MailService mailService;
    public RegisterController(UserService userService, MailService mailService) {
        this.userService = userService;
        this.mailService = mailService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model){
        if(error!=null) {
            model.addAttribute("error", error);
            model.addAttribute("hasError",  true);
        }
        else  model.addAttribute("hasError",  false);

        model.addAttribute("bodyContent", "register");
        model.addAttribute("categories", Category.values());
        return "master.html";
    }
    @PostMapping
    public String postRegisterPage(@RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String repeatedPassword,
                                   @RequestParam String name,
                                   @RequestParam String surname,
                                   @RequestParam String phone){
        try {
            userService.register(username, password, repeatedPassword, name, surname, phone, Role.ROLE_USER);
            mailService.sendRegisterConfirmation(username, name, surname);
            return "redirect:/home";
        }catch (IllegalArgumentException | UsernameAlreadyExistsException | PasswordsDoNotMatchException | UsernameMustBeAnEmailException e)
        {
            return "redirect:/register?error="+e.getMessage();
        }
    }
}
