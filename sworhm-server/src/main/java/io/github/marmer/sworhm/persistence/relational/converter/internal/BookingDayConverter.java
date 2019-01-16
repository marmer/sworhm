package io.github.marmer.sworhm.persistence.relational.converter.internal;

import io.github.marmer.sworhm.MappingConfiguration;
import io.github.marmer.sworhm.core.Converter;
import io.github.marmer.sworhm.core.model.BookingDay;
import io.github.marmer.sworhm.persistence.relational.entity.BookingDayEntity;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfiguration.class)
public interface BookingDayConverter extends Converter<BookingDayEntity, BookingDay> {

    @Override
    BookingDay convert(BookingDayEntity s);
}
