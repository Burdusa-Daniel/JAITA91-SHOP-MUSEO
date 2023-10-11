package org.exercise.java.JAITA91SHOPMUSEO.controller;

import org.exercise.java.JAITA91SHOPMUSEO.repository.TuristVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/visita-guidata")
public class TuristVisitController {

    @Autowired
    private TuristVisitRepository turistVisitRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("turistvisit", turistVisitRepository.findAll());
        return "visit/index";
    }


    /*@GetMapping("/edit/{id}")
       public String editVisit (Model model, @PathVariable Integer id){
        model.addAttribute("")
    }

    */

}
