package org.exercise.java.JAITA91SHOPMUSEO.service;

import org.exercise.java.JAITA91SHOPMUSEO.model.Category;
import org.exercise.java.JAITA91SHOPMUSEO.model.MacroCategory;
import org.exercise.java.JAITA91SHOPMUSEO.repository.CategoryRepository;
import org.exercise.java.JAITA91SHOPMUSEO.repository.MacroCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class MacroCategoryService {

    private final MacroCategoryRepository macroCategoryRepository;

    @Autowired
    public MacroCategoryService(MacroCategoryRepository macroCategoryRepository) {
        this.macroCategoryRepository = macroCategoryRepository;
    }

    public MacroCategory getById(Integer id) {
        Optional<MacroCategory> macroCategoryOptional = macroCategoryRepository.findById(id);
        if (macroCategoryOptional.isPresent()) {
            return macroCategoryOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
