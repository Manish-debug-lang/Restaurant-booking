package com.api.restaurantbooking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.api.restaurantbooking.entity.Restaurant;
import com.api.restaurantbooking.repository.RestaurantRepository;

@SpringBootApplication
public class RestaurantBookingApplication {

    public static void main(String[] args) {

        SpringApplication.run(
                RestaurantBookingApplication.class,
                args
        );
    }

    @Bean
    CommandLineRunner loadData(RestaurantRepository restaurantRepository) {

        return args -> {

            if (restaurantRepository.count() == 0) {

                restaurantRepository.save(
                    new Restaurant(
                        "Spice Garden",
                        "Pune",
                        "Authentic Indian cuisine with a modern atmosphere.",
                        "https://images.unsplash.com/photo-1515003197210-e0cd71810b5f"
                    )
                );

                restaurantRepository.save(
                    new Restaurant(
                        "The Food Studio",
                        "Mumbai",
                        "A beautiful place for family dinners and celebrations.",
                        "https://images.unsplash.com/photo-1517248135467-4c7edcad34c4"
                    )
                );

                restaurantRepository.save(
                    new Restaurant(
                        "Urban Tadka",
                        "Nashik",
                        "Delicious food, comfortable seating and great service.",
                        "https://images.unsplash.com/photo-1552566626-52f8b828add9"
                    )
                );

            }
        };
    }
}