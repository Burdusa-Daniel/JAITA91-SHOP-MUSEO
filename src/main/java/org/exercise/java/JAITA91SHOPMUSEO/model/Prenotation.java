package org.exercise.java.JAITA91SHOPMUSEO.model;

import jakarta.persistence.*;

@Entity
@Table(name = "prenotations")
public class Prenotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private TuristVisit visit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TuristVisit getVisit() {
        return visit;
    }

    public void setVisit(TuristVisit visit) {
        this.visit = visit;
    }

}
