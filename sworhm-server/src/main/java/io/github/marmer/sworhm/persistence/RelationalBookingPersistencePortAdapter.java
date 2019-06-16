package io.github.marmer.sworhm.persistence;

import io.github.marmer.sworhm.core.Converter;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.core.persistence.BookingPersistencePort;
import io.github.marmer.sworhm.persistence.relational.entity.BookingDbo;
import io.github.marmer.sworhm.persistence.relational.repositories.BookingDboRepository;

import javax.inject.Named;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.stream.Collector;
import java.util.stream.Stream;

@Named
public class RelationalBookingPersistencePortAdapter implements BookingPersistencePort {
    private final BookingDboRepository bookingDboRepository;
    private final Converter<BookingDbo, Booking> bookingConverter;

    public RelationalBookingPersistencePortAdapter(final BookingDboRepository bookingDboRepository, final Converter<BookingDbo, Booking> bookingConverter) {
        this.bookingDboRepository = bookingDboRepository;
        this.bookingConverter = bookingConverter;
    }

    @Override
    @Transactional
    public Stream<Booking> findBookingsByDay(final LocalDate day) {
        return bookingDboRepository.findAllByDay(day)
                .map(bookingConverter::convert)
                .collect(toStreamProcessed());
    }

    private <T> Collector<T, Stream.Builder<T>, Stream<T>> toStreamProcessed() {
        return Collector.of(
                Stream::builder,
                Stream.Builder::add,
                (builder, builder2) -> {
                    builder2.build().forEach(builder::add);
                    return builder;
                },
                Stream.Builder::build
        );
    }
}
