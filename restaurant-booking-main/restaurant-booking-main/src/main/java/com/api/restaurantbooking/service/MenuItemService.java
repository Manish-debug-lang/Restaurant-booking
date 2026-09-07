package com.api.restaurantbooking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.restaurantbooking.entity.MenuItem;
import com.api.restaurantbooking.repository.MenuItemRepository;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    // Get all menu items for a restaurant
    public List<MenuItem> getMenuByRestaurantId(Long restaurantId) {

        return menuItemRepository.findByRestaurantId(restaurantId);
    }

    // Save menu item
    public MenuItem saveMenuItem(MenuItem menuItem) {

        return menuItemRepository.save(menuItem);
    }

    // Get all menu items
    public List<MenuItem> getAllMenuItems() {

        return menuItemRepository.findAll();
    }

    // Get menu item by ID
    public MenuItem getMenuItemById(Long id) {

        return menuItemRepository
                .findById(id)
                .orElse(null);
    }

    // Delete menu item
    public void deleteMenuItem(Long id) {

        menuItemRepository.deleteById(id);
    }
}
