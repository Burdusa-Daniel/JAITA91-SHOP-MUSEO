package org.exercise.java.JAITA91SHOPMUSEO.service;

import org.exercise.java.JAITA91SHOPMUSEO.model.TuristVisit;
import org.exercise.java.JAITA91SHOPMUSEO.repository.TuristVisitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class TuristVisitService {

    private final TuristVisitRepository turistVisitRepository;

    public TuristVisitService(TuristVisitRepository turistVisitRepository) {
        this.turistVisitRepository = turistVisitRepository;
    }


    public TuristVisit getById(Integer id) {
        Optional<TuristVisit> turistVisitOptional = turistVisitRepository.findById(id);
        if (turistVisitOptional.isPresent()) return turistVisitOptional.get();
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
