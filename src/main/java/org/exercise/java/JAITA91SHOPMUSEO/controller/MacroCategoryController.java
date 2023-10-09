package org.exercise.java.JAITA91SHOPMUSEO.controller;

import jakarta.validation.Valid;
import org.exercise.java.JAITA91SHOPMUSEO.model.Category;
import org.exercise.java.JAITA91SHOPMUSEO.model.MacroCategory;
import org.exercise.java.JAITA91SHOPMUSEO.model.Product;
import org.exercise.java.JAITA91SHOPMUSEO.repository.CategoryRepository;
import org.exercise.java.JAITA91SHOPMUSEO.repository.MacroCategoryRepository;
import org.exercise.java.JAITA91SHOPMUSEO.repository.ProductRepository;
import org.exercise.java.JAITA91SHOPMUSEO.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/macro-categories")
public class MacroCategoryController {
    private final MacroCategoryRepository macroCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    @Autowired


    public MacroCategoryController(
            MacroCategoryRepository macroCategoryRepository, CategoryRepository categoryRepository,
            CategoryService categoryService, ProductRepository productRepository
    ) {
        this.macroCategoryRepository = macroCategoryRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
        this.productRepository = productRepository;
    }

    @GetMapping
    public String macroCategories(Model model) {
        model.addAttribute("macroCategories", macroCategoryRepository.findAll());
        return "admin/categories/index";
    }

    @GetMapping("/edit/{id}")
    public String editMacroCategory(Model model, @PathVariable Integer id) {
        model.addAttribute("macroCategory", categoryService.getById(id));
        return "admin/categories/edit";
    }

    @PostMapping("/edit/{id}")
    public String editMacroCategory(@Valid @ModelAttribute MacroCategory macroCategory, BindingResult bindingResult, @PathVariable Integer id) {
        if (bindingResult.hasErrors()) {
            return "admin/categories/edit";
        }

        macroCategoryRepository.save(macroCategory);
        return "redirect:/admin/categories";
    }

    @GetMapping("/create")
    public String createCategory(Model model) {
        model.addAttribute("macroCategory", new MacroCategory());
        return "admin/categories/create";
    }

    @PostMapping("/create")
    public String createMacroCategory(@Valid @ModelAttribute MacroCategory macroCategory, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/categories/create";
        }

        macroCategoryRepository.save(macroCategory);
        return "redirect:/admin/categories";
    }

    @PostMapping("/delete/{id}")
    public String deleteMacroCategory(@PathVariable Integer id) {
        Category category = categoryService.getById(id);

        for (Product product : category.getProducts()) {
            product.getCategories().remove(category);
            productRepository.save(product);
        }

        categoryRepository.deleteById(id);

        return "redirect:/admin/categories";
    }

}

