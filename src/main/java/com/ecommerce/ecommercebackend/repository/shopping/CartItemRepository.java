package com.ecommerce.ecommercebackend.repository.shopping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommercebackend.model.shopping.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

}
