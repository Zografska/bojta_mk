package com.example.bojta_mk.web;


import com.example.bojta_mk.model.Post;
import com.example.bojta_mk.model.enumerations.Category;
import com.example.bojta_mk.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = {"/", "/home"})

public class HomeController {
    private final PostService postService;

    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String getHomePage(Model model){
        model.addAttribute("bodyContent", "home");
        model.addAttribute("posts", postService.listAll());
        model.addAttribute("categories", Category.values());
        return "master.html";
    }
    @GetMapping("/zaNas")
    public String getZaNasPage(Model model){
        model.addAttribute("bodyContent", "zaNas");
        model.addAttribute("categories", Category.values());
        return "master.html";
    }
}