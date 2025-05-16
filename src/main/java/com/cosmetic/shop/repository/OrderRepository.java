package com.cosmetic.shop.repository;

import com.cosmetic.shop.model.Order;
import com.cosmetic.shop.model.OrderStatus;
import com.cosmetic.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.user = :user")
    List<Order> findByUser(@Param("user") User user);

    @Query("SELECT o FROM Order o WHERE o.status = :status")
    List<Order> findByStatus(@Param("status") OrderStatus status);


//    List<Order> findByUserAndStatus(User user, OrderStatus status);
//
//
//    List<Order> findByCreatedAtAfter(LocalDateTime date);
//
//
//    List<Order> findByTotalPriceGreaterThan(Double amount);
}
