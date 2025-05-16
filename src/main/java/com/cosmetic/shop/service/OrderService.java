package com.cosmetic.shop.service;

import com.cosmetic.shop.model.Order;
import com.cosmetic.shop.model.OrderStatus;
import com.cosmetic.shop.model.User;
import com.cosmetic.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> getUserOrders(User user) {
        return orderRepository.findByUser(user);
    }

    public List<Order> getPendingOrders() {
        return orderRepository.findByStatus(OrderStatus.PENDING);
    }

}
