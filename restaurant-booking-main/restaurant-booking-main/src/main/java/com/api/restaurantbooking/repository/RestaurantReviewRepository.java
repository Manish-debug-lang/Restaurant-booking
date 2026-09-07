package com.api.restaurantbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.restaurantbooking.entity.RestaurantReview;

public interface RestaurantReviewRepository
        extends JpaRepository<RestaurantReview, Long> {

    List<RestaurantReview> findByRestaurantId(Long restaurantId);

    void deleteByRestaurantId(Long restaurantId);
}