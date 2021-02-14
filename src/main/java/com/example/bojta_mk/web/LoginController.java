package com.example.bojta_mk.web;

import com.example.bojta_mk.model.User;
import com.example.bojta_mk.model.exeptions.InvalidUserCredentialsException;
import com.example.bojta_mk.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final UserService authService;

    public LoginController(UserService userService) {
        this.authService = userService;
    }

    @GetMapping
    public String getLoginPage(Model model){
        model.addAttribute("bodyContent", "login");
        return "master.html";
    }
    @PostMapping
    public String postLoginPage(HttpServletRequest request, Model model){
        User user=null;
        try{
            user = authService.login(request.getParameter("username"), request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/home";
        }catch (InvalidUserCredentialsException e){
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            return "/login";
        }
    }
}
