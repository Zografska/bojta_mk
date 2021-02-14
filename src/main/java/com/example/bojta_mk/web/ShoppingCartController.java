package com.example.bojta_mk.web;

import com.example.bojta_mk.model.OrderItem;
import com.example.bojta_mk.model.Product;
import com.example.bojta_mk.model.ShoppingCart;
import com.example.bojta_mk.service.OrderItemService;
import com.example.bojta_mk.service.ProductService;
import com.example.bojta_mk.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    private final OrderItemService orderItemService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService, OrderItemService orderItemService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error, HttpServletRequest req, Model model){
        if (error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }


        //User user = (User) req.getSession().getAttribute("user");
        String username = req.getRemoteUser();
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username);
        List<OrderItem> orderItemList = this.shoppingCartService.listAllProductsInShoppingCart(shoppingCart.getId());
        model.addAttribute("orderItems", this.shoppingCartService.listAllProductsInShoppingCart(shoppingCart.getId()));
        model.addAttribute("bodyContent", "shopping-cart");
        model.addAttribute("username", username);

        return "master";
    }

    @PostMapping("/add/{id}")
    public String addProductToCart(@PathVariable String id,
                                   @RequestParam String dimension,
                                   HttpServletRequest req){
        try{
            String username = req.getRemoteUser();
            Product product = this.productService.findById(id);
            if (this.orderItemService.findByProductAndDimension(product, dimension).isEmpty())
                this.orderItemService.create(product, dimension);

            Long orderItemId = this.orderItemService.findByProductAndDimension(product, dimension).get().getId();

            this.shoppingCartService.addProductToShoppingCart(username, orderItemId);
            return "redirect:/cart";
        }catch (RuntimeException e){
            return "redirect:/cart?error=" + e.getMessage();
        }
    }

    @DeleteMapping("delete/{id}")
    public String removeProductFromCart(@PathVariable Long id,
                                        HttpServletRequest req){
        try{
            String username = req.getRemoteUser();
            this.shoppingCartService.removeProductFromShoppingCart(username, id);
            return "redirect:/cart";
        }catch (RuntimeException e){
            return "redirect:/cart?error=" + e.getMessage();
        }
    }

    @PostMapping("/quantity/{id}")
    public String editProduct(@PathVariable Long id,
                              @RequestParam int quantity)
    {
        orderItemService.editQuantity(id,quantity);
        return "redirect:/cart";
    }
}
