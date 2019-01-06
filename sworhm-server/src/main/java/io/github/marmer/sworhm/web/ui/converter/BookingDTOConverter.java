package io.github.marmer.sworhm.web.ui.converter;

import io.github.marmer.sworhm.core.Converter;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.web.ui.dto.BookingDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingDTOConverter extends Converter<Booking, BookingDTO> {
    @Override
    BookingDTO convert(Booking source);
}
