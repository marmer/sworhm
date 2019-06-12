package io.github.marmer.sworhm.persistence.relational.converter.internal;

import io.github.marmer.sworhm.MappingConfiguration;
import io.github.marmer.sworhm.core.Converter;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.persistence.relational.entity.BookingDbo;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfiguration.class, uses = {BookingDayConverter.class})
public interface BookingConverterFromEntity extends Converter<BookingDbo, Booking> {


    @Override
    Booking convert(BookingDbo s);
}
