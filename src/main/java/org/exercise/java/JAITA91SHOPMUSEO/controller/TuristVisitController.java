package org.exercise.java.JAITA91SHOPMUSEO.controller;

import jakarta.validation.Valid;
import org.exercise.java.JAITA91SHOPMUSEO.model.TuristVisit;
import org.exercise.java.JAITA91SHOPMUSEO.repository.TuristVisitRepository;
import org.exercise.java.JAITA91SHOPMUSEO.service.TuristVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/visita-guidata")
public class TuristVisitController {

    private final TuristVisitService turistVisitService;
    @Autowired
    private TuristVisitRepository turistVisitRepository;

    public TuristVisitController(TuristVisitService turistVisitService) {
        this.turistVisitService = turistVisitService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("turistvisit", turistVisitRepository.findAll());
        return "visit/index";
    }

    @GetMapping("admin/list")
    public String indexAdmin(Model model) {
        model.addAttribute("turistVisit", turistVisitRepository.findAll());
        return "admin/visit/index";
    }


    @GetMapping("/edit/{id}")
    public String editVisit(Model model, @PathVariable Integer id) {
        model.addAttribute("turistvisit", turistVisitService.getById(id));
        return "admin/visit/edit";
    }

    @PostMapping("/admin/edit/{id}")
    public String doEdit(Model model, @PathVariable Integer id, @Valid @ModelAttribute("turistVisit")
    TuristVisit turistVisitForm) {
        turistVisitRepository.save(turistVisitForm);
        return "redirect:admin/list";
    }

    @GetMapping("/detail/{id}")
    public String detailVisit(Model model, @PathVariable Integer id) {
        model.addAttribute("turistvisit", turistVisitService.getById(id));
        return "visit/detail";
    }

    @GetMapping("/admin/detail/{id}")
    public String adminDeatilVisit(Model model, @PathVariable Integer id) {
        model.addAttribute("turistvisit", turistVisitService.getById(id));
        return "admin/visit/detail";
    }


    @GetMapping("/create")
    public String createVisit(Model model) {
        model.addAttribute("turistvist", new TuristVisit());
        return "admin/visit/create";
    }

    @PostMapping("/admin/create")
    public String doCreate(
            Model model,
            @Valid @ModelAttribute("turistVisit") TuristVisit turistVisitForm,
            BindingResult bindingResult
    ) {
        turistVisitRepository.save(turistVisitForm);
        return "redirect:/visita-guidata/admin/list";
    }

    @PostMapping("/admin/delete/{id}")
    public String delete(@PathVariable Integer id) {
        turistVisitRepository.deleteById(id);

        return "redirect:/visita-guidata/admin/list";
    }


}
