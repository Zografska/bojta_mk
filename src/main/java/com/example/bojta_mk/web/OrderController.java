package com.example.bojta_mk.web;

import com.example.bojta_mk.model.Order;
import com.example.bojta_mk.model.OrderItem;
import com.example.bojta_mk.model.ShoppingCart;
import com.example.bojta_mk.model.enumerations.OrderStatus;
import com.example.bojta_mk.service.MailService;
import com.example.bojta_mk.service.OrderService;
import com.example.bojta_mk.service.PdfGeneratorService;
import com.example.bojta_mk.service.ShoppingCartService;
import com.itextpdf.text.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final PdfGeneratorService pdfGeneratorService;
    private final MailService mailService;

    public OrderController(OrderService orderService, ShoppingCartService shoppingCartService, PdfGeneratorService pdfGeneratorService, MailService mailService) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.pdfGeneratorService = pdfGeneratorService;
        this.mailService = mailService;
    }

    @GetMapping
    public String getOrderSentPage(@RequestParam(required = false) String error, HttpServletRequest req, Model model) {

        String username = req.getRemoteUser();
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username);
        Order order = this.orderService.findByShoppingCart(shoppingCart);

        String email = shoppingCart.getUser().getUsername();
        String name = shoppingCart.getUser().getName();
        Long id = order.getId();

        this.shoppingCartService.deactivateConfirmedShoppingCart(shoppingCart.getId());

        try {
            pdfGeneratorService.init(order);
            this.mailService.sendOrderMail(name, id);
            model.addAttribute("bodyContent", "order-sent");
            return "master";
        } catch (FileNotFoundException | DocumentException e) {
            return "redirect:/order?error=" + e.getMessage();
        }
    }

    @GetMapping("/page")
    public String getOrdersPage(Model model) {
        List<OrderStatus> orderStatusList = new ArrayList<>();
        orderStatusList.add(OrderStatus.TO_COMPLETE);
        orderStatusList.add(OrderStatus.COMPLETED);
        orderStatusList.add(OrderStatus.TO_DELETE);
        model.addAttribute("orders", orderService.findAll());
        model.addAttribute("statuses", orderStatusList);
        model.addAttribute("bodyContent", "orders");
        return "master";
    }

    @PostMapping("/change-status/{id}")
    public String changeOrder(@PathVariable Long id, @RequestParam OrderStatus status) {
        orderService.changeStatus(id, status);
        return "redirect:/order/page";
    }

    @GetMapping("/details/{id}")
    public String OrderDetials(@PathVariable Long id, Model model) {
        Order order = orderService.findById(id);
        List<OrderItem> items = order.getShoppingCart().getProductList();
        model.addAttribute("order", order);
        model.addAttribute("products", items);
        model.addAttribute("bodyContent", "order-details");
        return "master";
    }

    @DeleteMapping("/delete/{id}")
    public String OrderDetials(@PathVariable Long id) {
        orderService.delete(id);
        return "redirect:/order/page";
    }
}