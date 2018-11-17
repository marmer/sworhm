package io.github.marmer.sworhm.core.impl;

import io.github.marmer.sworhm.core.BookingService;
import io.github.marmer.sworhm.core.model.Booking;

import java.time.LocalDate;
import java.util.List;

public class SimpleBookingService implements BookingService {
    @Override
    public Booking storeBooking(final Booking booking) {
        // TODO: marmer implement me
        return null;
    }

    @Override
    public List<Booking> findBookingsByDay(final LocalDate day) {
        // TODO: marmer implement me
        return null;
    }
}
