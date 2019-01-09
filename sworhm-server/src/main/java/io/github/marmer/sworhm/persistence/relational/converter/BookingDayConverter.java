package io.github.marmer.sworhm.persistence.relational.converter;

import io.github.marmer.sworhm.core.Converter;
import io.github.marmer.sworhm.core.model.BookingDay;
import io.github.marmer.sworhm.persistence.relational.entity.BookingDayEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface BookingDayConverter extends Converter<BookingDayEntity, BookingDay> {

    @Override
    BookingDay convert(BookingDayEntity s);
}
