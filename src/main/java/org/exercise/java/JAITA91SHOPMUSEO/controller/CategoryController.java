package org.exercise.java.JAITA91SHOPMUSEO.controller;

import jakarta.validation.Valid;
import org.exercise.java.JAITA91SHOPMUSEO.model.Category;
import org.exercise.java.JAITA91SHOPMUSEO.model.Product;
import org.exercise.java.JAITA91SHOPMUSEO.repository.CategoryRepository;
import org.exercise.java.JAITA91SHOPMUSEO.repository.ProductRepository;
import org.exercise.java.JAITA91SHOPMUSEO.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    @Autowired
    public CategoryController(
            CategoryRepository categoryRepository,
            CategoryService categoryService, ProductRepository productRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
        this.productRepository = productRepository;
    }

    @GetMapping
    public String categories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/categories/index";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(Model model, @PathVariable Integer id) {
        model.addAttribute("category", categoryService.getById(id));
        return "admin/categories/edit";
    }

    @PostMapping("/edit/{id}")
    public String editCategory(@Valid @ModelAttribute Category category, BindingResult bindingResult, @PathVariable Integer id) {
        if (bindingResult.hasErrors()) {
            return "admin/categories/edit";
        }

        categoryRepository.save(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/create")
    public String createCategory(Model model) {
        model.addAttribute("category", new Category());
        return "admin/categories/create";
    }

    @PostMapping("/create")
    public String createCategory(@Valid @ModelAttribute Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/categories/create";
        }

        categoryRepository.save(category);
        return "redirect:/admin/categories";
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        Category category = categoryService.getById(id);

        for (Product product : category.getProducts()) {
            product.getCategories().remove(category);
            productRepository.save(product);
        }

        categoryRepository.deleteById(id);

        return "redirect:/admin/categories";
    }

}

