package com.ecommerce.ecommercebackend.service.product;

import java.util.List;

import com.ecommerce.ecommercebackend.model.product.Product;

public interface ProductService {
    List<Product> listAll();

    void save(Product product);

    Product get(Integer id);

    void delete(Integer id);
}
