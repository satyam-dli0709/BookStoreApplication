package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.exception.CustomInvalidException;
import com.BookStore.BookStoreApplication.model.*;
import com.BookStore.BookStoreApplication.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderProcessingService implements OrderService {



        @Autowired
        CartRepository cartRepository;
        @Autowired
        OrderRepository orderRepository;
        @Autowired
        UserRepository userRepository;
        @Autowired
        CartItemRepository cartItemRepository;
        @Autowired
        OrderItemsRepository orderItemRepository;

        @Transactional
        @Override
        public Order placeOrder(long userId) {
            User user = userRepository.findById(userId).orElseThrow(() -> new CustomInvalidException("user not found"));
            Cart cart = cartRepository.findByUserId(user.getUserId());
            List<OrderItems> orderItems = new ArrayList<>();

            for (CartItem cartItem : cart.getCartItems()) {
                OrderItems orderItem = new OrderItems();
                Product product = cartItem.getProducts();
                orderItem.setProduct(product);
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setPrice((double) product.getPrice());
                orderItems.add(orderItem);
                cartItemRepository.delete(cartItem);
                cartItemRepository.flush();
            }

            double totalAmount = orderItems.stream()
                    .mapToDouble(item -> item.getPrice() * item.getQuantity())
                    .sum();

            Order order = new Order();
            order.setUser(user);
            order.setTotalAmount(totalAmount);
            order.setOrderStatus(OrderStatus.PENDING);
            order.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            order = orderRepository.save(order); // Save the order first to get the ID

            if (order == null) {
                throw new CustomInvalidException("Order could not be saved");
            }

            for (OrderItems orderItem : orderItems) {
                orderItem.setOrder(order); // Set the order reference in each OrderItems
                orderItemRepository.save(orderItem);
            }

            order.setOrderItems(orderItems); // Set the order items in the order
            order = orderRepository.save(order); // Save the order again to update the order items

            if (order == null) {
                throw new CustomInvalidException("Order could not be updated");
            }

            return order;
        }

}

