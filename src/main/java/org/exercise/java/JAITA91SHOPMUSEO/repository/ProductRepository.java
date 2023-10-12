package org.exercise.java.JAITA91SHOPMUSEO.repository;

import org.exercise.java.JAITA91SHOPMUSEO.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByNameContaining(String query);
}
