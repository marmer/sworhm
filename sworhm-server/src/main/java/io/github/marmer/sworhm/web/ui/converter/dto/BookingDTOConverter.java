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
        final int max = 60 * 24 - 1;
        final int cutMinutes = Math.abs(minutes) > max ? max : Math.abs(minutes);
        return LocalTime.of(cutMinutes / 60, cutMinutes % 60);
    }
}
