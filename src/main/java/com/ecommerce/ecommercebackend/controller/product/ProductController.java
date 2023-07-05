package com.ecommerce.ecommercebackend.controller.product;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommercebackend.model.product.Product;
import com.ecommerce.ecommercebackend.model.product.ProductCategory;
import com.ecommerce.ecommercebackend.repository.product.ProductCategoryRepository;
import com.ecommerce.ecommercebackend.service.product.ProductService;

@RestController
@RequestMapping("/api/v2/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @GetMapping("/getAll")
    public List<Product> list() {
        return productService.listAll();
    }

    @GetMapping("/getAllCategory")
    public List<ProductCategory> categories() {
        return productCategoryRepository.findAll();
    }

    @GetMapping("/image/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) throws IOException {
        Resource resource = new ClassPathResource("static/images/" + fileName);

        if (resource.exists()) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Product product) {
        productService.save(product);
        return ResponseEntity.ok("Product added successfully");
    }
}
