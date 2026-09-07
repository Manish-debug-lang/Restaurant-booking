package com.api.restaurantbooking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.restaurantbooking.entity.RestaurantReview;
import com.api.restaurantbooking.repository.RestaurantReviewRepository;

@Service
public class RestaurantReviewService {

    private final RestaurantReviewRepository restaurantReviewRepository;

    public RestaurantReviewService(
            RestaurantReviewRepository restaurantReviewRepository) {

        this.restaurantReviewRepository =
                restaurantReviewRepository;
    }


    // Get reviews for a restaurant
    public List<RestaurantReview> getReviewsByRestaurantId(
            Long restaurantId) {

        return restaurantReviewRepository
                .findByRestaurantId(restaurantId);
    }


    // Save review
    public RestaurantReview saveReview(
            RestaurantReview restaurantReview) {

        return restaurantReviewRepository
                .save(restaurantReview);
    }


    // Get all reviews
    public List<RestaurantReview> getAllReviews() {

        return restaurantReviewRepository.findAll();
    }


    // Get review by ID
    public RestaurantReview getReviewById(Long id) {

        return restaurantReviewRepository
                .findById(id)
                .orElse(null);
    }


    // Delete review
    public void deleteReview(Long id) {

        restaurantReviewRepository.deleteById(id);
    }
}