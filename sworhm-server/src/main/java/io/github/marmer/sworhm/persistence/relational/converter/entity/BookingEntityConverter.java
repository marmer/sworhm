package io.github.marmer.sworhm.persistence.relational.converter.entity;

import io.github.marmer.sworhm.MappingConfiguration;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.persistence.relational.entity.BookingDbo;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfiguration.class)
public interface BookingEntityConverter {
    BookingDbo convert(Booking booking);
}
