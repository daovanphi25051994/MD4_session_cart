package com.codegym.controller;

import com.codegym.model.Cart;
import com.codegym.model.Product;
import com.codegym.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("cart")
@RequestMapping("/")
public class HomeController {
    int defaultQuantity = 1;
    @Autowired
    private IProductService productService;

    @ModelAttribute("cart")
    public Cart setUpCart() {
        return new Cart();
    }

    @GetMapping()
    public ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("products", productService.getAll());
        return modelAndView;
    }

    @PostMapping("/addToCart")
    public ModelAndView addToCart(@ModelAttribute Product product, @RequestParam Long id , @ModelAttribute("cart") Cart cart) {
        ModelAndView modelAndView = new ModelAndView("product/cart");
        cart.setId(product.getId());
        cart.setNameProduct(product.getName());
        cart.setQuantity(defaultQuantity);
        return modelAndView;
    }

}