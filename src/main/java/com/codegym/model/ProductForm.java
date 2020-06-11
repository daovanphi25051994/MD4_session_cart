package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductForm {
    private Long id;
    private String name;
    private MultipartFile image;
    private float price;

    public ProductForm() {
    }

    public ProductForm(Long id, String name, MultipartFile image, float price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}