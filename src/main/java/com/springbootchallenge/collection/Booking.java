package com.springbootchallenge.collection;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
@Document(collection = "booking")
public class Booking {

    @Transient
    public static final String SEQUENCE_NAME = "booking_sequence";

    @Id
    private BigInteger id;
    private String name;
    private String email;
    private String origin;
    private String destination;
    private LocalDateTime departureDateAndTime;
    private Duration duration;

}
