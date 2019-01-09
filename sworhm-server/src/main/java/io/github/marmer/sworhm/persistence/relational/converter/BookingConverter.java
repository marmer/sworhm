package io.github.marmer.sworhm.persistence.relational.converter;

import io.github.marmer.sworhm.core.Converter;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.persistence.relational.entity.BookingEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

// TODO: marmer 09.01.2019 use @MapperConfig
@Mapper(componentModel = "spring", uses = {BookingDayConverter.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface BookingConverter extends Converter<BookingEntity, Booking> {


    @Override
    Booking convert(BookingEntity s);
}
