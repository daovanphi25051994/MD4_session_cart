package com.codegym.service.product;

import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;
    @Override
    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product model) {
        return productRepository.save(model);
    }
}