package com.codegym.model;

public class Cart {
    private Long id;
    private String nameProduct;
    private int quantity;

    public Cart() {
    }

    public Cart(Long id, String nameProduct, int quantity) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}