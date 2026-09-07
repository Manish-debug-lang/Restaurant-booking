package com.api.restaurantbooking.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.restaurantbooking.entity.MenuItem;
import com.api.restaurantbooking.entity.Restaurant;
import com.api.restaurantbooking.entity.RestaurantTable;
import com.api.restaurantbooking.service.MenuItemService;
import com.api.restaurantbooking.service.RestaurantService;
import com.api.restaurantbooking.service.RestaurantTableService;
import com.api.restaurantbooking.entity.RestaurantPhoto;
import com.api.restaurantbooking.service.RestaurantPhotoService;
import com.api.restaurantbooking.entity.RestaurantReview;
import com.api.restaurantbooking.service.RestaurantReviewService;

@Controller
public class RestaurantController {

    private final RestaurantService restaurantService;

    private final RestaurantTableService restaurantTableService;

    private final MenuItemService menuItemService;
    
    private final RestaurantPhotoService restaurantPhotoService;
    
    private final RestaurantReviewService restaurantReviewService;


    public RestaurantController(
            RestaurantService restaurantService,
            RestaurantTableService restaurantTableService,
            MenuItemService menuItemService,
            RestaurantPhotoService restaurantPhotoService,
            RestaurantReviewService restaurantReviewService) {

        this.restaurantService = restaurantService;
        this.restaurantTableService = restaurantTableService;
        this.menuItemService = menuItemService;
        this.restaurantPhotoService = restaurantPhotoService;
        this.restaurantReviewService = restaurantReviewService;
    }

    // ==========================================
    // HOME PAGE
    // ==========================================

    @GetMapping("/")
    public String home(Model model) {

        List<Restaurant> restaurants =
                restaurantService.getAllRestaurants();

        model.addAttribute(
                "restaurants",
                restaurants
        );

        return "index";
    }


    // ==========================================
    // RESTAURANT DETAILS
    // ==========================================

    @GetMapping("/restaurant/{id}")
    public String restaurantDetails(
            @PathVariable Long id,
            Model model) {

        // Get restaurant
        Restaurant restaurant =
                restaurantService.getRestaurantById(id);


        // Get restaurant tables
        List<RestaurantTable> tables =
                restaurantTableService
                        .getTablesByRestaurantId(id);


        // Get restaurant menu
        List<MenuItem> menuItems =
                menuItemService
                        .getMenuByRestaurantId(id);
        
        List<RestaurantPhoto> photos =
                restaurantPhotoService
                        .getPhotosByRestaurantId(id);
        
        List<RestaurantReview> reviews =
                restaurantReviewService
                        .getReviewsByRestaurantId(id);
        
        model.addAttribute(
                "reviews",
                reviews
        );
        
        model.addAttribute(
                "photos",
                photos
        );


        model.addAttribute(
                "restaurant",
                restaurant
        );

        model.addAttribute(
                "tables",
                tables
        );

        model.addAttribute(
                "menuItems",
                menuItems
        );


        return "restaurant-details";
    }


    // ==========================================
    // API - ALL RESTAURANTS
    // ==========================================

    @GetMapping("/api/restaurants")
    @ResponseBody
    public List<Restaurant> getAllRestaurants() {

        return restaurantService.getAllRestaurants();
    }

}