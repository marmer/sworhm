package io.github.marmer.sworhm.web.ui.converter.dto;

import io.github.marmer.sworhm.MappingConfiguration;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.web.ui.dto.BookingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalTime;

@Mapper(config = MappingConfiguration.class)
public interface BookingDTOConverter {
    @Mapping(source = "day.day", target = "day")
    @Mapping(target = "duration", qualifiedByName = "minutesToTime")
    BookingDTO convert(Booking source);

    default LocalTime minutesToTime(final int minutes) {
        return LocalTime.of(minutes / 60, minutes % 60);
    }
}
