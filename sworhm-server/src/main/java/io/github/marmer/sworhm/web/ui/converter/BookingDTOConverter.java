package io.github.marmer.sworhm.web.ui.converter;

import io.github.marmer.sworhm.core.Converter;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.web.ui.BookingController;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingDTOConverter extends Converter<Booking, BookingController.BookingDTO> {
    @Override
    // TODO: marmer 06.01.2019 test me
    public BookingController.BookingDTO convert(Booking source);
}
