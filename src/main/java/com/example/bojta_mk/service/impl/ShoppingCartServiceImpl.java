package com.example.bojta_mk.service.impl;

import com.example.bojta_mk.model.OrderItem;
import com.example.bojta_mk.model.ShoppingCart;
import com.example.bojta_mk.model.User;
import com.example.bojta_mk.model.enumerations.ShoppingCartStatus;
import com.example.bojta_mk.model.exeptions.OrderItemAlreadyInShoppingCart;
import com.example.bojta_mk.model.exeptions.ShoppingCartNotFoundException;
import com.example.bojta_mk.model.exeptions.UserNotFoundException;
import com.example.bojta_mk.repository.ShoppingCartRepository;
import com.example.bojta_mk.repository.UserRepository;
import com.example.bojta_mk.service.OrderItemService;
import com.example.bojta_mk.service.ProductService;
import com.example.bojta_mk.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductService productService;
    private final OrderItemService orderItemService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   UserRepository userRepository,
                                   ProductService productService, OrderItemService orderItemService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
        this.orderItemService = orderItemService;
    }

    @Override
    public List<OrderItem> listAllProductsInShoppingCart(Long cartId) {
        if (this.shoppingCartRepository.findById(cartId).isEmpty()){
            throw new ShoppingCartNotFoundException(cartId);
        }
        return this.shoppingCartRepository.findById(cartId).get().getProductList();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {

        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return  this.shoppingCartRepository
                .findByUserAndStatus(user, ShoppingCartStatus.ACTIVE)
                .orElseGet(() -> {
                    ShoppingCart shoppingCart = new ShoppingCart(user);
                    List<ShoppingCart> carts = new ArrayList<>();
                    carts.add(shoppingCart);
                    user.setShoppingCarts(carts);
                    return this.shoppingCartRepository.save(shoppingCart);
                });
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long orderItemId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        OrderItem orderItem = this.orderItemService.findById(orderItemId);

        if (shoppingCart.getProductList().stream().anyMatch(x -> x.getId().equals(orderItemId))){
            throw new OrderItemAlreadyInShoppingCart();
        }


        shoppingCart.getProductList().add(orderItem);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart removeProductFromShoppingCart(String username, Long orderItemId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        OrderItem orderItem = this.orderItemService.findById(orderItemId);

        shoppingCart.getProductList().remove(orderItem);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}

