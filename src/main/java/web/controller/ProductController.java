package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {
    //TODO: Plan out Products main page
    //TODO: Create mappings for different product categories

    @GetMapping
    public String getProductsPage(Model model){
        model.addAttribute("bodyContent", "home");
        return "master.html";
    }
}
