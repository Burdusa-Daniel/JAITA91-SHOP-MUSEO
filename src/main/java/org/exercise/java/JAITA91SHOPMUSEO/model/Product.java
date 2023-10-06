package org.exercise.java.JAITA91SHOPMUSEO.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    //ATTRIBUTI

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String url;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private BigDecimal price;

    @ManyToMany
    List<Category> categories;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Order> orders;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Assortment> assortments;

    public Integer getAvailable() {
        int fromOrders = 0;
        for (Order order : orders) {
            fromOrders += order.getQuantity();
        }

        int storageAvailable = 0;
        for (Assortment assortment : assortments) {
            storageAvailable += assortment.getQuantity();
        }

        return storageAvailable - fromOrders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Assortment> getAssortments() {
        return assortments;
    }

    public void setAssortments(List<Assortment> assortments) {
        this.assortments = assortments;
    }
//GETTER E SETTER


    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
