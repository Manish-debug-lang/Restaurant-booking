package com.api.restaurantbooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RestaurantReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long restaurantId;

    private String customerName;

    private String comment;

    private int rating;


    // Default constructor
    public RestaurantReview() {
    }


    // Constructor
    public RestaurantReview(
            Long restaurantId,
            String customerName,
            String comment,
            int rating) {

        this.restaurantId = restaurantId;
        this.customerName = customerName;
        this.comment = comment;
        this.rating = rating;
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


    // Customer Name

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    // Comment

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    // Rating

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}