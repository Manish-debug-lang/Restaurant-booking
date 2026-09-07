package com.api.restaurantbooking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.restaurantbooking.entity.Restaurant;
import com.api.restaurantbooking.repository.RestaurantRepository;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAllRestaurants() {

        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long id) {

        return restaurantRepository.findById(id).orElse(null);
    }

    public Restaurant saveRestaurant(Restaurant restaurant) {

        return restaurantRepository.save(restaurant);
    }
}