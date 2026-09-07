package com.api.restaurantbooking.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.restaurantbooking.entity.Booking;

import org.springframework.transaction.annotation.Transactional;

public interface BookingRepository
        extends JpaRepository<Booking, Long> {

	boolean existsByTableIdAndBookingDateAndBookingTimeLessThanAndBookingEndTimeGreaterThanAndStatusNot(
	        Long tableId,
	        LocalDate bookingDate,
	        LocalTime newEndTime,
	        LocalTime newStartTime,
	        String status
	);

    @Transactional
    void deleteByRestaurantId(Long restaurantId);
    boolean existsByTableId(Long tableId);
}