package io.github.marmer.sworhm.rest.v1.converter.internal;

import io.github.marmer.sworhm.MappingConfiguration;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.rest.v1.BookingController;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MappingConfiguration.class)
public interface BookingFromDtoConverter {
    @Mapping(target = "day", ignore = true)
    Booking convert(final BookingController.BookingDto source);
}
