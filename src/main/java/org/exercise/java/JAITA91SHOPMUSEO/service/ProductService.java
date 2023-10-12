package org.exercise.java.JAITA91SHOPMUSEO.service;

import org.exercise.java.JAITA91SHOPMUSEO.model.Product;
import org.exercise.java.JAITA91SHOPMUSEO.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()) {
            return productOptional.get();
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<Product> getTopProducts() {
        return productRepository.findAll().stream().sorted((a, b) -> b.getNumberOfBuys() - a.getNumberOfBuys()).limit(3).toList();
    }

    public List<Product> getMostVoted() {
        return productRepository.findAll().stream().sorted((a, b) -> b.getAverageRating() - a.getAverageRating()).limit(3).toList();

    }

}


