package com.codegym.controller;

import com.codegym.model.Cart;
import com.codegym.model.Customer;
import com.codegym.model.Product;
import com.codegym.model.ProductCart;
import com.codegym.service.cart.ICartService;
import com.codegym.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private ICartService cartService;

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
    public ModelAndView addToCart(@RequestParam Long id, @ModelAttribute("cart") Cart cart) {
        ModelAndView modelAndView = new ModelAndView("product/cart");
        List<ProductCart> productCarts = cart.getProducts();
        boolean isProductExist = cartService.isExists(id, productCarts);
        if (isProductExist) {
            ProductCart productCart = cartService.findOne(id, productCarts);
            productCart.setQuantity(productCart.getQuantity() + 1);
        } else {
            Product product = productService.getOne(id);
            ProductCart productCart = new ProductCart(id, product.getName(), product.getImage(), product.getPrice(), 1);
            productCarts.add(productCart);
        }
        int totalQuantity = cartService.getTotalQuantity(productCarts);
        cart.setTotalQuantity(totalQuantity);
        double totalPrice = cartService.getTotalPrice(productCarts);
        cart.setTotalPrice(totalPrice);
        return modelAndView;
    }

    @GetMapping("/product-plus/{id}")
    public ModelAndView plusProduct(@PathVariable Long id, @ModelAttribute("cart") Cart cart) {
        ModelAndView modelAndView = new ModelAndView("product/cart");
        List<ProductCart> productCarts = cart.getProducts();
        ProductCart productCart = cartService.findOne(id, productCarts);
        productCart.setQuantity(productCart.getQuantity() + 1);
        int totalQuantity = cartService.getTotalQuantity(productCarts);
        cart.setTotalQuantity(totalQuantity);
        double totalPrice = cartService.getTotalPrice(productCarts);
        cart.setTotalPrice(totalPrice);
        return modelAndView;
    }

    @GetMapping("/product-subtraction/{id}")
    public ModelAndView subtractionProduct(@PathVariable Long id, @ModelAttribute("cart") Cart cart) {
        ModelAndView modelAndView = new ModelAndView("product/cart");
        List<ProductCart> productCarts = cart.getProducts();
        ProductCart productCart = cartService.findOne(id, productCarts);
        productCart.setQuantity(productCart.getQuantity() - 1);
        int totalQuantity = cartService.getTotalQuantity(productCarts);
        cart.setTotalQuantity(totalQuantity);
        double totalPrice = cartService.getTotalPrice(productCarts);
        cart.setTotalPrice(totalPrice);
        return modelAndView;
    }

    @GetMapping("/product-delete/{id}")
    public ModelAndView removeProduct(@PathVariable Long id, @ModelAttribute("cart") Cart cart) {
        ModelAndView modelAndView = new ModelAndView("product/cart");
        List<ProductCart> productCarts = cart.getProducts();
        cartService.remove(id, productCarts);
        int totalQuantity = cartService.getTotalQuantity(productCarts);
        cart.setTotalQuantity(totalQuantity);
        double totalPrice = cartService.getTotalPrice(productCarts);
        cart.setTotalPrice(totalPrice);
        return modelAndView;
    }

}