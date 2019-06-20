package io.github.marmer.sworhm.persistence;

import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.core.persistence.BookingPersistencePort;
import io.github.marmer.sworhm.persistence.relational.converter.external.BookingDboFromBookingConverter;
import io.github.marmer.sworhm.persistence.relational.converter.internal.BookingFromBookingDboConverter;
import io.github.marmer.sworhm.persistence.relational.entity.BookingDbo;
import io.github.marmer.sworhm.persistence.relational.repositories.BookingDboRepository;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.stream.Stream;

@Named
public class RelationalBookingPersistencePortAdapter implements BookingPersistencePort {
    private final BookingDboRepository bookingDboRepository;
    private final BookingFromBookingDboConverter bookingConverter;
    private final BookingDboFromBookingConverter bookingDboFromBookingConverter;

    public RelationalBookingPersistencePortAdapter(final BookingDboRepository bookingDboRepository, final BookingFromBookingDboConverter bookingConverter, final BookingDboFromBookingConverter bookingDboFromBookingConverter) {
        this.bookingDboRepository = bookingDboRepository;
        this.bookingConverter = bookingConverter;
        this.bookingDboFromBookingConverter = bookingDboFromBookingConverter;
    }

    @Override
    public Stream<Booking> findBookingsByDay(final LocalDate day) {
        return bookingDboRepository.findAllByDay(day)
                .stream()
                .map(bookingConverter::convert);
    }

    @Override
    public void saveOrUpdate(final Booking booking) {
        final BookingDbo dbo = bookingDboFromBookingConverter.convert(booking);
        bookingDboRepository.saveOrUpdate(dbo);
    }

    @Override
    public void deleteBookingById(final String id) {
        bookingDboRepository.deleteBookingById(id);
    }
}
