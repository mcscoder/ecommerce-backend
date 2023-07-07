package com.ecommerce.ecommercebackend.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommercebackend.dto.CartItemDTO;
import com.ecommerce.ecommercebackend.model.product.Product;
import com.ecommerce.ecommercebackend.model.shopping.CartItem;
import com.ecommerce.ecommercebackend.model.shopping.ShoppingSession;
import com.ecommerce.ecommercebackend.model.user.User;
import com.ecommerce.ecommercebackend.repository.product.ProductRepository;
import com.ecommerce.ecommercebackend.repository.shopping.CartItemRepository;
import com.ecommerce.ecommercebackend.repository.shopping.ShoppingSessionRepository;
import com.ecommerce.ecommercebackend.repository.user.UserRepository;
import com.ecommerce.ecommercebackend.security.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShoppingSessionRepository sessionRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/getUser")
    public User getUser(HttpServletRequest request) {
        String email = jwtUtils.extractUsername(request);
        return userRepository.findFirstByEmail(email);
    }

    @GetMapping("/getSession")
    public ShoppingSession getShoppingSession(HttpServletRequest request) {
        String email = jwtUtils.extractUsername(request);
        return sessionRepository.findByUser_Email(email);
    }

    @PostMapping("/addProductToCart")
    public ShoppingSession addProductToCart(HttpServletRequest request, @RequestBody CartItemDTO cartItemDTO) {
        String email = jwtUtils.extractUsername(request);

        Product product = productRepository.findById(cartItemDTO.getProductId()).get();
        ShoppingSession shoppingSession = sessionRepository.findByUser_Email(email);

        CartItem cartItem = CartItem.builder()
                .product(product)
                .quantity(cartItemDTO.getQuantity())
                .shoppingSession(shoppingSession)
                .build();
        cartItemRepository.save(cartItem);

        return sessionRepository.findByUser_Email(email);
    }

    @GetMapping("/getCartProductItems")
    public List<Product> getCartProductItems(HttpServletRequest request) {
        String email = jwtUtils.extractUsername(request);
        
        Integer sessionId = sessionRepository.findByUser_Email(email).getId();

        List<Product> products = new ArrayList<>();
        
        List<CartItem> cartItems = cartItemRepository.findByShoppingSessionId(sessionId);

        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            products.add(product);
        }

        return products;
    }
}
