package com.example.bojta_mk.service.impl;

import com.example.bojta_mk.model.OrderItem;
import com.example.bojta_mk.model.Product;
import com.example.bojta_mk.model.exeptions.OrderItemNotFoundException;
import com.example.bojta_mk.repository.OrderItemRepository;
import com.example.bojta_mk.service.OrderItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<OrderItem> findAll() {
        return this.orderItemRepository.findAll();
    }

    @Override
    public OrderItem findById(Long id) {
        return this.orderItemRepository.findById(id).orElseThrow(() -> new OrderItemNotFoundException(id));
    }

    @Override
    public OrderItem create(Product p, String dimension) {
        return this.orderItemRepository.save(new OrderItem(p, dimension,1));
    }

    @Override
    public Optional<OrderItem> findByProductAndDimension(Product p, String dimension) {
        return this.orderItemRepository.findByProductAndDimension(p, dimension);
    }

    @Override
    public void deleteById(Long id) {
        this.orderItemRepository.deleteById(id);
    }

    @Override
    public OrderItem editQuantity(Long id, int quantity) {
        OrderItem orderItem = findById(id);
        orderItem.setQuantity(quantity);
        orderItemRepository.save(orderItem);
        return orderItem;
    }
}
