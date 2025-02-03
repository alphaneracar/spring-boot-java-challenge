package com.springbootchallenge.service;

import com.springbootchallenge.collection.Booking;
import com.springbootchallenge.dtos.CreateOrEditBookingDto;
import com.springbootchallenge.exception.BookingNotFoundException;

import java.math.BigInteger;

public interface BookingService {
    Long createOrUpdate(CreateOrEditBookingDto createOrEditBookingDto) throws BookingNotFoundException;

    CreateOrEditBookingDto getBooking(Long id) throws BookingNotFoundException;

    void deleteBooking(Long id) throws BookingNotFoundException;
}
