package com.example.bojta_mk.web;

import com.example.bojta_mk.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String GetCreatePostPage(Model model)
    {
        model.addAttribute("bodyContent","post");
        return "master.html";
    }
    @PostMapping
    public String PostCreatePostPage(@RequestParam String headline,
                                     @RequestParam String description){
        postService.create(headline,description);

        return "redirect:/home";
    }
    @DeleteMapping("/delete/{id}")
    public  String DeletePost(@PathVariable Long id)
    {
        postService.delete(id);

        return "redirect:/home";
    }
}
