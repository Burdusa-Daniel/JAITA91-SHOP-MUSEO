package org.exercise.java.JAITA91SHOPMUSEO.controller;


import org.exercise.java.JAITA91SHOPMUSEO.model.Product;
import org.exercise.java.JAITA91SHOPMUSEO.repository.ProductRepository;
import org.exercise.java.JAITA91SHOPMUSEO.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductController {

    private final ProductService productService;

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(
            ProductService productService, ProductRepository productRepository
    ) {
        this.productService = productService;
        this.productRepository = productRepository;
    }


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products/index";
    }

    @GetMapping("/admin")
    public String homepage(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "admin/index";
    }

    //-----------Detail----------------------------

    @GetMapping("/products/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("products", productService.getById(id));
        return "products/detail";
    }

    @GetMapping("admin/products/{id}")
    public String detailAdmin(@PathVariable Integer id, Model model) {
        model.addAttribute("products", productService.getById(id));
        return "admin/detail";
    }


    //---------------Create-----------------------


    @GetMapping("/admin/create")
    public String create(Model model) {
        model.addAttribute("products", new Product());
        return "admin/create";
    }

    @PostMapping("/admin/create")
    public String doCreate(
            @ModelAttribute("product") Product productForm,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/create";
        }
        productRepository.save(productForm);
        return "redirect:/admin/";
    }

    //---------Edit------------------

    @GetMapping("/admin/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("prodotti", productService.getById(id));
        return "admin/edit";
    }

    @PostMapping("admin/edit/{id}")
    public String doEdit(
            @PathVariable Integer id,
            @ModelAttribute("product") Product productForm,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/edit";
        }
        productRepository.save(productForm);
        return "redirect:/admin";
    }

    @PostMapping("/admin/products/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productRepository.deleteById(id);
        return "redirect:/admin";
    }

}
