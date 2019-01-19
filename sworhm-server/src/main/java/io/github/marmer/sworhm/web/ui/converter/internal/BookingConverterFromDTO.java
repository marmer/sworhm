package io.github.marmer.sworhm.web.ui.converter.internal;

import io.github.marmer.sworhm.MappingConfiguration;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.core.model.BookingDay;
import io.github.marmer.sworhm.web.ui.dto.BookingDTO;
import org.mapstruct.*;

import java.time.LocalDate;
import java.time.LocalTime;

// TODO: marmer 16.01.2019 unmappedSourcePolicy should be removed as soon as MapstructBug is fixed
@Mapper(config = MappingConfiguration.class, unmappedSourcePolicy = ReportingPolicy.WARN)
public interface BookingConverterFromDTO {
    @Mapping(source = "day", target = "day.day")
    @Mapping(source = "duration", target = "duration", qualifiedByName = "localDateToDurationInMinutes")
    Booking convert(final BookingDTO bookingDTOToStore, @Context final LocalDate day);

    default int localDateToDurationInMinutes(final LocalTime localTime) {
        return localTime == null ?
                0 :
                localTime.getHour() * 60 + localTime.getMinute();
    }

    @AfterMapping
    default void setDayOfContent(@MappingTarget final BookingDay.BookingDayBuilder bookingDay, @Context final LocalDate day) {
        bookingDay.day(day);
    }
}
