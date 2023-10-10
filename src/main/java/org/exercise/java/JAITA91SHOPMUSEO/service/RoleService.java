package org.exercise.java.JAITA91SHOPMUSEO.service;

import org.exercise.java.JAITA91SHOPMUSEO.model.Role;
import org.exercise.java.JAITA91SHOPMUSEO.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getByName(String name) {
        Optional<Role> roleOptional = roleRepository.findByName(name);
        if (roleOptional.isPresent()) return roleOptional.get();
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
