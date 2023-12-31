package org.exercise.java.JAITA91SHOPMUSEO.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "turist_visit")
public class TuristVisit {

    //ATTRIBUTI

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    private String name;

    private String description;

    private BigDecimal price;

    private String url;

    @OneToMany(mappedBy = "visit")
    private List<Prenotation> prenotations;

    public List<Prenotation> getPrenotations() {
        return prenotations;
    }

    public void setPrenotations(List<Prenotation> prenotations) {
        this.prenotations = prenotations;
    }

    //GETTER E SETTER

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
