package com.api.restaurantbooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.api.restaurantbooking.entity.Booking;
import com.api.restaurantbooking.entity.Restaurant;
import com.api.restaurantbooking.entity.RestaurantTable;
import com.api.restaurantbooking.service.BookingService;
import com.api.restaurantbooking.service.RestaurantService;
import com.api.restaurantbooking.entity.RestaurantReview;
import com.api.restaurantbooking.service.RestaurantReviewService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class BookingController {

    private final BookingService bookingService;

    private final RestaurantService restaurantService;
    
    private final RestaurantReviewService restaurantReviewService;


    // ==========================================
    // CONSTRUCTOR
    // ==========================================

    public BookingController(
            BookingService bookingService,
            RestaurantService restaurantService,
            RestaurantReviewService restaurantReviewService) {

        this.bookingService = bookingService;
        this.restaurantService = restaurantService;
        this.restaurantReviewService = restaurantReviewService;
    }
    // ==========================================
    // SHOW CUSTOMER DETAILS FORM
    // ==========================================

    @GetMapping("/restaurant/{id}/book")
    public String showBookingForm(
            @PathVariable Long id,
            Model model) {

        Restaurant restaurant =
                restaurantService.getRestaurantById(id);

        model.addAttribute(
                "restaurant",
                restaurant
        );

        model.addAttribute(
                "booking",
                new Booking()
        );

        return "booking-form";
    }


    // ==========================================
    // CHECK TABLE AVAILABILITY
    // ==========================================

    @PostMapping("/restaurant/{id}/check-availability")
    public String checkAvailability(
            @PathVariable Long id,
            @Valid Booking booking,
            BindingResult result,
            Model model) {
    	
    	if (result.hasErrors()) {

    	    Restaurant restaurant =
    	            restaurantService.getRestaurantById(id);

    	    model.addAttribute("restaurant", restaurant);
    	    model.addAttribute("booking", booking);

    	    return "booking-form";
    	}


        // Make sure this is a NEW booking
        booking.setId(null);


        // Connect booking to restaurant
        booking.setRestaurantId(id);

        booking.setBookingEndTime(
                booking.getBookingTime().plusHours(1)
        );

        // ==========================================
        // CHECK WHETHER TABLE SIZE EXISTS
        // ==========================================

        boolean tableSizeExists =
                bookingService.hasTableOfSize(
                        id,
                        booking.getGuests()
                );


        // Get restaurant
        Restaurant restaurant =
                restaurantService.getRestaurantById(id);


        model.addAttribute(
                "restaurant",
                restaurant
        );

        model.addAttribute(
                "booking",
                booking
        );


        // ==========================================
        // TABLE SIZE DOES NOT EXIST
        // ==========================================

        if (!tableSizeExists) {

            model.addAttribute(
                    "available",
                    false
            );

            model.addAttribute(
                    "message",
                    "Sorry, this restaurant does not have a "
                    + booking.getGuests()
                    + "-person table."
            );

            return "availability-result";
        }


        // ==========================================
        // FIND AVAILABLE TABLE
        // ==========================================

        RestaurantTable availableTable =
                bookingService.findAvailableTable(
                        id,
                        booking.getBookingDate(),
                        booking.getBookingTime(),
                        booking.getGuests()
                );


        // ==========================================
        // ALL TABLES ARE BOOKED
        // ==========================================

        if (availableTable == null) {

            model.addAttribute(
                    "available",
                    false
            );

            model.addAttribute(
                    "message",
                    "Sorry, all "
                    + booking.getGuests()
                    + "-person tables are already booked "
                    + "for this date and time. "
                    + "Please wait or choose another time."
            );

            return "availability-result";
        }


        // ==========================================
        // TABLE IS AVAILABLE
        // ==========================================

        booking.setTableId(
                availableTable.getId()
        );


        model.addAttribute(
                "available",
                true
        );

        model.addAttribute(
                "availableTable",
                availableTable
        );

        model.addAttribute(
                "message",
                "Great! A "
                + booking.getGuests()
                + "-person table is available."
        );


        return "availability-result";
    }


    // ==========================================
    // CONFIRM BOOKING
    // ==========================================

    @PostMapping("/restaurant/{id}/confirm-booking")
    public String confirmBooking(
            @PathVariable Long id,
            Booking booking,
            Model model) {


        // Make sure this is a NEW booking
        booking.setId(null);


        // Connect booking to restaurant
        booking.setRestaurantId(id);
        
        booking.setBookingEndTime(
                booking.getBookingTime().plusHours(1)
        );


        // ==========================================
        // CHECK TABLE ONE FINAL TIME
        // ==========================================

        RestaurantTable availableTable =
                bookingService.findAvailableTable(
                        id,
                        booking.getBookingDate(),
                        booking.getBookingTime(),
                        booking.getGuests()
                );


        // Get restaurant information
        Restaurant restaurant =
                restaurantService.getRestaurantById(id);


        // ==========================================
        // TABLE IS NO LONGER AVAILABLE
        // ==========================================

        if (availableTable == null) {

            model.addAttribute(
                    "restaurant",
                    restaurant
            );

            model.addAttribute(
                    "booking",
                    booking
            );

            model.addAttribute(
                    "available",
                    false
            );

            model.addAttribute(
                    "message",
                    "Sorry, the table was just booked by another customer. "
                    + "Please choose another time."
            );

            return "availability-result";
        }


        // ==========================================
        // ASSIGN AVAILABLE TABLE
        // ==========================================

        booking.setTableId(
                availableTable.getId()
        );


        // ==========================================
        // SAVE BOOKING
        // ==========================================

        Booking savedBooking =
                bookingService.saveBooking(booking);


        // ==========================================
        // SEND DATA TO SUCCESS PAGE
        // ==========================================

        model.addAttribute(
                "restaurant",
                restaurant
        );

        model.addAttribute(
                "booking",
                savedBooking
        );

        model.addAttribute(
                "availableTable",
                availableTable
        );


        return "booking-success";
        
        
    }
    
 // ==========================================
 // SUBMIT RESTAURANT REVIEW
 // ==========================================

 @PostMapping("/restaurant/{id}/review")
 public String submitReview(
         @PathVariable Long id,
         String customerName,
         int rating,
         String comment) {

     RestaurantReview review =
             new RestaurantReview();

     review.setRestaurantId(id);
     review.setCustomerName(customerName);
     review.setRating(rating);
     review.setComment(comment);

     restaurantReviewService.saveReview(review);

     return "redirect:/restaurant/" + id;
 }
 
 			//==========================================
 			//CANCEL BOOKING
 			//==========================================

 			@PostMapping("/admin/bookings/{id}/cancel")
 				public String cancelBooking(@PathVariable Long id) {

 				bookingService.cancelBooking(id);

 				return "redirect:/admin/bookings";
 			}
 			
 			
 			
}