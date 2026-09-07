package com.api.restaurantbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.restaurantbooking.entity.RestaurantPhoto;

import org.springframework.transaction.annotation.Transactional;

public interface RestaurantPhotoRepository
        extends JpaRepository<RestaurantPhoto, Long> {

    List<RestaurantPhoto> findByRestaurantId(Long restaurantId);

    @Transactional
    void deleteByRestaurantId(Long restaurantId);
}