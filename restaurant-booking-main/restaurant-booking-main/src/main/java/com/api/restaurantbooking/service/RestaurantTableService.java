package com.api.restaurantbooking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.restaurantbooking.entity.RestaurantTable;
import com.api.restaurantbooking.repository.RestaurantTableRepository;

@Service
public class RestaurantTableService {

    private final RestaurantTableRepository restaurantTableRepository;

    public RestaurantTableService(
            RestaurantTableRepository restaurantTableRepository) {

        this.restaurantTableRepository = restaurantTableRepository;
    }

    public List<RestaurantTable> getTablesByRestaurantId(
            Long restaurantId) {

        return restaurantTableRepository
                .findByRestaurantId(restaurantId);
    }
}