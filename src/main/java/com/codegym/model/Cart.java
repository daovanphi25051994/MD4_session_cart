package com.codegym.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<ProductCart> products;
    private int totalQuantity;
    private double totalPrice;

    public Cart() {
        products = new ArrayList<>();
        totalQuantity = 0;
        totalPrice = 0;
    }

    public Cart(List<ProductCart> products, int totalQuantity, double totalPrice) {
        this.products = products;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }

    public List<ProductCart> getProducts() {
        return products;
    }

    public void setProducts(List<ProductCart> products) {
        this.products = products;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}