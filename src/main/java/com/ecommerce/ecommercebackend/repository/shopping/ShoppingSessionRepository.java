package com.ecommerce.ecommercebackend.repository.shopping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommercebackend.model.shopping.ShoppingSession;

@Repository
public interface ShoppingSessionRepository extends JpaRepository<ShoppingSession, Integer> {
    ShoppingSession findByUser_Email(String email);
}
