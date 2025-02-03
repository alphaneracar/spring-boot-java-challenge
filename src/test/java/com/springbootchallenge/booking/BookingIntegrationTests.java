package com.springbootchallenge.booking;


import com.springbootchallenge.AdaSpringBootJavaChallengeApplication;
import com.springbootchallenge.collection.Booking;
import com.springbootchallenge.dtos.CreateOrEditBookingDto;
import com.springbootchallenge.exception.BookingNotFoundException;
import com.springbootchallenge.repository.BookingRepository;
import com.springbootchallenge.repository.DatabaseSequenceRepository;
import com.springbootchallenge.service.BookingService;
import com.springbootchallenge.service.SequenceGeneratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AdaSpringBootJavaChallengeApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class BookingIntegrationTests {

    //@Autowired
    //private MockMvc mockMvc;

    @Autowired
    private BookingService bookingService;

    @Autowired
    @Qualifier("bookingAutoGeneratedFieldRepository")
    private BookingRepository bookingRepository;

    @Autowired
    private DatabaseSequenceRepository databaseSequenceRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    private BigInteger bookingId1;

    @BeforeEach
    public void setUp() {
        bookingRepository.deleteAll();
        databaseSequenceRepository.deleteAll();

        dbSetup();
    }

    private void dbSetup() {

        bookingId1 = BigInteger.valueOf(sequenceGeneratorService.generateSequence(Booking.SEQUENCE_NAME));

        Booking booking1 = Booking.builder()
                .id(bookingId1)
                .name("Booking 1")
                .email("email@mail.com")
                .origin("Origin")
                .destination("Destination")
                .departureDateAndTime(LocalDateTime.parse("2021-08-01T10:00:00"))
                .duration(Duration.parse("PT2H"))
                .build();

        bookingRepository.save(booking1);
    }

    //@Autowired MongoTemplate mongoTemplate

    @Test
    void whenCreateBooking_thenBookingIsCreated() {
        CreateOrEditBookingDto bookingDto = CreateOrEditBookingDto.builder()
                .name("Booking 2")
                .email("email@mail.com")
                .origin("Origin")
                .destination("Destination")
                .departureDateAndTime(LocalDateTime.parse("2021-08-01T10:00:00"))
                .duration(Duration.parse("PT2H"))
                .build();

        bookingService.createOrUpdate(bookingDto);

        assert bookingRepository.count() == 2;
        assertEquals("Booking 2", bookingRepository.findById(BigInteger.valueOf(2)).get().getName());
    }

    @Test
    void whenUpdateBooking_thenBookingIsUpdated() {
        CreateOrEditBookingDto bookingDto = CreateOrEditBookingDto.builder()
                .id(bookingId1.longValue())
                .name("Booking 2")
                .email("email@mail.com")
                .origin("Origin")
                .destination("Destination")
                .departureDateAndTime(LocalDateTime.parse("2021-08-01T10:00:00"))
                .duration(Duration.parse("PT2H"))
                .build();

        bookingService.createOrUpdate(bookingDto);
        assertEquals("Booking 2", bookingRepository.findById(bookingId1).get().getName());

    }

    @Test
    void whenGetBooking_thenBookingIsReturned() {
        CreateOrEditBookingDto bookingDto = bookingService.getBooking(bookingId1.longValue());
        assertEquals("Booking 1", bookingDto.getName());
    }

    @Test
    void whenGetNonExistentBooking_thenBookingNotFoundExceptionIsThrown() {
        assertThrows(BookingNotFoundException.class, () -> bookingService.getBooking(2L));
    }

    @Test
    void whenDeleteBooking_thenBookingIsDeleted() {
        bookingService.deleteBooking(bookingId1.longValue());
        assert bookingRepository.count() == 0;
    }

}
