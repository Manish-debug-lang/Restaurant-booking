package com.api.restaurantbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.restaurantbooking.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}