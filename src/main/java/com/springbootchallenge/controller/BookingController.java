package com.springbootchallenge.controller;

import com.springbootchallenge.collection.Booking;
import com.springbootchallenge.dtos.CreateOrEditBookingDto;
import com.springbootchallenge.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    private Long createOrUpdate(@RequestBody CreateOrEditBookingDto createOrEditBookingDto) {
        return bookingService.createOrUpdate(createOrEditBookingDto);
    }

    @GetMapping("/{id}")
    private CreateOrEditBookingDto getBooking(@PathVariable Long id) {
        return bookingService.getBooking(id);
    }

    @DeleteMapping("/{id}")
    private void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }

}
