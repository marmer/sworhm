package io.github.marmer.sworhm.persistence.relational.converter.external;

import io.github.marmer.sworhm.MappingConfiguration;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.persistence.relational.entity.BookingDbo;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfiguration.class)
public interface BookingDboFromBookingConverter {
    BookingDbo convert(final Booking source);
}
