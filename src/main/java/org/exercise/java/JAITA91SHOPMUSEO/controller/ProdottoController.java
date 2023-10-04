package org.exercise.java.JAITA91SHOPMUSEO.controller;


import org.exercise.java.JAITA91SHOPMUSEO.model.Prodotto;
import org.exercise.java.JAITA91SHOPMUSEO.repository.ProdottoRepository;
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

    //-----------Detail----------------------------

    @GetMapping("/prodotto/{id}")
    public String detail(@PathVariable Integer id, Model model){
        Optional<Prodotto> prodottoOptional = prodottoRepository.findById(id);
        if(prodottoOptional.isPresent()) {
            model.addAttribute("prodotti", prodottoOptional.get());
            return "prodotti/detail";
        }else {
            // se l'opional è vuoto sollevo un'eccezione
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("admin/prodotto/{id}")
    public String detailAdmin(@PathVariable Integer id, Model model){
        Optional<Prodotto> prodottoOptional = prodottoRepository.findById(id);
        if(prodottoOptional.isPresent()) {
            Prodotto prodottoDB = prodottoOptional.get();
            model.addAttribute("prodotti", prodottoDB);
            return "admin/detail";
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    //---------------Create-----------------------


    @GetMapping("/admin/create")
    public String create(Model model){
        model.addAttribute("prodotti", new Prodotto());
        return "admin/create";
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

    //---------Edit------------------

    @GetMapping("/admin/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Optional<Prodotto> prodottoOptional = prodottoRepository.findById(id);
        model.addAttribute("prodotti", prodottoOptional.get());
        return "admin/edit";
    }

    @PostMapping("admin/edit/{id}")
    public String doEdit(@PathVariable Integer id,
         @ModelAttribute("prodotti") Prodotto prodottoForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "admin/edit";
        }
        prodottoRepository.save(prodottoForm);
        return "redirect:/admin";
    }

    @PostMapping("/admin/prodotto/delete/{id}")
    public String delete(@PathVariable Integer id){
        prodottoRepository.deleteById(id);
        return "redirect:/admin";
    }

}
