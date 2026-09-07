package com.api.restaurantbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.restaurantbooking.entity.MenuItem;

import org.springframework.transaction.annotation.Transactional;

public interface MenuItemRepository
        extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findByRestaurantId(Long restaurantId);

    @Transactional
    void deleteByRestaurantId(Long restaurantId);
}