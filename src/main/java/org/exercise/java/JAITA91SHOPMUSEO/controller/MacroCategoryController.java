package org.exercise.java.JAITA91SHOPMUSEO.controller;

import jakarta.validation.Valid;
import org.exercise.java.JAITA91SHOPMUSEO.model.Category;
import org.exercise.java.JAITA91SHOPMUSEO.model.MacroCategory;
import org.exercise.java.JAITA91SHOPMUSEO.repository.CategoryRepository;
import org.exercise.java.JAITA91SHOPMUSEO.repository.MacroCategoryRepository;
import org.exercise.java.JAITA91SHOPMUSEO.service.MacroCategoryService;
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
    private final MacroCategoryService macroCategoryService;

    @Autowired
    public MacroCategoryController(
            MacroCategoryRepository macroCategoryRepository,
            CategoryRepository categoryRepository,
            MacroCategoryService macroCategoryService
    ) {
        this.macroCategoryRepository = macroCategoryRepository;
        this.categoryRepository = categoryRepository;
        this.macroCategoryService = macroCategoryService;
    }

    @GetMapping("/edit/{id}")
    public String editMacroCategory(Model model, @PathVariable Integer id) {
        model.addAttribute("macroCategory", macroCategoryService.getById(id));
        return "admin/macro-categories/edit";
    }

    @PostMapping("/edit/{id}")
    public String editMacroCategory(
            @Valid @ModelAttribute MacroCategory macroCategory,
            BindingResult bindingResult,
            @PathVariable Integer id
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/macro-categories/edit";
        }

        macroCategoryRepository.save(macroCategory);
        return "redirect:/admin/categories";
    }

    @GetMapping("/create")
    public String createMacroCategory(Model model) {
        model.addAttribute("macroCategory", new MacroCategory());
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/macro-categories/create";
    }

    @PostMapping("/create")
    public String createMacroCategory(
            @Valid @ModelAttribute MacroCategory macroCategory,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/macro-categories/create";
        }

        macroCategoryRepository.save(macroCategory);
        return "redirect:/admin/categories";
    }

    @PostMapping("/delete/{id}")
    public String deleteMacroCategory(@PathVariable Integer id) {
        MacroCategory macroCategory = macroCategoryService.getById(id);

        for (Category category : macroCategory.getCategories()) {
            category.setMacroCategory(null);
            categoryRepository.save(category);
        }

        macroCategoryRepository.deleteById(id);

        return "redirect:/admin/categories";
    }

}

