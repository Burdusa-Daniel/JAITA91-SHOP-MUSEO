package org.exercise.java.JAITA91SHOPMUSEO.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @ManyToOne
    private MacroCategory macroCategory;

    @ManyToMany(mappedBy = "categories")
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MacroCategory getMacroCategory() {
        return macroCategory;
    }

    public void setMacroCategory(MacroCategory macroCategory) {
        this.macroCategory = macroCategory;
    }

}
