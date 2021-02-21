package com.example.bojta_mk.web;

import com.example.bojta_mk.model.OrderItem;
import com.example.bojta_mk.model.ShoppingCart;
import com.example.bojta_mk.model.enumerations.Category;
import com.example.bojta_mk.model.Product;
import com.example.bojta_mk.model.Shape;
import com.example.bojta_mk.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product-categories")
public class ProductController {

    private final ProductService productService;
    private final ShapeService shapeService;
    private final ShoppingCartService shoppingCartService;
    private final OrderItemService orderItemService;

    public ProductController(ProductService productService, ShapeService shapeService, ShoppingCartService shoppingCartService, OrderItemService orderItemService) {
        this.productService = productService;
        this.shapeService = shapeService;
        this.shoppingCartService = shoppingCartService;
        this.orderItemService = orderItemService;
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
                              @RequestParam Category category,
                              @RequestParam String shape){
        Shape s = this.shapeService.findById(shape);
        this.productService.create(id, category, s, name);
        return "redirect:/product-categories";
    }

    @DeleteMapping("/{cat}/delete/{id}")
    public String deleteProduct(@PathVariable String cat,
                                @PathVariable String id){
        List<ShoppingCart> shoppingCarts = this.shoppingCartService.findAll();
        List<Long> toDelete = new ArrayList<>();
        for (ShoppingCart sh : shoppingCarts) {
            List<OrderItem> newList = new ArrayList<>();
            for (OrderItem o : sh.getProductList())
                if (!o.getProduct().getId().equals(id)) {
                    newList.add(o);
                    System.out.println(o.getProduct().getId());
                }else{
                    toDelete.add(o.getId());
                }
            sh.setProductList(newList);
        }

        for (Long i : toDelete) this.orderItemService.deleteById(i);
        this.productService.deleteById(id);
        return "redirect:/product-categories/{cat}";
    }
    @GetMapping("/search")
    public String searchProductsPage(@RequestParam String str, Model model){
        model.addAttribute("bodyContent", "concrete-category");
        List<Product> products = productService.findByNameOrId(str);
        model.addAttribute("category", "all");
        model.addAttribute("products", products);
        model.addAttribute("search", true);
        return "master.html";
    }
}
