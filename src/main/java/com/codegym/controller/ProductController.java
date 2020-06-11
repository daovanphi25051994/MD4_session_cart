package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Product;
import com.codegym.model.ProductForm;
import com.codegym.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    Environment env;

    @GetMapping("/product-list")
    public ModelAndView moveToProductListPage(){
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("products", productService.getAll());
        return modelAndView;
    }
    @GetMapping("/product-add")
    public ModelAndView moveToProductFormPage(){
        ModelAndView modelAndView = new ModelAndView("product/add-form");
        modelAndView.addObject("productForm", new ProductForm());
        return modelAndView;
    }
    @PostMapping("/product-add")
    public ModelAndView createProduct(@ModelAttribute ProductForm productForm){
        ModelAndView modelAndView = new ModelAndView("product/add-form");
        Product product = new Product(productForm.getName(), null, productForm.getPrice());
        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();
        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setImage(fileName);
        Product product1 = productService.save(product);
        if (product1 == null){
            modelAndView.addObject("message", "errors");
        }else {
            modelAndView.addObject("message", "ok");
        }
        modelAndView.addObject("productForm", new ProductForm());
        modelAndView.addObject("message", "successfully !!");
        return modelAndView;
    }
}