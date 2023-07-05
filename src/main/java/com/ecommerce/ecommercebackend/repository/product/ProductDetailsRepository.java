package com.ecommerce.ecommercebackend.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommercebackend.model.product.ProductDetails;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Integer> {

}
