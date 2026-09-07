package com.api.restaurantbooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long restaurantId;

    private String name;

    private String description;

    private double price;

    private String category;

    private String imageUrl;


    // Default constructor
    public MenuItem() {
    }


    // Constructor
    public MenuItem(
            Long restaurantId,
            String name,
            String description,
            double price,
            String category,
            String imageUrl) {

        this.restaurantId = restaurantId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
    }


    // ID

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    // Restaurant ID

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }


    // Name

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    // Description

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    // Price

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    // Category

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    // Image URL

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
