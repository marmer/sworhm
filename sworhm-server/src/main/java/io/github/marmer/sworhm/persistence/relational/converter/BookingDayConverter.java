package io.github.marmer.sworhm.persistence.relational.converter;

import io.github.marmer.sworhm.core.Converter;
import io.github.marmer.sworhm.core.model.BookingDay;
import io.github.marmer.sworhm.core.persistence.entity.BookingDayEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingDayConverter extends Converter<BookingDayEntity, BookingDay> {

    @Override
    BookingDay convert(BookingDayEntity s);
}
