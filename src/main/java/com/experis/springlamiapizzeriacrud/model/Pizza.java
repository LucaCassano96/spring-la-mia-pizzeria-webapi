package com.experis.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @OneToMany(mappedBy = "pizza", cascade = CascadeType.REMOVE)
    private List<SpecialOffer> specialOffer;

    @ManyToMany
    private List<Ingredients> ingridients;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "name must not be blank")
    @Size(max = 255, message = "Length must be less than 255")
    private String name;
    @NotBlank(message = "description must not be blank")
    @Lob
    private String description;
    @NotBlank(message = "photo_url must not be blank")
    private String photo_url;
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @DecimalMax(value = "999999.99", message = "Price must not exceed 999999.99")
    private double price;
    private LocalDateTime created_at;

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

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public List<SpecialOffer> getSpecialOffer() {
        return specialOffer;
    }

    public void setSpecialOffer(List<SpecialOffer> specialOffer) {
        this.specialOffer = specialOffer;
    }

    public List<Ingredients> getIngridients() {
        return ingridients;
    }

    public void setIngridients(List<Ingredients> ingridients) {
        this.ingridients = ingridients;
    }
}
