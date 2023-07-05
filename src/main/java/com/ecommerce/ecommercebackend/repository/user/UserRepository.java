package com.ecommerce.ecommercebackend.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommercebackend.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findFirstByEmail(String email);
}
