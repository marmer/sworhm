package io.github.marmer.sworhm.persistence.relational;

import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.core.persistence.BookingPersistencePort;

import javax.inject.Named;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

/**
 * TODO Replace me!
 */
@Named
public class DummyBookingPersistencePortImpl implements BookingPersistencePort {
    @Override
    public Booking storeBooking(final Booking booking) {
        return Booking.builder().startTime(LocalTime.now()).build();
    }

    @Override
    public List<Booking> findBookingsByDay(final LocalDate day) {
        return Collections.emptyList();
    }
}
