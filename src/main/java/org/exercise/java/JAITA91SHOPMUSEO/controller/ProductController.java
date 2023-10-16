package org.exercise.java.JAITA91SHOPMUSEO.controller;


import jakarta.validation.Valid;
import org.exercise.java.JAITA91SHOPMUSEO.model.*;
import org.exercise.java.JAITA91SHOPMUSEO.repository.*;
import org.exercise.java.JAITA91SHOPMUSEO.security.DatabaseUserDetails;
import org.exercise.java.JAITA91SHOPMUSEO.service.CategoryService;
import org.exercise.java.JAITA91SHOPMUSEO.service.ProductService;
import org.exercise.java.JAITA91SHOPMUSEO.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class ProductController {

    private final ProductService productService;

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;
    private final OrderRepository orderRepository;
    private final AssortmentRepository assortmentRepository;
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final MacroCategoryRepository macroCategoryRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(
            ProductService productService,
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            OrderRepository orderRepository,
            AssortmentRepository assortmentRepository,
            ReviewRepository reviewRepository,
            UserService userService,
            UserRepository userRepository,
            MacroCategoryRepository macroCategoryRepository,
            CategoryService categoryService
    ) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.orderRepository = orderRepository;
        this.assortmentRepository = assortmentRepository;
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.macroCategoryRepository = macroCategoryRepository;
        this.categoryService = categoryService;
    }


    @GetMapping("/")
    public String index(
            Model model,
            @RequestParam(value = "q", required = false) String query,
            @RequestParam(value = "category", required = false) Integer categoryId
    ) {


        model.addAttribute("topProducts", productService.getTopProducts());
        model.addAttribute("mostVoted", productService.getMostVoted());


        model.addAttribute("macroCategories",
                           macroCategoryRepository.findAll()
        );
        return "products/index";
    }

    @GetMapping("/admin/restocks")
    public String restocks(Model model) {
        model.addAttribute("assortments", assortmentRepository.findAll());

        return "admin/products/restock-history";
    }

    @GetMapping("/admin/orders")
    public String orders(Model model) {
        model.addAttribute("orders", orderRepository.findAll());

        return "admin/products/order-history";
    }

    @GetMapping("/shop")
    public String shop(
            Model model,
            @RequestParam(value = "q", required = false) String query,
            @RequestParam(value = "category", required = false) Integer categoryId
    ) {
        if (query != null) {
            model.addAttribute("products",
                               productRepository.findAllByNameContaining(query)
            );
        } else {
            if (categoryId != null) {
                model.addAttribute("products",
                                   categoryService.getById(categoryId)
                                                  .getProducts()
                );
            } else {
                model.addAttribute("products", productRepository.findAll());
            }
        }

        model.addAttribute("macroCategories",
                           macroCategoryRepository.findAll()
        );
        return "products/shop";
    }

    @GetMapping("/admin")
    public String homepage(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "admin/products/index";
    }

    //-----------Detail----------------------------

    @GetMapping("/products/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getById(id));
        model.addAttribute("order", new Order());
        model.addAttribute("review", new Review());

        return "products/detail";
    }

    @GetMapping("admin/products/{id}")
    public String detailAdmin(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "admin/products/detail";
    }


    //---------------Create-----------------------


    @GetMapping("/admin/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/products/create";
    }

    @PostMapping("/admin/create")
    public String doCreate(
            Model model,
            @Valid @ModelAttribute("product") Product productForm,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "admin/products/create";
        }
        productRepository.save(productForm);
        return "redirect:/admin";
    }

    @PostMapping("/products/buy/{id}")
    public String buy(
            Model model,
            @PathVariable Integer id,
            @ModelAttribute Order order,
            Authentication authentication
    ) {
        Integer buyerId =
                ((DatabaseUserDetails) authentication.getPrincipal()).getId();
        User buyer = userService.findById(buyerId);
        Product product = productService.getById(id);

        order.setProduct(productService.getById(id));
        order.setDate(LocalDate.now());
        order.setId(null);
        order.setBuyer(buyer);
        orderRepository.save(order);

        buyer.getOrders().add(order);
        userRepository.save(buyer);

        product.getOrders().add(order);
        productRepository.save(product);

        model.addAttribute("order", order);
        model.addAttribute("product", product);

        return "/products/purchased";
    }


    @PostMapping("/products/{id}/review/create")
    public String createReview(
            @PathVariable Integer id,
            @ModelAttribute Review review,
            Authentication authentication
    ) {
        Integer buyerId =
                ((DatabaseUserDetails) authentication.getPrincipal()).getId();
        User reviewer = userService.findById(buyerId);
        Product product = productService.getById(id);

        review.setId(null);
        review.setProduct(productService.getById(id));
        review.setReviewer(reviewer);
        reviewRepository.save(review);

        reviewer.getReviews().add(review);
        userRepository.save(reviewer);

        product.getReviews().add(review);
        productRepository.save(product);

        return "redirect:/products/" + id;
    }


    @GetMapping("/admin/products/restock/{id}")
    public String restock(
            Model model, @PathVariable Integer id
    ) {
        Assortment assortment = new Assortment();
        assortment.setProduct(productService.getById(id));
        model.addAttribute("assortment", assortment);
        return "/admin/products/restock";
    }

    @PostMapping("/admin/products/restock/{id}")
    public String restock(
            @ModelAttribute Assortment assortment,
            @PathVariable Integer id,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "/admin/products/restock";
        }

        assortment.setDate(LocalDate.now());
        assortment.setId(null);
        assortmentRepository.save(assortment);

        Product product = productService.getById(id);
        product.getAssortments().add(assortment);


        productRepository.save(product);


        return "redirect:/admin";
    }


    //---------Edit------------------

    @GetMapping("/admin/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getById(id));
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/products/edit";
    }

    @PostMapping("admin/edit/{id}")
    public String doEdit(
            Model model,
            @PathVariable Integer id,
            @Valid @ModelAttribute("product") Product productForm,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "admin/products/edit";
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
