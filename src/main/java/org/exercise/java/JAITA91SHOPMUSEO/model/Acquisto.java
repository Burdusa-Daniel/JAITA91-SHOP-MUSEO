package org.exercise.java.JAITA91SHOPMUSEO.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "acquisti")
public class Acquisto{

    //attributi
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate data;
    private int quantita;

    //getter e setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
