package com.springbootchallenge.mapper;

import com.springbootchallenge.collection.Booking;
import com.springbootchallenge.dtos.CreateOrEditBookingDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(target = "id", ignore = true)
    CreateOrEditBookingDto bookingToCreateOrEdit(Booking booking);

    @Mapping(target = "id", ignore = true)
    Booking createOrEditToBooking(CreateOrEditBookingDto createOrEditBookingDto);

}
