package com.api.restaurantbooking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.restaurantbooking.entity.RestaurantPhoto;
import com.api.restaurantbooking.repository.RestaurantPhotoRepository;

@Service
public class RestaurantPhotoService {

    private final RestaurantPhotoRepository restaurantPhotoRepository;

    public RestaurantPhotoService(
            RestaurantPhotoRepository restaurantPhotoRepository) {

        this.restaurantPhotoRepository = restaurantPhotoRepository;
    }


    // Get photos for a restaurant
    public List<RestaurantPhoto> getPhotosByRestaurantId(
            Long restaurantId) {

        return restaurantPhotoRepository
                .findByRestaurantId(restaurantId);
    }


    // Save photo
    public RestaurantPhoto savePhoto(
            RestaurantPhoto restaurantPhoto) {

        return restaurantPhotoRepository
                .save(restaurantPhoto);
    }


    // Get all photos
    public List<RestaurantPhoto> getAllPhotos() {

        return restaurantPhotoRepository.findAll();
    }


    // Get photo by ID
    public RestaurantPhoto getPhotoById(Long id) {

        return restaurantPhotoRepository
                .findById(id)
                .orElse(null);
    }


    // Delete photo
    public void deletePhoto(Long id) {

        restaurantPhotoRepository.deleteById(id);
    }
}