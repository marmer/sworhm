package io.github.marmer.sworhm.core.impl;

import io.github.marmer.sworhm.core.BookingService;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.core.persistence.BookingPersistencePort;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.stream.Stream;

@Named
public class SimpleBookingService implements BookingService {
    private final BookingPersistencePort bookingPersistencePort;

    public SimpleBookingService(final BookingPersistencePort bookingPersistencePort) {
        this.bookingPersistencePort = bookingPersistencePort;
    }

    @Override
    public Stream<Booking> getBookingsByDay(final LocalDate day) {
        return bookingPersistencePort.findBookingsByDay(day);
    }

    @Override
    public void saveOrUpdate(final Booking booking) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
