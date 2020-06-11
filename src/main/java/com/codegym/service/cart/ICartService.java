package com.codegym.service.cart;

import com.codegym.model.ProductCart;

import java.util.List;

public interface ICartService {
    int getTotalQuantity(List<ProductCart> products);

    double getTotalPrice(List<ProductCart> products);

    boolean isExists(Long id, List<ProductCart> products);

    ProductCart findOne(Long id, List<ProductCart> products);

    boolean remove(Long id, List<ProductCart> products);

}
