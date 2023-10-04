package org.exercise.java.JAITA91SHOPMUSEO.controller;


import org.exercise.java.JAITA91SHOPMUSEO.model.Prodotto;
import org.exercise.java.JAITA91SHOPMUSEO.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProdottoController {

    @Autowired
    private ProdottoRepository prodottoRepository;


    @GetMapping
    public String index(Model model) {
        List<Prodotto> prodottoList = prodottoRepository.findAll();
        model.addAttribute("prodotti", prodottoList);
        return "prodotti/index";
    }


}
