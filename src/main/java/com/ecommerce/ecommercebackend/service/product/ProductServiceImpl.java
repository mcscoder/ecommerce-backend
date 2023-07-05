package com.ecommerce.ecommercebackend.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommercebackend.model.product.Product;
import com.ecommerce.ecommercebackend.repository.product.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> listAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product get(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
}
