package com.codegym.controller;

import com.codegym.model.Cart;
import com.codegym.model.Customer;
import com.codegym.model.Product;
import com.codegym.model.ProductCart;
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

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("cart")
@RequestMapping("/")
public class HomeController {
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
    public ModelAndView addToCart(@ModelAttribute Product product, @RequestParam String id, @ModelAttribute("cart") Cart cart) {
        ModelAndView modelAndView = new ModelAndView("product/cart");
        for (ProductCart productCart : cart.getProducts()) {
            if (productCart.getId().equals(id)) {
                productCart.setQuantity(productCart.getQuantity() + 1);
                int totalQuantity = 0;
                double totalPrice = 0;
                for (ProductCart productCart1 : cart.getProducts()){git
                    totalQuantity += productCart1.getQuantity();
                    totalPrice += productCart1.getQuantity() * productCart1.getPrice();
                }
                cart.setTotalQuantity(totalQuantity);
                cart.setTotalPrice(totalPrice);
                return modelAndView;
            }
        }
        Long id1 = Long.valueOf(id);
        Product product1 = productService.getOne(id1);
        ProductCart productCart = new ProductCart(id, product1.getName(), product1.getImage(), product1.getPrice(), 1);
        cart.getProducts().add(productCart);
        int totalQuantity = 0;
        double totalPrice = 0;
        for (ProductCart productCart1 : cart.getProducts()){
            totalQuantity += productCart1.getQuantity();
            totalPrice += productCart1.getQuantity() * productCart1.getPrice();
        }
        cart.setTotalQuantity(totalQuantity);
        cart.setTotalPrice(totalPrice);
        return modelAndView;
    }

}