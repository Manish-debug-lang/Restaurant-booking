package com.api.restaurantbooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RestaurantPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long restaurantId;

    private String imageUrl;

    private String caption;


    // Default constructor
    public RestaurantPhoto() {
    }


    // Constructor
    public RestaurantPhoto(
            Long restaurantId,
            String imageUrl,
            String caption) {

        this.restaurantId = restaurantId;
        this.imageUrl = imageUrl;
        this.caption = caption;
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


    // Image URL

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    // Caption

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}