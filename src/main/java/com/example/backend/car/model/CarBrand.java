package com.example.backend.car.model;

import jakarta.persistence.*;

@Entity
@Table(name = "car_brand", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class CarBrand {

    @Id
    private int id;

    @Column(length = 64, nullable = false)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
