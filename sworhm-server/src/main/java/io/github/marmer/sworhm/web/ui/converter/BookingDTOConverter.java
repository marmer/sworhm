package io.github.marmer.sworhm.web.ui.converter;

import io.github.marmer.sworhm.MappingConfiguration;
import io.github.marmer.sworhm.core.Converter;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.web.ui.dto.BookingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalTime;

@Mapper(config = MappingConfiguration.class)
public interface BookingDTOConverter extends Converter<Booking, BookingDTO> {
    @Override
    @Mapping(source = "day.day", target = "day")
    @Mapping(target = "duration", qualifiedByName = "minutesToTime")
    BookingDTO convert(Booking source);

    default LocalTime minutesToTime(final int minutes) {
        return LocalTime.of(minutes / 60, minutes % 60);
    }
}
