package io.github.marmer.sworhm.persistence.relational.converter;

import io.github.marmer.sworhm.MappingConfiguration;
import io.github.marmer.sworhm.core.Converter;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.persistence.relational.entity.BookingEntity;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfiguration.class, uses = {BookingDayConverter.class})
public interface BookingConverter extends Converter<BookingEntity, Booking> {


    @Override
    Booking convert(BookingEntity s);
}
