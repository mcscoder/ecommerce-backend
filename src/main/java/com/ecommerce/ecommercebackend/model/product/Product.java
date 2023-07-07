package com.ecommerce.ecommercebackend.model.product;

import java.util.List;

import com.ecommerce.ecommercebackend.model.shopping.CartItem;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int quantitySold;
    private int quantity;
    private double price;
    private int rating;
    @Column(length = 5000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "productCategory_id")
    @JsonBackReference
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<ProductDetails> details;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<ProductImage> images;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<CartItem> cartItems;
}
