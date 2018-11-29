package io.github.marmer.sworhm.core.impl;

import io.github.marmer.sworhm.core.BookingService;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.core.persistence.BookingPersistencePort;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Named;

@Named
public class SimpleBookingService implements BookingService {
    private final BookingPersistencePort bookingPersistencePort;

    public SimpleBookingService(final BookingPersistencePort bookingPersistencePort) {
        this.bookingPersistencePort = bookingPersistencePort;
    }

    @Override
    public Booking storeBooking(final Booking booking) {
        return bookingPersistencePort.storeBooking(booking);
    }

    @Override
    public List<Booking> findBookingsByDay(final LocalDate day) {
        return bookingPersistencePort.findBookingsByDay(day);
    }
}
