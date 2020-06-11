package com.codegym.service.cart;

import com.codegym.model.Product;
import com.codegym.model.ProductCart;

import java.util.List;

public class CartService implements ICartService {
    @Override
    public int getTotalQuantity(List<ProductCart> productCarts) {
        int totalQuantity = 0;
        for (ProductCart productCart : productCarts) {
            totalQuantity += productCart.getQuantity();
        }
        return totalQuantity;
    }

    @Override
    public double getTotalPrice(List<ProductCart> productCarts) {
        double totalPrice = 0;
        for (ProductCart productCart : productCarts) {
            totalPrice += productCart.getQuantity() * productCart.getPrice();
        }
        return totalPrice;
    }

    @Override
    public boolean isExists(Long id, List<ProductCart> products) {
        for (ProductCart productCart : products) {
            if (productCart.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ProductCart findOne(Long id, List<ProductCart> products) {
        for (ProductCart productCart : products) {
            if (productCart.getId().equals(id)) {
                return productCart;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Long id, List<ProductCart> products) {
        ProductCart product = findOne(id, products);
        if (product != null) {
            products.remove(product);
            return true;
        } else {
            return false;
        }
    }
}