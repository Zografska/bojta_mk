package com.example.bojta_mk.web;

import com.example.bojta_mk.model.Order;
import com.example.bojta_mk.model.ShoppingCart;
import com.example.bojta_mk.service.OrderService;
import com.example.bojta_mk.service.PdfGeneratorService;
import com.example.bojta_mk.service.ShoppingCartService;
import com.itextpdf.text.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;

@Controller
@RequestMapping("/order-sent")
public class OrderController {

    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final PdfGeneratorService pdfGeneratorService;

    public OrderController(OrderService orderService, ShoppingCartService shoppingCartService, PdfGeneratorService pdfGeneratorService) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping
    public String getOrderSentPage(@RequestParam(required = false) String error, HttpServletRequest req, Model model){

        String username = req.getRemoteUser();
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username);
        Order order = this.orderService.findByShoppingCart(shoppingCart);
        this.shoppingCartService.deactivateConfirmedShoppingCart(shoppingCart.getId());

        try {
            pdfGeneratorService.init(order);
        }catch (FileNotFoundException | DocumentException e){
            return "redirect:/order-sent?error=" + e.getMessage();
        }

        model.addAttribute("bodyContent", "order-sent");
        return "master";
    }
}
