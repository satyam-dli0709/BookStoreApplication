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
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Autowired
//    private OrderItemsRepository orderItemsRepository;
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    ProductRepository productRepository;
//
//    // Place an order
//    @Override
//    public Order placeOrder(long userId, List<OrderItems> cartItems) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new CustomInvalidException("User not found"));
//
//       // Check if an order already exists for the user
//        Order existingOrder = orderRepository.findByUser(user.getUserId());
//        Order order = new Order();
//        if (existingOrder != null) {
//            // Update the existing order if needed
//            existingOrder.setOrderStatus(OrderStatus.PENDING);
//            order = existingOrder;
//           orderRepository.save(order);
//        } else {
//            // Create a new order if none exists
//            order.setUser(user);
//
//            order.setOrderStatus(OrderStatus.PENDING);
//            order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
//           orderRepository.save(order);
//        }
//
//
//        for(OrderItems orders:cartItems)
//        {
//            Product product = productRepository.findById(orders.getProduct().getId()).orElseThrow(()-> new CustomInvalidException("Product not found"));
//            orders.setPrice(product.getPrice());
//            orders.setProduct(product);
//            orders.setOrder(order);
//           orderItemsRepository.save(orders);
//           order.getOrderItems().add(orders);
//        }
//        order.setOrderItems(cartItems);
//
//
//        double totalAmount = 0.0;
//        for (OrderItems item : cartItems) {
//            item.setOrder(order);
//            int quantity = item.getQuantity();
//            double price = item.getPrice();
//            totalAmount = totalAmount+(quantity*price);
//        }
//        order.setTotalAmount(totalAmount);
//        orderRepository.save(order);
//
//       return order;
//        //orderRepository.save(order);
//    }
//
//    // Update order status
//    public Order updateOrderStatus(long orderId, OrderStatus orderStatus) {
//        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
//        order.setOrderStatus(orderStatus);
//        return orderRepository.save(order);
//    }

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
            User user = userRepository.findById(userId).orElse(null);
            Cart cart = cartRepository.findByUserId(userId);
            List<OrderItems> orderItems = new ArrayList<>();

            for (CartItem cartItem : cart.getCartItems()) {
                OrderItems orderItem = new OrderItems();
              //  Product product = cartItem.getProduct();
                Product product = cartItem.getProducts();
                orderItem.setProduct(product);
                orderItem.setQuantity(cartItem.getQuantity());
//                orderItem.setPrice(BigDecimal.valueOf(product.getProductPrice()));
                orderItem.setPrice((double)(product.getPrice()));
                orderItems.add(orderItem);
            }

            double totalAmount = 0;
            for (OrderItems item : orderItems) {
                double price = item.getPrice();
                double quantity = item.getQuantity();
                totalAmount += (price*quantity);
              //  totalAmount = totalAmount.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            }

            Order order = new Order();
            order.setUser(user);
            order.setTotalAmount(totalAmount);
            order.setOrderStatus(OrderStatus.PENDING);
            order.setCreatedAt(new Timestamp(System.currentTimeMillis()));


            order = orderRepository.save(order);

            for (OrderItems orderItem : orderItems) {
                orderItem.setOrder(order);
                orderItemRepository.save(orderItem);
            }

            order.setOrderItems(orderItems);
            order = orderRepository.save(order);

            cartItemRepository.deleteAll(cart.getCartItems());

            return order;


}
}

