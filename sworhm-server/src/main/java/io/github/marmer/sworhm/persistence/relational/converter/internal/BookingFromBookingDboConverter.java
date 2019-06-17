package io.github.marmer.sworhm.persistence.relational.converter.internal;

import io.github.marmer.sworhm.MappingConfiguration;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.persistence.relational.entity.BookingDbo;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfiguration.class)
public interface BookingFromBookingDboConverter {
    Booking convert(final BookingDbo source);
}
