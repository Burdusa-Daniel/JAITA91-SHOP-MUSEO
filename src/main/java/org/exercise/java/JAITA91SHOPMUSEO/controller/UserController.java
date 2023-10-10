package org.exercise.java.JAITA91SHOPMUSEO.controller;

import org.exercise.java.JAITA91SHOPMUSEO.model.User;
import org.exercise.java.JAITA91SHOPMUSEO.repository.RoleRepository;
import org.exercise.java.JAITA91SHOPMUSEO.repository.UserRepository;
import org.exercise.java.JAITA91SHOPMUSEO.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleService roleService;

    public UserController(UserRepository userRepository,
                          RoleRepository roleRepository, RoleService roleService
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());

        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, BindingResult bindingResult) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Un utente con username " + user.getUsername() + " esiste già."
            );
        }

        if (bindingResult.hasErrors()) return "auth/register";

        user.setRegistrationDate(LocalDate.now());
        user.getRoles().add(roleService.getByName("USER"));
        user.setPassword("{noop}" + user.getPassword());
        userRepository.save(user);

        return "redirect:/login";
    }

}
