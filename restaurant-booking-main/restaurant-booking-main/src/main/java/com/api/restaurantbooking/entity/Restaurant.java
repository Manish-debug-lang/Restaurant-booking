package com.api.restaurantbooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    private String description;

    private String imageUrl;

    private double rating;


    // Default constructor
    public Restaurant() {
    }


    // Existing constructor
    public Restaurant(
            String name,
            String location,
            String description,
            String imageUrl) {

        this.name = name;
        this.location = location;
        this.description = description;
        this.imageUrl = imageUrl;
    }


    // New constructor with rating
    public Restaurant(
            String name,
            String location,
            String description,
            String imageUrl,
            double rating) {

        this.name = name;
        this.location = location;
        this.description = description;
        this.imageUrl = imageUrl;
        this.rating = rating;
    }


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}