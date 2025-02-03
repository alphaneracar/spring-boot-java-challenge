package com.springbootchallenge.dtos;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Builder;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateOrEditBookingDto {

    private Long id;
    private String name;
    private String email;
    private String origin;
    private String destination;
    private LocalDateTime departureDateAndTime;
    private Duration duration;

    @JsonGetter("duration")
    public long getDurationInMinutes() {
        return duration.toMinutes();
    }

    @JsonSetter("duration")
    public void setDurationInMinutes(long durationInMinutes) {
        this.duration = Duration.ofMinutes(durationInMinutes);
    }

}
