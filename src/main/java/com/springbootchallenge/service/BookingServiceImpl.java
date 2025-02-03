package com.springbootchallenge.service;

import com.springbootchallenge.collection.Booking;
import com.springbootchallenge.dtos.CreateOrEditBookingDto;
import com.springbootchallenge.exception.BookingNotFoundException;
import com.springbootchallenge.mapper.BookingMapper;
import com.springbootchallenge.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public CreateOrEditBookingDto getBooking(Long id) throws BookingNotFoundException {

        Optional<Booking> requestedBooking = bookingRepository.findById(BigInteger.valueOf(id));

        if (requestedBooking.isEmpty()) {
            throw new BookingNotFoundException("Booking not found");
        }
        CreateOrEditBookingDto requestedBookingDto
                = bookingMapper.bookingToCreateOrEdit(requestedBooking.get());
        requestedBookingDto.setId(requestedBooking.get().getId().longValue());

        return requestedBookingDto;
    }

    @Override
    public void deleteBooking(Long id) throws BookingNotFoundException {
        BigInteger bigIntId = BigInteger.valueOf(id);
        if (!bookingRepository.existsById(bigIntId)) {
            throw new BookingNotFoundException("Booking not found");
        }
        bookingRepository.deleteById(bigIntId);
    }

    @Override
    public Long createOrUpdate(CreateOrEditBookingDto createOrEditBookingDto) throws BookingNotFoundException{
        if (createOrEditBookingDto.getId() == null) {
            return create(createOrEditBookingDto);
        } else {
            return update(createOrEditBookingDto);
        }
    }

    private Long create(CreateOrEditBookingDto createOrEditBookingDto) {
        Booking booking = bookingMapper.createOrEditToBooking(createOrEditBookingDto);
        booking.setId(BigInteger.valueOf(sequenceGeneratorService.generateSequence(Booking.SEQUENCE_NAME)));
        booking = bookingRepository.save(booking);
        return booking.getId().longValue();
    }

    private Long update(CreateOrEditBookingDto createOrEditBookingDto) throws BookingNotFoundException{
        BigInteger bigIntId = BigInteger.valueOf(createOrEditBookingDto.getId());
        if (!bookingRepository.existsById(bigIntId)) {
            throw new BookingNotFoundException("Booking not found");
        }

        Booking booking = bookingMapper.createOrEditToBooking(createOrEditBookingDto);
        booking.setId(bigIntId);
        booking = bookingRepository.save(booking);
        return booking.getId().longValue();

    }
}
