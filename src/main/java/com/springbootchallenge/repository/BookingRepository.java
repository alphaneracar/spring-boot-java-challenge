package com.springbootchallenge.repository;

import com.springbootchallenge.collection.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository("bookingAutoGeneratedFieldRepository")
public interface BookingRepository extends MongoRepository<Booking, BigInteger> {
}
