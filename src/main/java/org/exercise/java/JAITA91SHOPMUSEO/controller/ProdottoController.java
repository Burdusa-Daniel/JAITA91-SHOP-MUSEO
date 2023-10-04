package org.exercise.java.JAITA91SHOPMUSEO.controller;


import org.exercise.java.JAITA91SHOPMUSEO.model.Prodotto;
import org.exercise.java.JAITA91SHOPMUSEO.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProdottoController {

    @Autowired
    private ProdottoRepository prodottoRepository;


    @GetMapping("/")
    public String index(Model model) {
        List<Prodotto> prodottoList = prodottoRepository.findAll();
        model.addAttribute("prodotti", prodottoList);
        return "prodotti/index";
    }

    @GetMapping("/admin")
    public String homepage(Model model) {
        List<Prodotto> prodottoList = prodottoRepository.findAll();
        model.addAttribute("prodotti", prodottoList);
        return "admin/homepage";
    }

    @GetMapping("/admin/create")
    public String create(Model model){
        model.addAttribute("prodotti", new Prodotto()){
            return "admin/create";
        }
    }

    @PostMapping("/admin/create")
    public String doCreate(@ModelAttribute("prodotti") Prodotto prodottoForm,
    BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "admin/create";
        }
        prodottoRepository.save(prodottoForm);
        return "redirect:/admin/";
    }



}
