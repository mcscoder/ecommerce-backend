package com.ecommerce.ecommercebackend.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommercebackend.model.product.ProductImage;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

}
