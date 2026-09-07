package com.api.restaurantbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.restaurantbooking.entity.RestaurantTable;

import org.springframework.transaction.annotation.Transactional;

public interface RestaurantTableRepository
        extends JpaRepository<RestaurantTable, Long> {

    List<RestaurantTable> findByRestaurantId(Long restaurantId);

    @Transactional
    void deleteByRestaurantId(Long restaurantId);
}