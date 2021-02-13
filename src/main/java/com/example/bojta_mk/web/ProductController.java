package com.example.bojta_mk.web;

import com.example.bojta_mk.model.enumerations.Category;
import com.example.bojta_mk.model.Product;
import com.example.bojta_mk.model.Shape;
import com.example.bojta_mk.service.ProductService;
import com.example.bojta_mk.service.ShapeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product-categories")
public class ProductController {
    //TODO: Plan out Products main page
    //TODO: Create mappings for different product categories

    private final ProductService productService;
    private final ShapeService shapeService;

    public ProductController(ProductService productService, ShapeService shapeService) {
        this.productService = productService;
        this.shapeService = shapeService;
    }

    @GetMapping
    public String getProductsCategoriesPage(Model model){
        model.addAttribute("bodyContent", "product-categories");
        model.addAttribute("categories", Category.values());
        return "master";
    }

    @GetMapping("/{cat}")
    public String getConcreteCategoryPage(Model model,
                                          @PathVariable Category cat){

        List<Product> productsPerCategory = productService.findByCategory(cat);
        model.addAttribute("bodyContent", "concrete-category");
        model.addAttribute("category", cat);
        model.addAttribute("products", productsPerCategory);

        return "master";
    }

    @GetMapping("/{cat}/add-form")
    public String getAddForm(Model model,
                             @PathVariable String cat){
        model.addAttribute("bodyContent", "form");
        model.addAttribute("category", cat);
        model.addAttribute("categories", Category.values());
        model.addAttribute("shapes", shapeService.findAll());
        return "master";
    }

    @GetMapping("/{cat}/edit-form/{id}")
    public String getAddForm(Model model,
                             @PathVariable String cat,
                             @PathVariable String id){

        Product product = productService.findById(id);
        model.addAttribute("bodyContent", "form");
        model.addAttribute("category", cat);
        model.addAttribute("categories", Category.values());
        model.addAttribute("shapes", shapeService.findAll());
        model.addAttribute("product", product);

        return "master";
    }

    @GetMapping("/{cat}/{id}")
    public String getDetailsPage(Model model,
                                 @PathVariable String cat,
                                 @PathVariable String id){

        Product product = this.productService.findById(id);
        model.addAttribute("bodyContent", "details");
        model.addAttribute("category", cat);
        model.addAttribute("product", product);
        return "master";
    }

    @PostMapping("/add")
    public String saveProduct(@RequestParam String id,
                              @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Category category,
                              @RequestParam String shape){
        Shape s = this.shapeService.findById(shape);
        this.productService.create(id, category, s, name, description);
        return "redirect:/product-categories";
    }

    @DeleteMapping("/{cat}/delete/{id}")
    public String deleteProduct(@PathVariable String cat,
                                @PathVariable String id){
        this.productService.deleteById(id);
        return "redirect:/product-categories/{cat}";
    }
}
