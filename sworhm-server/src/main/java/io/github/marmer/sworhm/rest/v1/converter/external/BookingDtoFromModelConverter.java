package io.github.marmer.sworhm.rest.v1.converter.external;

import io.github.marmer.sworhm.MappingConfiguration;
import io.github.marmer.sworhm.core.Converter;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.rest.v1.BookingController;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfiguration.class)
public interface BookingDtoFromModelConverter extends Converter<Booking, BookingController.BookingDto> {
    @Override
    BookingController.BookingDto convert(Booking source);
}
