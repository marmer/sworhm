package io.github.marmer.sworhm.persistence.relational;

import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.core.persistence.BookingPersistencePort;
import io.github.marmer.sworhm.persistence.relational.converter.entity.BookingEntityConverter;
import io.github.marmer.sworhm.persistence.relational.converter.internal.BookingConverterFromDbo;
import io.github.marmer.sworhm.persistence.relational.entity.BookingDbo;
import io.github.marmer.sworhm.persistence.relational.repositories.BookingDayDboRepository;
import io.github.marmer.sworhm.persistence.relational.repositories.BookingDboRepository;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.List;

@Named
public class JpaBookingPersistencePortImpl implements BookingPersistencePort {
    private final BookingDboRepository bookingDboRepository;
    private final BookingConverterFromDbo bookingConverterFromDbo;
    private final BookingEntityConverter bookingEntityConverter;
    private final BookingDayDboRepository bookingDayDboRepository;

    public JpaBookingPersistencePortImpl(final BookingDboRepository bookingDboRepository, final BookingConverterFromDbo bookingConverterFromDbo, final BookingEntityConverter bookingEntityConverter, final BookingDayDboRepository bookingDayDboRepository) {
        this.bookingDboRepository = bookingDboRepository;
        this.bookingConverterFromDbo = bookingConverterFromDbo;
        this.bookingEntityConverter = bookingEntityConverter;
        this.bookingDayDboRepository = bookingDayDboRepository;
    }

    @Override
    public Booking storeBooking(final Booking booking) {
        final BookingDbo bookingToSave = bookingEntityConverter.convert(booking);
        bookingDayDboRepository.findByDay(booking.getDay().getDay()).ifPresent(bookingToSave::setDay);
        final BookingDbo saved = bookingDboRepository.save(bookingToSave);
        return bookingConverterFromDbo.convert(saved);
    }

    @Override
    public List<Booking> findBookingsByDay(final LocalDate day) {
        return bookingConverterFromDbo.convert(bookingDboRepository.findByDayDay(day));
    }

    @Override
    public void deleteBooking(final String bookingId) {
        bookingDboRepository.deleteById(bookingId);
    }
}
