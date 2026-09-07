package com.api.restaurantbooking.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.api.restaurantbooking.entity.Booking;
import com.api.restaurantbooking.entity.RestaurantTable;
import com.api.restaurantbooking.repository.BookingRepository;
import com.api.restaurantbooking.repository.RestaurantTableRepository;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    private final RestaurantTableRepository restaurantTableRepository;


    // ==========================================
    // CONSTRUCTOR
    // ==========================================

    public BookingService(
            BookingRepository bookingRepository,
            RestaurantTableRepository restaurantTableRepository) {

        this.bookingRepository = bookingRepository;
        this.restaurantTableRepository = restaurantTableRepository;
    }


    // ==========================================
    // SAVE BOOKING
    // ==========================================

    public Booking saveBooking(Booking booking) {

        return bookingRepository.save(booking);
    }


    // ==========================================
    // GET ALL BOOKINGS
    // ==========================================

    public List<Booking> getAllBookings() {

        return bookingRepository.findAll();
    }


    // ==========================================
    // GET BOOKING BY ID
    // ==========================================

    public Booking getBookingById(Long id) {

        return bookingRepository
                .findById(id)
                .orElse(null);
    }


    // ==========================================
    // DELETE BOOKING
    // ==========================================

 // ==========================================
 // CANCEL BOOKING
 // ==========================================

 public void cancelBooking(Long id) {

     Booking booking = bookingRepository
             .findById(id)
             .orElse(null);

     if (booking != null) {

         booking.setStatus("CANCELLED");

         bookingRepository.save(booking);
     }
 }


    // ==========================================
    // CHECK WHETHER TABLE SIZE EXISTS
    // ==========================================

    public boolean hasTableOfSize(
            Long restaurantId,
            int guests) {

        List<RestaurantTable> tables =
                restaurantTableRepository
                        .findByRestaurantId(restaurantId);

        for (RestaurantTable table : tables) {

            if (table.getSeats() == guests) {

                return true;
            }
        }

        return false;
    }


    // ==========================================
    // FIND AVAILABLE TABLE
    // ==========================================

    public RestaurantTable findAvailableTable(
            Long restaurantId,
            LocalDate bookingDate,
            LocalTime bookingTime,
            int guests) {


        // ==========================================
        // NEW BOOKING END TIME
        // ==========================================

        // Every booking occupies the table for 1 hour

        LocalTime bookingEndTime =
                bookingTime.plusHours(1);


        // ==========================================
        // GET RESTAURANT TABLES
        // ==========================================

        List<RestaurantTable> tables =
                restaurantTableRepository
                        .findByRestaurantId(restaurantId);


        // ==========================================
        // CHECK EACH TABLE
        // ==========================================

        for (RestaurantTable table : tables) {


            // Table must have EXACT number of seats

            if (table.getSeats() == guests) {


                // ==========================================
                // CHECK OVERLAPPING BOOKING
                // ==========================================

            	boolean alreadyBooked =
            	        bookingRepository
            	                .existsByTableIdAndBookingDateAndBookingTimeLessThanAndBookingEndTimeGreaterThanAndStatusNot(
            	                        table.getId(),
            	                        bookingDate,
            	                        bookingEndTime,
            	                        bookingTime,
            	                        "CANCELLED"
            	                );

            	
                // ==========================================
                // TABLE AVAILABLE
                // ==========================================

                if (!alreadyBooked) {

                    return table;
                }
            }
            
        }

        

        // ==========================================
        // NO TABLE AVAILABLE
        // ==========================================

        return null;
    }
}