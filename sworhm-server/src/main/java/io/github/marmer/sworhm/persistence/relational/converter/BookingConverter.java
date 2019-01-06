package io.github.marmer.sworhm.persistence.relational.converter;

import io.github.marmer.sworhm.core.Converter;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.persistence.relational.entity.BookingEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = BookingDayConverter.class)
public interface BookingConverter extends Converter<BookingEntity, Booking> {

    @Override
    Booking convert(BookingEntity s);
}
