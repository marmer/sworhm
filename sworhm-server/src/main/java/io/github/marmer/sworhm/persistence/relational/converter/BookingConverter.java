package io.github.marmer.sworhm.persistence.relational.converter;

import io.github.marmer.sworhm.core.Converter;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.core.persistence.entity.BookingEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingConverter extends Converter<BookingEntity, Booking> {

    @Override
    Booking convert(BookingEntity s);
}
